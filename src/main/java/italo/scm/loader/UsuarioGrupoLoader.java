package italo.scm.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.request.save.UsuarioGrupoSaveRequest;
import italo.scm.model.response.AcessoResponse;
import italo.scm.model.response.UsuarioGrupoResponse;
import italo.scm.model.response.detalhes.UsuarioGrupoDetalhesResponse;
import italo.scm.model.response.edit.UsuarioGrupoEditResponse;
import italo.scm.model.response.reg.UsuarioGrupoRegResponse;

@Component
public class UsuarioGrupoLoader {
		
	public void loadBean( UsuarioGrupo g, UsuarioGrupoSaveRequest request ) {
		g.setNome( request.getNome() ) ;
	}
	
	public void loadGetResponse( UsuarioGrupoResponse resp, UsuarioGrupo g ) throws LoaderException {
		resp.setId( g.getId() );
		resp.setNome( g.getNome() );
	}
		
	public void loadDetalhesResponse( UsuarioGrupoDetalhesResponse resp, UsuarioGrupo g ) throws LoaderException {

	}
	
	public void loadEditResponse( UsuarioGrupoEditResponse resp ) throws LoaderException {

	}
	
	public void loadRegResponse( UsuarioGrupoRegResponse resp ) throws LoaderException {
		
	}
				
	public UsuarioGrupo novoBean() {
		return new UsuarioGrupo();
	}
	
	public UsuarioGrupoResponse novoResponse() {
		return new UsuarioGrupoResponse();
	}
			
	public UsuarioGrupoDetalhesResponse novoDetalhesResponse( 
			UsuarioGrupoResponse gresp, List<AcessoResponse> acessosResp ) {
		UsuarioGrupoDetalhesResponse resp = new UsuarioGrupoDetalhesResponse();
		resp.setGrupo( gresp );
		resp.setAcessos( acessosResp );
		return resp;
	}
	
	public UsuarioGrupoEditResponse novoEditResponse( 
			UsuarioGrupoResponse gresp, List<AcessoResponse> acessosResp ) {
		UsuarioGrupoEditResponse resp = new UsuarioGrupoEditResponse();
		resp.setGrupo( gresp );
		resp.setAcessos( acessosResp );
		return resp;
	}
	
	public UsuarioGrupoRegResponse novoRegResponse() {
		return new UsuarioGrupoRegResponse();
	}
	
}

