package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.UsuarioGrupoLoader;
import italo.scm.loader.UsuarioLoader;
import italo.scm.model.Usuario;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.UsuarioGrupoVinculo;
import italo.scm.model.request.save.UsuarioGrupoVinculadoListSaveRequest;
import italo.scm.model.response.UsuarioGrupoResponse;
import italo.scm.model.response.UsuarioGrupoVinculadosResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.repository.UsuarioGrupoRepository;
import italo.scm.repository.UsuarioGrupoVinculoRepository;
import italo.scm.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioGrupoVinculoService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioGrupoRepository usuarioGrupoRepository;
	
	@Autowired
	private UsuarioGrupoVinculoRepository usuarioGrupoVinculoRepository;
	
	@Autowired
	private UsuarioLoader usuarioLoader;
	
	@Autowired
	private UsuarioGrupoLoader usuarioGrupoLoader;
		 
	@Transactional
	public void salva( Long usuarioId, UsuarioGrupoVinculadoListSaveRequest request ) throws ServiceException {
		Optional<Usuario> usuarioOp = usuarioRepository.findById( usuarioId );
		if ( !usuarioOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_NAO_ENCONTRADO );
		
		Usuario usuario = usuarioOp.get();		

		usuarioGrupoVinculoRepository.deleteByUsuarioId( usuario.getId() );
		
		List<Long> grupoIDs = request.getGrupos();
						
		for( Long gid : grupoIDs ) {
			Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( gid );
			if ( !grupoOp.isPresent() )
				throw new ServiceException( Erro.VINCULO_USUARIO_GRUPO_NAO_ENCONTRADO, ""+gid );
			
			UsuarioGrupo grupo = grupoOp.get();
			
			UsuarioGrupoVinculo vinculo = new UsuarioGrupoVinculo();
			vinculo.setUsuario( usuario ); 
			vinculo.setGrupo( grupo );
			
			usuarioGrupoVinculoRepository.save( vinculo );
		}
	}
	
	public UsuarioGrupoVinculadosResponse vinculados( Long id ) throws ServiceException {
		Optional<Usuario> uop = usuarioRepository.findById( id );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.USUARIO_NAO_ENCONTRADO );
		
		Usuario usuario = uop.get();		
		List<UsuarioGrupoVinculo> vinculosAdicionados = usuario.getUsuarioGrupoVinculos();

		List<UsuarioGrupoResponse> gruposResp = new ArrayList<>();
		List<Boolean> vinculadosFlagsResp = new ArrayList<>();
		
		List<UsuarioGrupoVinculo> vinculosTodos = usuarioGrupoVinculoRepository.findAll();
		for( UsuarioGrupoVinculo vinculo : vinculosTodos ) {
			boolean adicionado = false;
			
			int size = vinculosAdicionados.size();
			for( int i = 0; !adicionado && i < size; i++ ) {
				UsuarioGrupoVinculo vadd = vinculosAdicionados.get( i );
				if ( vadd.getId() == vinculo.getId() )
					adicionado = true;;
			}
			
			UsuarioGrupo grupo = vinculo.getGrupo();
			
			UsuarioGrupoResponse grupoResp = usuarioGrupoLoader.novoResponse();
			usuarioGrupoLoader.loadResponse( grupoResp, grupo );
			
			gruposResp.add( grupoResp );
			vinculadosFlagsResp.add( adicionado );
		}						
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, usuario );
		
		UsuarioGrupoVinculadosResponse resp = new UsuarioGrupoVinculadosResponse();
		resp.setUsuario( uresp );
		resp.setGrupos( gruposResp );
		resp.setVinculadosFlags( vinculadosFlagsResp  ); 
		return resp;
	}
	
}
