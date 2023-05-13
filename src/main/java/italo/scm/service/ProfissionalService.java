package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.enums.tipos.UsuarioPerfil;
import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.ProfissionalLoader;
import italo.scm.loader.UsuarioLoader;
import italo.scm.model.Clinica;
import italo.scm.model.Profissional;
import italo.scm.model.ProfissionalClinicaVinculo;
import italo.scm.model.Usuario;
import italo.scm.model.request.filtro.ProfissionalFiltroRequest;
import italo.scm.model.request.save.ProfissionalSaveRequest;
import italo.scm.model.response.ProfissionalResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.ProfissionalDetalhesLoadResponse;
import italo.scm.model.response.load.ProfissionalEditLoadResponse;
import italo.scm.model.response.load.ProfissionalRegLoadResponse;
import italo.scm.repository.ProfissionalRepository;
import italo.scm.repository.UsuarioRepository;
import italo.scm.service.shared.UsuarioSharedService;
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
		usuarioSharedService.vinculaGrupo( usuarioLogado, UsuarioPerfil.PROFISSIONAL ); 
	}
			
	public void alteraCompleto( Long uid, ProfissionalSaveRequest request ) throws ServiceException {
		this.altera( uid, true, request);
	}
	
	public void alteraParcial( Long uid, ProfissionalSaveRequest request ) throws ServiceException {
		this.altera( uid, false, request );
	}
	
	private void altera( Long uid, boolean completo, ProfissionalSaveRequest request ) throws ServiceException {
		Optional<Profissional> dop = profissionalRepository.findById( uid );
		if ( !dop.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );		
		
		Profissional p = dop.get();
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
		
		if ( completo ) {
			profissionalLoader.loadBean( p, request ); 
		} else {
			profissionalLoader.loadParcialBean( p, request );
		}
		
		profissionalRepository.save( p );
	}
		
	public List<ProfissionalResponse> filtra( ProfissionalFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();
		String clinicaNomeIni = request.getClinicaNomeIni();
		
		List<Profissional> profissionales;
		if ( nomeIni.equals( "*" ) ) {
			if ( request.isIncluirClinica() ) {
				profissionales = profissionalRepository.filtraPorClinica( "%"+clinicaNomeIni+"%" );
			} else {
				profissionales = profissionalRepository.findAll();
			}
		} else {
			if ( request.isIncluirClinica() ) {
				profissionales = profissionalRepository.filtra( nomeIni+"%", "%"+clinicaNomeIni+"%" );
			} else {
				profissionales = profissionalRepository.filtraPorNome( nomeIni+"%" );
			}
		}
		
		List<ProfissionalResponse> lista = new ArrayList<>();
		for( Profissional p : profissionales ) {
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
	
	public ProfissionalDetalhesLoadResponse getDetalhesLoad( Long id ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.findById( id );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional p = profissionalOp.get();
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, p.getUsuario() ); 
		
		ProfissionalResponse profissionalResp = profissionalLoader.novoResponse( uresp );
		profissionalLoader.loadResponse( profissionalResp, p );
		
		List<String> clinicas = new ArrayList<>();
		
		List<ProfissionalClinicaVinculo> vinculos = p.getProfissionalClinicaVinculos();
		for( ProfissionalClinicaVinculo vinculo : vinculos ) {
			Clinica clinica = vinculo.getClinica();
			clinicas.add( clinica.getNome() );
		}
		
		ProfissionalDetalhesLoadResponse resp = 
				profissionalLoader.novoDetalhesResponse( profissionalResp , clinicas );
		
		return resp;
	}
	
	public ProfissionalRegLoadResponse getRegLoad() throws ServiceException {		
		ProfissionalRegLoadResponse resp = profissionalLoader.novoRegLoadResponse();
		profissionalLoader.loadRegResponse( resp );	
		return resp;
	}
	
	public ProfissionalEditLoadResponse getEditLoad( Long id ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.findById( id );
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
