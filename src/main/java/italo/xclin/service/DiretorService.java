package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.enums.tipos.UsuarioPerfil;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.DiretorLoader;
import italo.xclin.loader.UsuarioLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Diretor;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioClinicaVinculo;
import italo.xclin.model.request.filtro.DiretorFiltroRequest;
import italo.xclin.model.request.save.DiretorSaveRequest;
import italo.xclin.model.response.DiretorResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.detalhes.DiretorDetalhesLoadResponse;
import italo.xclin.repository.DiretorRepository;
import italo.xclin.repository.UsuarioRepository;
import italo.xclin.service.shared.UsuarioSharedService;
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
	
	@Autowired
	private UsuarioSharedService usuarioSharedService;
	
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
		usuarioSharedService.vinculaGrupo( u, UsuarioPerfil.DIRETOR );
	}
		
	public void alteraPorLogadoUID( Long logadoUID, DiretorSaveRequest request ) throws ServiceException {
		Optional<Diretor> diretorOp = diretorRepository.buscaPorUsuario( logadoUID );
		this.altera2( diretorOp, request ); 
	}
	
	public void altera( Long uid, DiretorSaveRequest request ) throws ServiceException {
		Optional<Diretor> diretorOp = diretorRepository.findById( uid );
		this.altera2( diretorOp, request );
	}
	
	private void altera2( Optional<Diretor> diretorOp, DiretorSaveRequest request ) throws ServiceException {
		if ( !diretorOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );		
		
		Diretor d = diretorOp.get();
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
		
		usuarioLoader.loadBean( u, request.getUsuario() ); 
		diretorLoader.loadBean( d, request );
		
		diretorRepository.save( d );
	}
		
	public List<DiretorResponse> filtra( Long clinicaId, DiretorFiltroRequest request ) throws ServiceException {
		String filtroNome = request.getFiltroNome();
		
		List<Diretor> diretores;
		if ( filtroNome.equals( "*" ) ) {
			diretores = diretorRepository.filtraPorClinica( clinicaId );
		} else {
			diretores = diretorRepository.filtra( clinicaId, "%"+filtroNome+"%" );
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
	
	public List<DiretorResponse> filtraTodos( DiretorFiltroRequest request ) throws ServiceException {
		String filtroNome = request.getFiltroNome();
		
		List<Diretor> diretores;
		if ( filtroNome.equals( "*" ) ) {
			diretores = diretorRepository.findAll();
		} else {
			diretores = diretorRepository.filtraTodos( "%"+filtroNome+"%" );
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
	
	public DiretorResponse getPorLogadoUID( Long logadoUID ) throws ServiceException {
		Optional<Diretor> diretorOp = diretorRepository.buscaPorUsuario( logadoUID );
		if ( !diretorOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_LOGADO_NAO_ENCONTRADO );
		
		Diretor d = diretorOp.get();
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, d.getUsuario() ); 
		
		DiretorResponse resp = diretorLoader.novoResponse( uresp );
		diretorLoader.loadResponse( resp, d );
		
		return resp;
	}
		
	public DiretorDetalhesLoadResponse getDetalhesLoadPorLogadoUID( Long logadoUID ) throws ServiceException {
		Optional<Diretor> diretorOp = diretorRepository.buscaPorUsuario( logadoUID );		
		if ( !diretorOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_LOGADO_NAO_ENCONTRADO );
		
		return this.getDetalhesLoad2( diretorOp );
	}
	
	public DiretorDetalhesLoadResponse getDetalhesLoad( Long id ) throws ServiceException {
		Optional<Diretor> diretorOp = diretorRepository.findById( id );
		if ( !diretorOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );
		
		return this.getDetalhesLoad2( diretorOp );
	}
	
	public DiretorDetalhesLoadResponse getDetalhesLoad2( Optional<Diretor> diretorOp ) throws ServiceException {			
		Diretor d = diretorOp.get();
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, d.getUsuario() ); 
		
		DiretorResponse diretorResp = diretorLoader.novoResponse( uresp );
		diretorLoader.loadResponse( diretorResp, d );
		
		List<String> clinicas = new ArrayList<>();
		
		List<UsuarioClinicaVinculo> vinculos = d.getUsuario().getUsuarioClinicaVinculos();
		for( UsuarioClinicaVinculo vinculo : vinculos ) {
			Clinica clinica = vinculo.getClinica();
			clinicas.add( clinica.getNome() );
		}
		
		DiretorDetalhesLoadResponse resp = diretorLoader.novoDetalhesResponse( diretorResp, clinicas );		
		return resp;
	}
	
	public void deleta( Long id ) throws ServiceException {
		boolean existe = diretorRepository.existsById( id );
		if ( !existe )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );
		
		diretorRepository.deleteById( id ); 
	}
	
}
