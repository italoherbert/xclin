package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.logica.HashUtil;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.Diretor;
import italo.scm.model.DiretorClinicaVinculo;
import italo.scm.model.Profissional;
import italo.scm.model.ProfissionalClinicaVinculo;
import italo.scm.model.Recepcionista;
import italo.scm.model.Usuario;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.request.LoginRequest;
import italo.scm.model.response.LoginResponse;
import italo.scm.repository.DiretorRepository;
import italo.scm.repository.ProfissionalRepository;
import italo.scm.repository.RecepcionistaRepository;
import italo.scm.repository.UsuarioRepository;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DiretorRepository diretorRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private RecepcionistaRepository recepcionistaRepository;
	
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
		
		List<Long> clinicasIDs = this.buscaClinicasIDs( u );
								
		LoginResponse resp = new LoginResponse();
		resp.setToken( jwtLogica.geraToken( username, roles, perfil, u.getId(), clinicasIDs ) );
		resp.setUsername( username );
		resp.setPerfil( u.getPerfil() ); 
		return resp;		
	}
	
	public List<Long> buscaClinicasIDs( Usuario u ) throws ServiceException {
		List<Long> clinicasIDs = new ArrayList<>();
		switch( u.getPerfil() ) {
			case RAIZ:
			case ADMIN:
				clinicasIDs.add( -1L );
				break;
			case DIRETOR:
				Optional<Diretor> dop = diretorRepository.buscaPorUsuario( u.getId() );
				Diretor d = dop.get();
				List<DiretorClinicaVinculo> dvinculos = d.getDiretorClinicaVinculos();
				for( DiretorClinicaVinculo vinculo : dvinculos )
					clinicasIDs.add( vinculo.getClinica().getId() );				
				break;
			case PROFISSIONAL:
				Optional<Profissional> pop = profissionalRepository.buscaPorUsuario( u.getId() );
				Profissional p = pop.get();
				List<ProfissionalClinicaVinculo> pvinculos = p.getProfissionalClinicaVinculos();
				for( ProfissionalClinicaVinculo vinculo : pvinculos )
					clinicasIDs.add( vinculo.getClinica().getId() );
				break;
			case RECEPCIONISTA:
				Optional<Recepcionista> rop = recepcionistaRepository.buscaPorUsuario( u.getId() );
				Recepcionista r = rop.get();
				clinicasIDs.add( r.getClinica().getId() );
				break;			
		}
		return clinicasIDs;
	}
	
}
