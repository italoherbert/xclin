package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.enums.tipos.UsuarioPerfil;
import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.DiretorLoader;
import italo.scm.loader.UsuarioLoader;
import italo.scm.model.Diretor;
import italo.scm.model.Usuario;
import italo.scm.model.request.filtro.DiretorFiltroRequest;
import italo.scm.model.request.save.DiretorSaveRequest;
import italo.scm.model.response.DiretorResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.repository.DiretorRepository;
import italo.scm.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class DiretorService {

	@Autowired
	private DiretorRepository diretorRepository;
			
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DiretorLoader diretorLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;
	
	@Transactional
	public void registra( Long logadoUID, DiretorSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		Optional<Diretor> diretorOp = diretorRepository.buscaPorNome( nome );
		if ( diretorOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_JA_EXISTE );

		String username = request.getUsuario().getUsername();
		boolean existeUsuario = usuarioRepository.existePorUsername( username );
		if ( existeUsuario )
			throw new ServiceException( Erro.USUARIO_JA_EXISTE );
				
		Optional<Usuario> uop = usuarioRepository.findById( logadoUID );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario usuarioLogado = uop.get();
							
		Usuario u = usuarioLoader.novoBean( usuarioLogado );
		usuarioLoader.loadBean( u, request.getUsuario() );
		u.setPerfil( UsuarioPerfil.DIRETOR );
					
		Diretor d = diretorLoader.novoBean( u );
		diretorLoader.loadBean( d, request );
		
		diretorRepository.save( d );					
	}
		
	public void altera( Long uid, DiretorSaveRequest request ) throws ServiceException {
		Optional<Diretor> dop = diretorRepository.findById( uid );
		if ( !dop.isPresent() )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );		
		
		Diretor d = dop.get();
		Usuario u = d.getUsuario();
		
		String nome = request.getNome();
		if ( !nome.equalsIgnoreCase( d.getNome() ) ) {
			boolean existe = diretorRepository.existePorNome( nome );
			if ( existe )
				throw new ServiceException( Erro.DIRETOR_JA_EXISTE );
		}
		
		String username = request.getUsuario().getUsername();
		if ( !username.equalsIgnoreCase( u.getUsername() ) ) {
			boolean existe = usuarioRepository.existePorUsername( username );
			if ( existe )
				throw new ServiceException( Erro.USUARIO_JA_EXISTE );
		}
		
		diretorLoader.loadBean( d, request );		
		diretorRepository.save( d );
	}
		
	public List<DiretorResponse> filtra( DiretorFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();
		String clinicaNomeIni = request.getClinicaNomeIni();
		
		List<Diretor> diretores;
		if ( nomeIni.equals( "*" ) ) {
			if ( request.isIncluirClinica() ) {
				diretores = diretorRepository.filtraPorClinica( "%"+clinicaNomeIni+"%" );
			} else {
				diretores = diretorRepository.findAll();
			}
		} else {
			if ( request.isIncluirClinica() ) {
				diretores = diretorRepository.filtra( nomeIni+"%", "%"+clinicaNomeIni+"%" );
			} else {
				diretores = diretorRepository.filtraPorNome( nomeIni+"%" );
			}
		}
		
		List<DiretorResponse> lista = new ArrayList<>();
		for( Diretor d : diretores ) {
			UsuarioResponse uresp = usuarioLoader.novoResponse();
			usuarioLoader.loadResponse( uresp, d.getUsuario() ); 
			
			DiretorResponse resp = diretorLoader.novoResponse( uresp );
			diretorLoader.loadResponse( resp, d );			
			lista.add( resp );
		}
		return lista;
	}
	
	public DiretorResponse get( Long id ) throws ServiceException {
		Optional<Diretor> diretorOp = diretorRepository.findById( id );
		if ( !diretorOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );
		
		Diretor d = diretorOp.get();
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, d.getUsuario() ); 
		
		DiretorResponse resp = diretorLoader.novoResponse( uresp );
		diretorLoader.loadResponse( resp, d );
		
		return resp;
	}
		
	public void deleta( Long id ) throws ServiceException {
		boolean existe = diretorRepository.existsById( id );
		if ( !existe )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );
		
		diretorRepository.deleteById( id ); 
	}
	
}
