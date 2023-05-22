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
import italo.scm.loader.UsuarioLoader;
import italo.scm.model.Usuario;
import italo.scm.model.request.filtro.UsuarioFiltroRequest;
import italo.scm.model.request.save.UsuarioSaveRequest;
import italo.scm.model.request.save.UsuarioSenhaSaveRequest;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.edit.UsuarioEditLoadResponse;
import italo.scm.model.response.load.reg.UsuarioRegLoadResponse;
import italo.scm.repository.UsuarioRepository;
import italo.scm.service.shared.UsuarioSharedService;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	@Autowired
	private UsuarioLoader usuarioLoader;
	
	@Autowired
	private UsuarioSharedService usuarioSharedService;
	
	@Autowired
	private UsuarioPerfilEnumManager usuarioPerfilEnumManager;
		
	@Transactional
	public void registra( Long logadoUID, UsuarioSaveRequest request ) throws ServiceException {
		boolean existe = usuarioRepository.existePorUsername( request.getUsername() );
		if ( existe )
			throw new ServiceException( Erro.USERNAME_NAO_DISPONIVEL, request.getUsername() );
		
		UsuarioPerfil perfil = usuarioPerfilEnumManager.getEnum( request.getPerfil() ); 
				
		Optional<Usuario> uop = usuarioRepository.findById( logadoUID );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario usuarioLogado = uop.get();
		
		Usuario u = usuarioLoader.novoBean( usuarioLogado );
		usuarioLoader.loadBean( u, request );
		
		usuarioRepository.save( u );			
		usuarioSharedService.vinculaGrupo( usuarioLogado, perfil );						
	}
	
	public void altera( Long usuarioId, UsuarioSaveRequest request ) throws ServiceException {
		Optional<Usuario> uop = usuarioRepository.findById( usuarioId );
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
	
	public void alteraSenhaPorLogadoUID( Long logadoUID, UsuarioSenhaSaveRequest request ) throws ServiceException {
		Optional<Usuario> uop = usuarioRepository.findById( logadoUID );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.USUARIO_NAO_ENCONTRADO );
		
		Usuario u = uop.get();
		
		usuarioLoader.loadBeanSenha( u, request );
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
	
	public UsuarioRegLoadResponse getRegLoad() throws ServiceException {
		UsuarioRegLoadResponse resp = usuarioLoader.novoUsuarioRegResponse();
		usuarioLoader.loadRegResponse( resp );
		return resp;
	}
	
	public UsuarioEditLoadResponse getEditLoad( Long uid ) throws ServiceException {
		UsuarioResponse uresp = this.get( uid );
		
		UsuarioEditLoadResponse resp = usuarioLoader.novoUsuarioEditResponse( uresp );
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
