package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.UsuarioGrupoLoader;
import italo.xclin.loader.UsuarioLoader;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioGrupo;
import italo.xclin.model.UsuarioGrupoVinculo;
import italo.xclin.model.request.save.lista.UsuarioGrupoVinculoListaSaveRequest;
import italo.xclin.model.response.UsuarioGrupoResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.vinculos.UsuarioGrupoVinculosLoadResponse;
import italo.xclin.repository.UsuarioGrupoRepository;
import italo.xclin.repository.UsuarioGrupoVinculoRepository;
import italo.xclin.repository.UsuarioRepository;
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
	public void salva( Long usuarioId, UsuarioGrupoVinculoListaSaveRequest request ) throws ServiceException {
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
	
	public UsuarioGrupoVinculosLoadResponse vinculados( Long id ) throws ServiceException {
		Optional<Usuario> uop = usuarioRepository.findById( id );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.USUARIO_NAO_ENCONTRADO );
		
		Usuario usuario = uop.get();		
		List<UsuarioGrupoVinculo> vinculosAdicionados = usuario.getUsuarioGrupoVinculos();

		List<UsuarioGrupoResponse> gruposResp = new ArrayList<>();
		List<Boolean> vinculadosFlagsResp = new ArrayList<>();
		
		List<UsuarioGrupo> gruposTodos = usuarioGrupoRepository.findAll();
		for( UsuarioGrupo grupo : gruposTodos ) {
			boolean adicionado = false;
						
			int size = vinculosAdicionados.size();
			for( int i = 0; !adicionado && i < size; i++ ) {
				UsuarioGrupoVinculo vadd = vinculosAdicionados.get( i );
				UsuarioGrupo vgrupo = vadd.getGrupo();
				if ( vgrupo.getId() == grupo.getId() )
					adicionado = true;;
			}
						
			UsuarioGrupoResponse grupoResp = usuarioGrupoLoader.novoResponse();
			usuarioGrupoLoader.loadResponse( grupoResp, grupo );
			
			gruposResp.add( grupoResp );
			vinculadosFlagsResp.add( adicionado );
			System.out.println( grupo.getNome() );
		}						
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, usuario );
		
		UsuarioGrupoVinculosLoadResponse resp = new UsuarioGrupoVinculosLoadResponse();
		resp.setUsuario( uresp );
		resp.setGrupos( gruposResp );
		resp.setVinculadosFlags( vinculadosFlagsResp  ); 
		return resp;
	}
	
}
