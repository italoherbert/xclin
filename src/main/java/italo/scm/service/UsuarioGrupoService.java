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
import italo.scm.model.response.detalhes.UsuarioGrupoDetalhesResponse;
import italo.scm.model.response.edit.UsuarioGrupoEditResponse;
import italo.scm.model.response.reg.UsuarioGrupoRegResponse;
import italo.scm.repository.UsuarioGrupoRepository;

@Service
public class UsuarioGrupoService {

	@Autowired
	private UsuarioGrupoRepository usuarioGrupoRepository;
	
	@Autowired
	private UsuarioGrupoLoader usuarioGrupoLoader;
	
	@Autowired
	private AcessoLoader acessoLoader;
		
	public void registra( UsuarioGrupoSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.buscaPorNome( nome );
		if ( grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_JA_EXISTE, nome );
		
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
			usuarioGrupoLoader.loadGetResponse( resp, g );
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
		usuarioGrupoLoader.loadGetResponse( resp, grupo );
		return resp;
	}
	
	public UsuarioGrupoDetalhesResponse getDetalhes( Long id ) throws ServiceException {
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( id );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		UsuarioGrupo grupo = grupoOp.get();
		List<Acesso> acessos = grupo.getAcessos(); 
		
		UsuarioGrupoResponse grupoResp = usuarioGrupoLoader.novoResponse();
		usuarioGrupoLoader.loadGetResponse( grupoResp, grupo );
				
		List<AcessoResponse> acessosResp = new ArrayList<>();
		for( Acesso a : acessos ) {
			Recurso recurso = a.getRecurso();
			
			AcessoResponse aresp = acessoLoader.novoAcessoResponse( grupo, recurso );
			acessoLoader.loadGetResponse( aresp, a );
			
			acessosResp.add( aresp );
		}
		
		UsuarioGrupoDetalhesResponse resp = usuarioGrupoLoader.novoDetalhesResponse( grupoResp, acessosResp );
		usuarioGrupoLoader.loadDetalhesResponse( resp, grupo ); 
		return resp;
	}
	
	public UsuarioGrupoEditResponse getEdit( Long id ) throws ServiceException {
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( id );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		UsuarioGrupo grupo = grupoOp.get();
		List<Acesso> acessos = grupo.getAcessos(); 
		
		UsuarioGrupoResponse grupoResp = usuarioGrupoLoader.novoResponse();
		usuarioGrupoLoader.loadGetResponse( grupoResp, grupo );
				
		List<AcessoResponse> acessosResp = new ArrayList<>();
		for( Acesso a : acessos ) {
			Recurso recurso = a.getRecurso();
			
			AcessoResponse aresp = acessoLoader.novoAcessoResponse( grupo, recurso );
			acessoLoader.loadGetResponse( aresp, a );
			
			acessosResp.add( aresp );
		}
		
		UsuarioGrupoEditResponse resp = usuarioGrupoLoader.novoEditResponse( grupoResp, acessosResp );
		usuarioGrupoLoader.loadEditResponse( resp ); 
		return resp;
	}
	
	public UsuarioGrupoRegResponse getReg() throws ServiceException {		
		UsuarioGrupoRegResponse resp = usuarioGrupoLoader.novoRegResponse();
		usuarioGrupoLoader.loadRegResponse( resp );
		return resp;
	}
	
	public void deleta( Long id ) throws ServiceException {
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( id );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		usuarioGrupoRepository.deleteById( id ); 
	}
	
}
