package italo.xclin.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.UsuarioPerfilEnumManager;
import italo.xclin.enums.tipos.UsuarioPerfil;
import italo.xclin.exception.LoaderException;
import italo.xclin.logica.HashUtil;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.save.UsuarioSaveRequest;
import italo.xclin.model.request.save.UsuarioSenhaSaveRequest;
import italo.xclin.model.response.TipoResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.edit.UsuarioEditLoadResponse;
import italo.xclin.model.response.load.reg.UsuarioRegLoadResponse;

@Component
public class UsuarioLoader {

	@Autowired
	private HashUtil hashUtil;
	
	@Autowired
	private UsuarioPerfilEnumManager usuarioPerfilEnumManager;
	
	public void loadBean( Usuario u, UsuarioSaveRequest request ) {
		u.setUsername( request.getUsername() );
		u.setPerfil( usuarioPerfilEnumManager.getEnum( request.getPerfil() ) );
		if ( !request.isIgnorarSenha() )
			u.setSenha( hashUtil.geraHash( request.getSenha() ) ); 
	}
	
	public void loadBeanSenha( Usuario u, UsuarioSenhaSaveRequest request ) {
		u.setSenha( hashUtil.geraHash( request.getSenha() ) ); 
	}
		
	public void loadResponse( UsuarioResponse resp, Usuario u ) throws LoaderException {
		resp.setId( u.getId() );
		resp.setUsername( u.getUsername() ); 
		resp.setPerfil( u.getPerfil() );
		resp.setPerfilLabel( u.getPerfil().label() ); 
	}
	
	public void loadRegResponse( UsuarioRegLoadResponse resp ) throws LoaderException {
		List<TipoResponse> usuarios = new ArrayList<>();
		usuarios.add( usuarioPerfilEnumManager.tipoResponse( UsuarioPerfil.RAIZ ) );
		usuarios.add( usuarioPerfilEnumManager.tipoResponse( UsuarioPerfil.ADMIN ) );
		resp.setPerfis( usuarios ); 
	}
	
	public void loadEditResponse( UsuarioEditLoadResponse resp ) throws LoaderException {
		List<TipoResponse> usuarios = new ArrayList<>();
		usuarios.add( usuarioPerfilEnumManager.tipoResponse( UsuarioPerfil.RAIZ ) );
		usuarios.add( usuarioPerfilEnumManager.tipoResponse( UsuarioPerfil.ADMIN ) );
		resp.setPerfis( usuarios ); 
	}
		
	public Usuario novoBean( Usuario criador ) {
		Usuario usuario = new Usuario();
		usuario.setCriador( criador ); 
		return usuario;
	}
	
	public UsuarioResponse novoResponse() {
		return new UsuarioResponse();
	}
	
	public UsuarioRegLoadResponse novoUsuarioRegResponse() {
		return new UsuarioRegLoadResponse();
	}
	
	public UsuarioEditLoadResponse novoUsuarioEditResponse( UsuarioResponse uresp ) {
		UsuarioEditLoadResponse resp = new UsuarioEditLoadResponse();
		resp.setUsuario( uresp ); 
		return resp;
	}
	
}
