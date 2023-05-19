package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.enums.tipos.UsuarioPerfil;
import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.RecepcionistaLoader;
import italo.scm.loader.UsuarioLoader;
import italo.scm.model.Clinica;
import italo.scm.model.Recepcionista;
import italo.scm.model.Usuario;
import italo.scm.model.request.filtro.RecepcionistaFiltroRequest;
import italo.scm.model.request.save.RecepcionistaSaveRequest;
import italo.scm.model.response.RecepcionistaResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.RecepcionistaEditLoadResponse;
import italo.scm.model.response.load.RecepcionistaRegLoadResponse;
import italo.scm.repository.ClinicaRepository;
import italo.scm.repository.RecepcionistaRepository;
import italo.scm.repository.UsuarioRepository;
import italo.scm.service.shared.UsuarioSharedService;
import jakarta.transaction.Transactional;

@Service
public class RecepcionistaService {

	@Autowired
	private RecepcionistaRepository recepcionistaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private RecepcionistaLoader recepcionistaLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;
	
	@Autowired
	private UsuarioSharedService usuarioSharedService;
	
	@Transactional
	public void registra( Long logadoUID, RecepcionistaSaveRequest request ) throws ServiceException {
		Long clinicaId = request.getClinicaId();
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
				
		String nome = request.getNome();
		String username = request.getUsuario().getUsername();
		
		boolean existe = recepcionistaRepository.existePorNome( nome );
		if ( existe )
			throw new ServiceException( Erro.RECEPCIONISTA_JA_EXISTE );
		
		boolean existeUsuario = usuarioRepository.existePorUsername( username );
		if ( existeUsuario )
			throw new ServiceException( Erro.USUARIO_JA_EXISTE );
		
		Optional<Usuario> logadoUsuarioOp = usuarioRepository.findById( logadoUID );
		if ( !logadoUsuarioOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario logado = logadoUsuarioOp.get();

		Usuario usuario = usuarioLoader.novoBean( logado );
		usuarioLoader.loadBean( usuario, request.getUsuario() );	
		usuario.setPerfil( UsuarioPerfil.RECEPCIONISTA );
		
		Clinica clinica = clinicaOp.get();

		Recepcionista recepcionista = recepcionistaLoader.novoBean( usuario, clinica );
		recepcionistaLoader.loadBean( recepcionista, request );
		
		recepcionistaRepository.save( recepcionista );
		
		usuarioSharedService.vinculaGrupo( usuario, UsuarioPerfil.RECEPCIONISTA ); 
	}
	
	public void altera( 
			Long recepcionistaId, 
			RecepcionistaSaveRequest request ) throws ServiceException {
					
		Long clinicaId = request.getClinicaId();
		
		Optional<Recepcionista> recepcionistaOp = recepcionistaRepository.findById( recepcionistaId );
		if ( !recepcionistaOp.isPresent() )
			throw new ServiceException( Erro.RECEPCIONISTA_NAO_ENCONTRADO );
		
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Recepcionista r = recepcionistaOp.get();
		Usuario u = r.getUsuario();
		
		Clinica c = clinicaOp.get();
		r.setClinica( c ); 
		
		String nome = request.getNome();
		String username = request.getUsuario().getUsername();
		
		if ( !nome.equalsIgnoreCase( r.getNome() ) ) {
			boolean existe = recepcionistaRepository.existePorNome( nome );
			if ( existe )
				throw new ServiceException( Erro.RECEPCIONISTA_JA_EXISTE );
		}
		
		if ( !username.equalsIgnoreCase( u.getUsername() ) ) {
			boolean existeUsuario = usuarioRepository.existePorUsername( username );
			if ( existeUsuario )
				throw new ServiceException( Erro.USUARIO_JA_EXISTE );
		}
		usuarioLoader.loadBean( u, request.getUsuario() ); 
		recepcionistaLoader.loadBean( r, request ); 
				
		recepcionistaRepository.save( r );
	}
	
	public List<RecepcionistaResponse> filtra( RecepcionistaFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();
		String clinicaNomeIni = request.getClinicaNomeIni();

		List<Recepcionista> recepcionistas;
		if ( nomeIni.equals( "*" ) ) {
			if ( !request.getClinicaNomeIni().isBlank() ) {
				recepcionistas = recepcionistaRepository.filtraPorClinica( "%"+clinicaNomeIni+"%" );
			} else {
				recepcionistas = recepcionistaRepository.findAll();
			}
		} else {
			if ( !request.getClinicaNomeIni().isBlank() ) {
				recepcionistas = recepcionistaRepository.filtra( nomeIni+"%", "%"+clinicaNomeIni+"%" );
			} else {
				recepcionistas = recepcionistaRepository.filtra( nomeIni+"%" );
			}
		}
		
		List<RecepcionistaResponse> lista = new ArrayList<>();
		for( Recepcionista r : recepcionistas ) {					
			Usuario u = r.getUsuario();
			Clinica c = r.getClinica();
						
			UsuarioResponse uresp = usuarioLoader.novoResponse();
			usuarioLoader.loadResponse( uresp, u );
			
			RecepcionistaResponse resp = recepcionistaLoader.novoResponse( uresp, c );
			recepcionistaLoader.loadResponse( resp, r );
			
			lista.add( resp );
		}
		return lista;		
	}
	
	public RecepcionistaResponse get( Long id ) throws ServiceException {
		Optional<Recepcionista> rop = recepcionistaRepository.findById( id );
		if ( !rop.isPresent() )
			throw new ServiceException( Erro.RECEPCIONISTA_NAO_ENCONTRADO );
		
		Recepcionista r = rop.get();
		
		Usuario u = r.getUsuario();
		Clinica c = r.getClinica();
				
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, u );
		
		RecepcionistaResponse resp = recepcionistaLoader.novoResponse( uresp, c );
		recepcionistaLoader.loadResponse( resp, r );
		
		return resp;		
	}
	
	public RecepcionistaEditLoadResponse getEditLoad( Long id ) throws ServiceException {
		Optional<Recepcionista> recepcionistaOp = recepcionistaRepository.findById( id );
		if ( !recepcionistaOp.isPresent() )
			throw new ServiceException( Erro.RECEPCIONISTA_NAO_ENCONTRADO );
		
		Recepcionista r = recepcionistaOp.get();
		Usuario usuario = r.getUsuario();
		Clinica clinica = r.getClinica();
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, usuario );
				
		RecepcionistaResponse rresp = recepcionistaLoader.novoResponse( uresp, clinica );
		recepcionistaLoader.loadResponse( rresp, r ); 
		
		List<Clinica> clinicas = clinicaRepository.findAll();
		
		List<Long> clinicasIDs = new ArrayList<>();
		List<String> clinicasNomes = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs.add( c.getId() );
			clinicasNomes.add( c.getNome() );
		}
		
		return recepcionistaLoader.novoEditResponse( rresp, clinicasIDs, clinicasNomes );		
	}
		
	public RecepcionistaRegLoadResponse getRegLoad() throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.findAll();
		
		List<Long> clinicasIDs = new ArrayList<>();
		List<String> clinicasNomes = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs.add( c.getId() );
			clinicasNomes.add( c.getNome() );
		}
		
		return recepcionistaLoader.novoRegResponse( clinicasIDs, clinicasNomes );		
	}
	
	public void deleta( Long id ) throws ServiceException {
		boolean existe = recepcionistaRepository.existsById( id );
		if ( !existe )
			throw new ServiceException( Erro.RECEPCIONISTA_NAO_ENCONTRADO );
		
		recepcionistaRepository.deleteById( id ); 
	}
	
	
}
