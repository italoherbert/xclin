package italo.scm.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Usuario;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.UsuarioGrupoVinculo;
import italo.scm.model.request.save.UsuarioGrupoVinculoSaveRequest;
import italo.scm.model.response.UsuarioGrupoResponse;
import italo.scm.model.response.UsuarioGrupoVinculoResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.edit.UsuarioGrupoVinculosEditResponse;

@Component
public class UsuarioGrupoVinculoLoader {

	public void loadBean( UsuarioGrupoVinculo v, UsuarioGrupoVinculoSaveRequest request ) throws LoaderException {
		
	}
	
	public void loadResponse( UsuarioGrupoVinculoResponse resp, UsuarioGrupoVinculo v ) {

	}
	
	public void loadVinculosEdit( UsuarioGrupoVinculosEditResponse resp ) throws LoaderException {
		
	}
	
	public UsuarioGrupoVinculo novoBean( Usuario u, UsuarioGrupo g ) {
		UsuarioGrupoVinculo v = new UsuarioGrupoVinculo();
		v.setUsuario( u );
		v.setGrupo( g );
		return v;
	}
	
	public UsuarioGrupoVinculoResponse novoResponse( UsuarioGrupoResponse gresp ) {
		UsuarioGrupoVinculoResponse resp = new UsuarioGrupoVinculoResponse();
		resp.setUsuario( null );
		resp.setGrupo( gresp );
		resp.setAdicionado( false );
		return resp;
	}
	
	public UsuarioGrupoVinculoResponse novoResponse( UsuarioResponse uresp, UsuarioGrupoResponse gresp ) {
		UsuarioGrupoVinculoResponse resp = new UsuarioGrupoVinculoResponse();
		resp.setUsuario( uresp );
		resp.setGrupo( gresp );
		resp.setAdicionado( true );
		return resp;
	}
	
	public UsuarioGrupoVinculosEditResponse novoVinculosEditResponse( UsuarioResponse usuario, List<UsuarioGrupoVinculoResponse> vinculos ) {
		UsuarioGrupoVinculosEditResponse resp = new UsuarioGrupoVinculosEditResponse();
		resp.setUsuario( usuario );
		resp.setVinculos( vinculos ); 
		return resp;
	}		
}

