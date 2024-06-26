package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.enums.tipos.UsuarioPerfil;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ProfissionalLoader;
import italo.xclin.loader.UsuarioLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalEspecialidadeVinculo;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioClinicaVinculo;
import italo.xclin.model.request.filtro.ProfissionalFiltroRequest;
import italo.xclin.model.request.save.ProfissionalSaveRequest;
import italo.xclin.model.response.ProfissionalResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.detalhes.ProfissionalDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.ProfissionalEditLoadResponse;
import italo.xclin.model.response.load.reg.ProfissionalRegLoadResponse;
import italo.xclin.repository.ProfissionalRepository;
import italo.xclin.repository.UsuarioRepository;
import italo.xclin.service.shared.UsuarioSharedService;
import jakarta.transaction.Transactional;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository profissionalRepository;
			
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProfissionalLoader profissionalLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;
	
	@Autowired
	private UsuarioSharedService usuarioSharedService;
	
	@Transactional
	public void registra( Long logadoUID, ProfissionalSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorNome( nome );
		if ( profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_JA_EXISTE );
		
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
		u.setPerfil( UsuarioPerfil.PROFISSIONAL );
		
		Profissional p = profissionalLoader.novoBean( u );
		profissionalLoader.loadBean( p, request );
				
		profissionalRepository.save( p );
		
		usuarioSharedService.vinculaGrupo( u, UsuarioPerfil.PROFISSIONAL ); 
	}
				
	public void alteraPorLogadoUID( Long logadoUID, ProfissionalSaveRequest request ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		this.altera( profissionalOp, request ); 
	}
	
	public void altera( Long profissionalId, ProfissionalSaveRequest request ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );			
		this.altera( profissionalOp, request ); 
	}
	
	public void altera( Optional<Profissional> profissionalOp, ProfissionalSaveRequest request ) throws ServiceException {		
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );		
		
		Profissional p = profissionalOp.get();
		Usuario u = p.getUsuario();
		
		String nome = request.getNome();
		if ( !nome.equalsIgnoreCase( p.getNome() ) ) {
			boolean existe = profissionalRepository.existePorNome( nome );
			if ( existe )
				throw new ServiceException( Erro.PROFISSIONAL_JA_EXISTE );
		}
		
		String username = request.getUsuario().getUsername();
		if ( !username.equalsIgnoreCase( u.getUsername() ) ) {
			boolean existe = usuarioRepository.existePorUsername( username );
			if ( existe )
				throw new ServiceException( Erro.USUARIO_JA_EXISTE );
		}
		
		usuarioLoader.loadBean( u, request.getUsuario() );
		profissionalLoader.loadBean( p, request ); 
				
		profissionalRepository.save( p );
	}
	
	public List<ProfissionalResponse> filtra( Long clinicaId, ProfissionalFiltroRequest request ) throws ServiceException {
		String filtroNome = request.getFiltroNome();
		
		List<Profissional> profissionais;
		if ( filtroNome.equals( "*" ) ) {
			profissionais = profissionalRepository.listaPorClinica( clinicaId );			
		} else {
			profissionais = profissionalRepository.filtra( clinicaId, "%"+filtroNome+"%" );
		}
		
		List<ProfissionalResponse> lista = new ArrayList<>();
		for( Profissional p : profissionais ) {
			UsuarioResponse uresp = usuarioLoader.novoResponse();
			usuarioLoader.loadResponse( uresp, p.getUsuario() ); 
			
			ProfissionalResponse resp = profissionalLoader.novoResponse( uresp );
			profissionalLoader.loadResponse( resp, p );			
			lista.add( resp );
		}
		return lista;
	}

	public List<ProfissionalResponse> filtraTodos( ProfissionalFiltroRequest request ) throws ServiceException {
		String filtroNome = request.getFiltroNome();
		
		List<Profissional> profissionais;
		if ( filtroNome.equals( "*" ) ) {
			profissionais = profissionalRepository.findAll();		
		} else {
			profissionais = profissionalRepository.filtraTodos( "%"+filtroNome+"%" );
		}
		
		List<ProfissionalResponse> lista = new ArrayList<>();
		for( Profissional p : profissionais ) {
			UsuarioResponse uresp = usuarioLoader.novoResponse();
			usuarioLoader.loadResponse( uresp, p.getUsuario() ); 
			
			ProfissionalResponse resp = profissionalLoader.novoResponse( uresp );
			profissionalLoader.loadResponse( resp, p );			
			lista.add( resp );
		}
		return lista;
	}
		
	public ProfissionalResponse get( Long id ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.findById( id );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional p = profissionalOp.get();
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, p.getUsuario() ); 
		
		ProfissionalResponse resp = profissionalLoader.novoResponse( uresp );
		profissionalLoader.loadResponse( resp, p );
		
		return resp;
	}
	
	public ProfissionalDetalhesLoadResponse getDetalhesLoadPorLogadoUID( Long logadoUID ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		return this.getDetalhesLoad2( profissionalOp );
	}
	
	public ProfissionalDetalhesLoadResponse getDetalhesLoad( Long profissionalId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		return this.getDetalhesLoad2( profissionalOp );
	}
	
	public ProfissionalDetalhesLoadResponse getDetalhesLoad2( Optional<Profissional> profissionalOp ) throws ServiceException {
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional p = profissionalOp.get();
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, p.getUsuario() ); 
		
		ProfissionalResponse profissionalResp = profissionalLoader.novoResponse( uresp );
		profissionalLoader.loadResponse( profissionalResp, p );
		
		List<String> clinicas = new ArrayList<>();
		
		List<UsuarioClinicaVinculo> pcVinculos = p.getUsuario().getUsuarioClinicaVinculos();
		for( UsuarioClinicaVinculo vinculo : pcVinculos ) {
			Clinica clinica = vinculo.getClinica();
			clinicas.add( clinica.getNome() );
		}

		List<String> especialidades = new ArrayList<>();
		
		List<ProfissionalEspecialidadeVinculo> peVinculos = p.getProfissionalEspecialidadeVinculos();
		for( ProfissionalEspecialidadeVinculo vinculo : peVinculos )
			especialidades.add( vinculo.getEspecialidade().getNome() );		
		
		return profissionalLoader.novoDetalhesResponse( 
				profissionalResp, clinicas, especialidades );
	}
	
	public ProfissionalRegLoadResponse getRegLoad() throws ServiceException {		
		ProfissionalRegLoadResponse resp = profissionalLoader.novoRegLoadResponse();
		profissionalLoader.loadRegResponse( resp );	
		return resp;
	}
	
	public ProfissionalEditLoadResponse getEditLoadPorLogadoUID( Long logadoUID ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		return this.getEditLoad2( profissionalOp );
	}
	
	public ProfissionalEditLoadResponse getEditLoad( Long id ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.findById( id );
		return this.getEditLoad2( profissionalOp );
	}
	
	public ProfissionalEditLoadResponse getEditLoad2( Optional<Profissional> profissionalOp ) throws ServiceException {
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional p = profissionalOp.get();
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, p.getUsuario() ); 
		
		ProfissionalResponse presp = profissionalLoader.novoResponse( uresp );
		profissionalLoader.loadResponse( presp, p );
		
		ProfissionalEditLoadResponse resp = profissionalLoader.novoEditLoadResponse( presp );
		profissionalLoader.loadEditResponse( resp );	
		return resp;
	}
			
	public void deleta( Long id ) throws ServiceException {
		boolean existe = profissionalRepository.existsById( id );
		if ( !existe )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		profissionalRepository.deleteById( id ); 
	}
	
}
