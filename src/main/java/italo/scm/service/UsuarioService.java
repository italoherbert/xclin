package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.enums.UsuarioPerfilEnumManager;
import italo.scm.enums.tipos.UsuarioPerfil;
import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.UsuarioGrupoVinculoLoader;
import italo.scm.loader.UsuarioLoader;
import italo.scm.model.Usuario;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.UsuarioGrupoVinculo;
import italo.scm.model.request.filtro.UsuarioFiltroRequest;
import italo.scm.model.request.save.UsuarioGrupoVinculoListaSaveRequest;
import italo.scm.model.request.save.UsuarioGrupoVinculoSaveRequest;
import italo.scm.model.request.save.UsuarioSaveRequest;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.edit.UsuarioEditResponse;
import italo.scm.model.response.reg.UsuarioRegResponse;
import italo.scm.repository.UsuarioGrupoRepository;
import italo.scm.repository.UsuarioGrupoVinculoRepository;
import italo.scm.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioGrupoRepository usuarioGrupoRepository;
	
	@Autowired
	private UsuarioGrupoVinculoRepository usuarioGrupoVinculoRepository;
	
	@Autowired
	private UsuarioGrupoVinculoRepository usuarioGrupoMapRepository;
	
	@Autowired
	private UsuarioLoader usuarioLoader;
	
	@Autowired
	private UsuarioGrupoVinculoLoader usuarioGrupoVinculoLoader;
	
	@Autowired
	private UsuarioPerfilEnumManager usuarioPerfilEnumManager;

	@Transactional
	public void salvaGrupoVinculos( Long usuarioId, UsuarioGrupoVinculoListaSaveRequest request ) throws ServiceException {
		Optional<Usuario> usuarioOp = usuarioRepository.findById( usuarioId );
		if ( !usuarioOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_NAO_ENCONTRADO );
		
		Usuario usuario = usuarioOp.get();
		
		List<UsuarioGrupoVinculoSaveRequest> vinculoListaRequest = request.getVinculos();
		for( UsuarioGrupoVinculoSaveRequest vinculoRequest : vinculoListaRequest ) {
			Long grupoId = vinculoRequest.getGrupoId();
			
			Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.findById( grupoId );
			if ( !grupoOp.isPresent() )
				throw new ServiceException( Erro.VINCULO_USUARIO_GRUPO_NAO_ENCONTRADO, ""+grupoId );
			
			UsuarioGrupo grupo = grupoOp.get();
			
			UsuarioGrupoVinculo vinculo;
			
			Optional<UsuarioGrupoVinculo> vinculoOp = usuarioGrupoVinculoRepository.busca( usuarioId, grupoId );
			if ( vinculoOp.isPresent() ) {
				vinculo = vinculoOp.get();
			} else {
				vinculo = usuarioGrupoVinculoLoader.novoBean( usuario, grupo );
			}
			
			usuarioGrupoVinculoLoader.loadBean( vinculo, vinculoRequest );
			usuarioGrupoVinculoRepository.save( vinculo );
		}
	}
	
	@Transactional
	public void registra( UsuarioSaveRequest request ) throws ServiceException {
		boolean existe = usuarioRepository.existePorUsername( request.getUsername() );
		if ( existe )
			throw new ServiceException( Erro.USERNAME_NAO_DISPONIVEL, request.getUsername() );
		
		UsuarioPerfil perfil = usuarioPerfilEnumManager.getEnum( request.getPerfil() ); 
		
		Optional<UsuarioGrupo> grupoOp = usuarioGrupoRepository.buscaPorNome( perfil.name() );
		if ( !grupoOp.isPresent() )
			throw new ServiceException( Erro.USUARIO_GRUPO_NAO_ENCONTRADO, request.getPerfil() );
		
		UsuarioGrupo grupo = grupoOp.get();
		
		Usuario u = usuarioLoader.novoBean();
		usuarioLoader.loadBean( u, request );
		
		usuarioRepository.save( u );
			
		UsuarioGrupoVinculo map = new UsuarioGrupoVinculo();
		map.setUsuario( u ); 
		map.setGrupo( grupo );
		
		usuarioGrupoMapRepository.save( map );					
	}
	
	public void altera( Long uid, UsuarioSaveRequest request ) throws ServiceException {
		Optional<Usuario> uop = usuarioRepository.findById( uid );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.USUARIO_NAO_ENCONTRADO );		
		
		Usuario u = uop.get();
		
		String username = request.getUsername();
		if ( !username.equalsIgnoreCase( u.getUsername() ) ) {
			boolean existe = usuarioRepository.existePorUsername( username );
			if ( existe )
				throw new ServiceException( Erro.USUARIO_JA_EXISTE );
		}
		
		usuarioLoader.loadBean( u, request );		
		usuarioRepository.save( u );
	}
	
	public List<UsuarioResponse> filtra( UsuarioFiltroRequest filtro ) throws ServiceException {
		String usernameIni = filtro.getUsernameIni();
		
		List<Usuario> usuarios;
		if( usernameIni.equals( "*" ) )
			usuarios = usuarioRepository.findAll();
		else usuarios = usuarioRepository.buscaPorUsernameIni( usernameIni+"%" );
		
		List<UsuarioResponse> resplist = new ArrayList<>();
		for( Usuario u : usuarios ) {
			UsuarioResponse resp = usuarioLoader.novoResponse();
			usuarioLoader.loadResponse( resp, u ); 
			resplist.add( resp );
		}
		return resplist;
	}
	
	public UsuarioResponse get( Long id ) throws ServiceException {
		Optional<Usuario> uop = usuarioRepository.findById( id );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.USUARIO_NAO_ENCONTRADO );
		
		Usuario u = uop.get();
		
		UsuarioResponse resp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( resp, u );
		return resp;
	}
	
	public UsuarioRegResponse getDadosReg() throws ServiceException {
		UsuarioRegResponse resp = usuarioLoader.novoUsuarioRegResponse();
		usuarioLoader.loadRegResponse( resp );
		return resp;
	}
	
	public UsuarioEditResponse getDadosEdit( Long uid ) throws ServiceException {
		UsuarioResponse uresp = this.get( uid );
		
		UsuarioEditResponse resp = usuarioLoader.novoUsuarioEditResponse( uresp );
		usuarioLoader.loadEditResponse( resp );
		return resp;
	}
	
	public void delete( Long id ) throws ServiceException {
		Optional<Usuario> uop = usuarioRepository.findById( id );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.USUARIO_NAO_ENCONTRADO );
		
		usuarioRepository.deleteById( id );
	}
		
}
