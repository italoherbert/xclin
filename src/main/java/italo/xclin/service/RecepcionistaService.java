package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.enums.tipos.UsuarioPerfil;
import italo.xclin.exception.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.RecepcionistaLoader;
import italo.xclin.loader.UsuarioLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Recepcionista;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.filtro.RecepcionistaFiltroRequest;
import italo.xclin.model.request.save.RecepcionistaSaveRequest;
import italo.xclin.model.response.RecepcionistaResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.edit.RecepcionistaEditLoadResponse;
import italo.xclin.model.response.load.reg.RecepcionistaRegLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.RecepcionistaRepository;
import italo.xclin.repository.UsuarioRepository;
import italo.xclin.service.shared.UsuarioSharedService;
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
	public void registra( Long logadoUID, Long clinicaId, RecepcionistaSaveRequest request ) throws ServiceException {
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
	
	public void altera( Long clinicaId, Long recepcionistaId, RecepcionistaSaveRequest request ) throws ServiceException {
		Optional<Recepcionista> recepcionistaOp = recepcionistaRepository.findById( recepcionistaId );
		this.altera2( recepcionistaOp, clinicaId, request );
	}
	
	public void alteraPorLogadoUID( Long logadoUID, Long clinicaId, RecepcionistaSaveRequest request ) throws ServiceException {
		Optional<Recepcionista> recepcionistaOp = recepcionistaRepository.buscaPorUsuario( logadoUID );
		this.altera2( recepcionistaOp, clinicaId, request );
	}
	
	public void altera2( 
			Optional<Recepcionista> recepcionistaOp, 
			Long clinicaId,
			RecepcionistaSaveRequest request ) throws ServiceException {
							
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
		return this.get2( rop );
	}
	
	public RecepcionistaResponse getPorLogadoUID( Long logadoUID ) throws ServiceException {
		Optional<Recepcionista> rop = recepcionistaRepository.buscaPorUsuario( logadoUID );
		return this.get2( rop );
	}
	
	public RecepcionistaResponse get2( Optional<Recepcionista> recepcionistaOp ) throws ServiceException {
		if ( !recepcionistaOp.isPresent() )
			throw new ServiceException( Erro.RECEPCIONISTA_NAO_ENCONTRADO );
		
		Recepcionista r = recepcionistaOp.get();
		
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
		return this.getEditLoad2( recepcionistaOp );
	}
	
	public RecepcionistaEditLoadResponse getEditLoadPorLogadoUID( Long logadoUID ) throws ServiceException {
		Optional<Recepcionista> recepcionistaOp = recepcionistaRepository.buscaPorUsuario( logadoUID ); 
		return this.getEditLoad2( recepcionistaOp );
	}
	
	public RecepcionistaEditLoadResponse getEditLoad2( Optional<Recepcionista> recepcionistaOp ) throws ServiceException {
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
