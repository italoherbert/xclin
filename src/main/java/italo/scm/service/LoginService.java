package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.logica.HashUtil;
import italo.scm.logica.jwt.JWTTokenLogica;
import italo.scm.model.Usuario;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.request.LoginRequest;
import italo.scm.model.response.LoginResponse;
import italo.scm.repository.UsuarioRepository;

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
								
		LoginResponse resp = new LoginResponse();
		resp.setToken( jwtLogica.geraToken( username, roles, u.getId(), -1L ) );
		resp.setUsername( username );
		resp.setPerfil( u.getPerfil() ); 
		return resp;		
	}
	
}
