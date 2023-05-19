package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.AcessoLoader;
import italo.scm.model.Acesso;
import italo.scm.model.Recurso;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.request.save.AcessoListaSaveRequest;
import italo.scm.model.request.save.AcessoSaveRequest;
import italo.scm.model.response.AcessoResponse;
import italo.scm.repository.AcessoRepository;
import italo.scm.repository.RecursoRepository;
import italo.scm.repository.UsuarioGrupoRepository;
import jakarta.transaction.Transactional;

@Service
public class AcessoService {

	@Autowired
	private UsuarioGrupoRepository usuarioGrupoRepository;
	
	@Autowired
	private RecursoRepository recursoRepository;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@Autowired
	private AcessoLoader acessoLoader;
	
	public List<AcessoResponse> sincronizaAcessos( Long id ) throws ServiceException {
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( id );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		UsuarioGrupo grupo = grupoOp.get();
		List<Acesso> acessos = grupo.getAcessos();
		int asize = acessos.size();
		
		List<AcessoResponse> acessosResp = new ArrayList<>();
		
		List<Recurso> recursos = recursoRepository.findAllByOrderByIdDesc();				
		for( Recurso recurso : recursos ) {	
			Acesso acesso = null;
			for( int i = 0; acesso == null && i < asize; i++ ) {
				Acesso a = acessos.get( i );
				Recurso r = a.getRecurso();
				if ( recurso.getNome().equalsIgnoreCase( r.getNome() ) )
					acesso = a;				
			}
						
			if ( acesso == null )
				acesso = acessoLoader.novoBean( grupo, recurso );

			AcessoResponse aresp = acessoLoader.novoAcessoResponse( grupo, recurso );
			acessoLoader.loadResponse( aresp, acesso );
			acessosResp.add( aresp );
		}		
		
		return acessosResp;
	}
	
	@Transactional
	public void salvaAcessos( Long grupoId, AcessoListaSaveRequest request ) throws ServiceException {
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( grupoId );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO );
		
		UsuarioGrupo grupo = grupoOp.get();
		
		List<AcessoSaveRequest> acessoListaRequest = request.getAcessos();
		for( AcessoSaveRequest acessoRequest : acessoListaRequest ) {
			Long recursoId = acessoRequest.getRecursoId();
			
			Optional<Recurso> recursoOp = recursoRepository.findById( recursoId );
			if ( !recursoOp.isPresent() )
				throw new ServiceException( Erro.ACESSO_RECURSO_NAO_ENCONTRADO, ""+recursoId );
			
			Recurso recurso = recursoOp.get();
			
			Acesso acesso;
			
			Optional<Acesso> acessoOp = acessoRepository.busca( grupoId, recursoId );
			if ( acessoOp.isPresent() ) {
				acesso = acessoOp.get();
			} else {
				acesso = acessoLoader.novoBean( grupo, recurso );
			}
			
			acessoLoader.loadBean( acesso, acessoRequest );
			acessoRepository.save( acesso );
		}
	}
	
}
