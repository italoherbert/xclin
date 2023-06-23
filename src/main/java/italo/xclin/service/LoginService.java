package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.logica.HashUtil;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.Clinica;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioClinicaVinculo;
import italo.xclin.model.UsuarioGrupo;
import italo.xclin.model.request.LoginRequest;
import italo.xclin.model.response.LoginResponse;
import italo.xclin.repository.UsuarioRepository;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepository usuarioRepository;
		
	@Autowired
	private JWTTokenLogica jwtLogica;
	
	@Autowired
	private HashUtil hashUtil;
	
	public LoginResponse login( LoginRequest request ) throws ServiceException {
		String username = request.getUsername();
		String senha = hashUtil.geraHash( request.getSenha() );
		
		Optional<Usuario> uop = usuarioRepository.buscaPorLogin( username, senha );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.LOGIN_INVALIDO );
		
		List<String> roles = new ArrayList<>();
		
		Usuario u = uop.get();
		String perfil = u.getPerfil().name();		
		
		u.getUsuarioGrupoVinculos().forEach( ( map ) -> {
			UsuarioGrupo grupo = map.getGrupo();
			grupo.getAcessos().forEach( ( acesso ) -> {
				String recurso = acesso.getRecurso().getNome();
				if ( acesso.isLeitura() )
					roles.add( recurso + "READ" );
				if ( acesso.isEscrita() )
					roles.add( recurso + "WRITE" );
				if ( acesso.isRemocao() )
					roles.add( recurso + "DELETE" );
			} );
		} );
		
		List<UsuarioClinicaVinculo> vinculos = u.getUsuarioClinicaVinculos();
		
		List<Long> clinicasIDs = new ArrayList<>();
		for( UsuarioClinicaVinculo vinculo : vinculos ) {
			Clinica c = vinculo.getClinica();
			clinicasIDs.add( c.getId() );
		}
								
		LoginResponse resp = new LoginResponse();
		resp.setToken( jwtLogica.geraToken( username, roles, perfil, u.getId(), clinicasIDs ) );
		resp.setUsername( username );
		resp.setPerfil( u.getPerfil() );
		resp.setPerfilLabel( u.getPerfil().label() ); 
		return resp;		
	}
		
}
