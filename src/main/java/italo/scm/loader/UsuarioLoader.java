package italo.scm.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.enums.UsuarioPerfilEnumManager;
import italo.scm.enums.tipos.UsuarioPerfil;
import italo.scm.exception.LoaderException;
import italo.scm.logica.HashUtil;
import italo.scm.model.Usuario;
import italo.scm.model.request.save.UsuarioSaveRequest;
import italo.scm.model.request.save.UsuarioSenhaSaveRequest;
import italo.scm.model.response.TipoResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.edit.UsuarioEditLoadResponse;
import italo.scm.model.response.load.reg.UsuarioRegLoadResponse;

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
