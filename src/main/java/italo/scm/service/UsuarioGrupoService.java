package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.AcessoLoader;
import italo.scm.loader.UsuarioGrupoLoader;
import italo.scm.model.Acesso;
import italo.scm.model.Recurso;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.request.filtro.UsuarioGrupoFiltroRequest;
import italo.scm.model.request.save.UsuarioGrupoSaveRequest;
import italo.scm.model.response.AcessoResponse;
import italo.scm.model.response.UsuarioGrupoResponse;
import italo.scm.model.response.load.detalhes.UsuarioGrupoDetalhesLoadResponse;
import italo.scm.model.response.load.edit.UsuarioGrupoEditLoadResponse;
import italo.scm.repository.AcessoRepository;
import italo.scm.repository.UsuarioGrupoRepository;

@Service
public class UsuarioGrupoService {

	@Autowired
	private UsuarioGrupoRepository usuarioGrupoRepository;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@Autowired
	private UsuarioGrupoLoader usuarioGrupoLoader;
	
	@Autowired
	private AcessoLoader acessoLoader;
			
	public void registra( UsuarioGrupoSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.buscaPorNome( nome );
		if ( grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_JA_EXISTE );
		
		UsuarioGrupo grupo = usuarioGrupoLoader.novoBean();
		usuarioGrupoLoader.loadBean( grupo, request );
		
		usuarioGrupoRepository.save( grupo );
	}
	
	public void altera( Long id, UsuarioGrupoSaveRequest request ) throws ServiceException {		
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( id );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		UsuarioGrupo grupo = grupoOp.get();

		String nome = request.getNome();
		if ( !nome.equalsIgnoreCase( grupo.getNome() ) ) {
			boolean existe = usuarioGrupoRepository.existePorNome( nome );
			if ( existe )
				throw new ServiceException( Erro.USUARIO_GRUPO_JA_EXISTE );
		}
		
		usuarioGrupoLoader.loadBean( grupo, request );
		usuarioGrupoRepository.save( grupo );		
	}
	
	public List<UsuarioGrupoResponse> filtra( UsuarioGrupoFiltroRequest request ) throws ServiceException {
		List<UsuarioGrupo> grupos;
		
		String nomeIni = request.getNomeIni();
		if ( nomeIni.equals( "*" ) ) {
			grupos = usuarioGrupoRepository.findAll();
		} else {
			grupos = usuarioGrupoRepository.filtraPorNomeIni( nomeIni+"%" );
		}
		
		List<UsuarioGrupoResponse> respList = new ArrayList<>();
		for( UsuarioGrupo g : grupos ) {
			UsuarioGrupoResponse resp = usuarioGrupoLoader.novoResponse();
			usuarioGrupoLoader.loadResponse( resp, g );
			respList.add( resp );
		}
		return respList;
	}
	
	public UsuarioGrupoResponse get( Long id ) throws ServiceException {
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( id );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		UsuarioGrupo grupo = grupoOp.get();
		
		UsuarioGrupoResponse resp = usuarioGrupoLoader.novoResponse();
		usuarioGrupoLoader.loadResponse( resp, grupo );
		return resp;
	}
	
	public UsuarioGrupoDetalhesLoadResponse getDetalhesLoad( Long id ) throws ServiceException {
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( id );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		UsuarioGrupo grupo = grupoOp.get();
		List<Acesso> acessos = acessoRepository.buscaPorGrupo( grupo.getId() );
		
		UsuarioGrupoResponse grupoResp = usuarioGrupoLoader.novoResponse();
		usuarioGrupoLoader.loadResponse( grupoResp, grupo );
				
		List<AcessoResponse> acessosResp = new ArrayList<>();
		for( Acesso a : acessos ) {
			Recurso recurso = a.getRecurso();
			
			AcessoResponse aresp = acessoLoader.novoAcessoResponse( grupo, recurso );
			acessoLoader.loadResponse( aresp, a );
			
			acessosResp.add( aresp );
		}
		
		UsuarioGrupoDetalhesLoadResponse resp = usuarioGrupoLoader.novoDetalhesResponse( grupoResp, acessosResp );
		usuarioGrupoLoader.loadDetalhesResponse( resp, grupo ); 
		return resp;
	}
	
	public UsuarioGrupoEditLoadResponse getEditLoad( Long id ) throws ServiceException {
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( id );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		UsuarioGrupo grupo = grupoOp.get();
		List<Acesso> acessos = acessoRepository.buscaPorGrupo( grupo.getId() );
		
		UsuarioGrupoResponse grupoResp = usuarioGrupoLoader.novoResponse();
		usuarioGrupoLoader.loadResponse( grupoResp, grupo );
				
		List<AcessoResponse> acessosResp = new ArrayList<>();
		for( Acesso a : acessos ) {
			Recurso recurso = a.getRecurso();
			
			AcessoResponse aresp = acessoLoader.novoAcessoResponse( grupo, recurso );
			acessoLoader.loadResponse( aresp, a );
			
			acessosResp.add( aresp );
		}
		
		UsuarioGrupoEditLoadResponse resp = usuarioGrupoLoader.novoEditResponse( grupoResp, acessosResp );
		usuarioGrupoLoader.loadEditResponse( resp ); 
		return resp;
	}
		
	public void deleta( Long id ) throws ServiceException {
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( id );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		usuarioGrupoRepository.deleteById( id ); 
	}
	
}
