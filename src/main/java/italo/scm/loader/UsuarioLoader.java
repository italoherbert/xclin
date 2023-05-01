package italo.scm.loader;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.logica.HashUtil;
import italo.scm.model.Usuario;
import italo.scm.model.request.UsuarioRequest;
import italo.scm.model.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioLoader {

	private HashUtil hashUtil;
	
	public void loadBean( Usuario u, UsuarioRequest request ) {
		u.setUsername( request.getUsername() );
		u.setSenha( hashUtil.geraHash( request.getSenha() ) ); 
	}
	
	public void loadResponse( UsuarioResponse resp, Usuario u ) throws LoaderException {
		resp.setId( u.getId() );
		resp.setUsername( u.getUsername() ); 
	}
	
	public Usuario novoBean() {
		return new Usuario();
	}
	
	public UsuarioResponse novoResponse() {
		return new UsuarioResponse();
	}
	
}
