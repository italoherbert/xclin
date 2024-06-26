package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.enums.UsuarioPerfilEnumManager;
import italo.xclin.enums.tipos.UsuarioPerfil;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.UsuarioLoader;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.filtro.UsuarioFiltroRequest;
import italo.xclin.model.request.save.UsuarioSaveRequest;
import italo.xclin.model.request.save.UsuarioSenhaSaveRequest;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.edit.UsuarioEditLoadResponse;
import italo.xclin.model.response.load.reg.UsuarioRegLoadResponse;
import italo.xclin.repository.UsuarioRepository;
import italo.xclin.service.shared.UsuarioSharedService;
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
	
		usuarioSharedService.vinculaGrupo( u, perfil );						
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
	
	public ListaResponse listaPorClinica( Long clinicaId ) throws ServiceException {
		List<Usuario> usuarios = usuarioRepository.listaPorClinica( clinicaId );
				
		List<Long> ids = new ArrayList<>();
		List<String> nomes = new ArrayList<>();
		
		for( Usuario u : usuarios ) {
			ids.add( u.getId() );
			nomes.add( u.getUsername() );
		}
		
		return new ListaResponse( ids, nomes );
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
		
		Usuario u = uop.get();
		if ( u.getPerfil() != UsuarioPerfil.RAIZ && 
				u.getPerfil() != UsuarioPerfil.ADMIN && 
				u.getPerfil() != UsuarioPerfil.SUPORTE )
			throw new ServiceException( Erro.USUARIO_NAO_DELETADO_POR_PERFIL );
		
		usuarioRepository.deleteById( id );
	}
		
}
