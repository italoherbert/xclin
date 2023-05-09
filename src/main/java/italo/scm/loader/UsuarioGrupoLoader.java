package italo.scm.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.request.save.UsuarioGrupoSaveRequest;
import italo.scm.model.response.AcessoResponse;
import italo.scm.model.response.UsuarioGrupoResponse;
import italo.scm.model.response.load.UsuarioGrupoDetalhesLoadResponse;
import italo.scm.model.response.load.UsuarioGrupoEditLoadResponse;

@Component
public class UsuarioGrupoLoader {
		
	public void loadBean( UsuarioGrupo g, UsuarioGrupoSaveRequest request ) {
		g.setNome( request.getNome() ) ;
	}
	
	public void loadResponse( UsuarioGrupoResponse resp, UsuarioGrupo g ) throws LoaderException {
		resp.setId( g.getId() );
		resp.setNome( g.getNome() );
	}
		
	public void loadDetalhesResponse( UsuarioGrupoDetalhesLoadResponse resp, UsuarioGrupo g ) throws LoaderException {

	}
	
	public void loadEditResponse( UsuarioGrupoEditLoadResponse resp ) throws LoaderException {

	}
					
	public UsuarioGrupo novoBean() {
		return new UsuarioGrupo();
	}
	
	public UsuarioGrupoResponse novoResponse() {
		return new UsuarioGrupoResponse();
	}
			
	public UsuarioGrupoDetalhesLoadResponse novoDetalhesResponse( 
			UsuarioGrupoResponse gresp, List<AcessoResponse> acessosResp ) {
		UsuarioGrupoDetalhesLoadResponse resp = new UsuarioGrupoDetalhesLoadResponse();
		resp.setGrupo( gresp );
		resp.setAcessos( acessosResp );
		return resp;
	}
	
	public UsuarioGrupoEditLoadResponse novoEditResponse( 
			UsuarioGrupoResponse gresp, List<AcessoResponse> acessosResp ) {
		UsuarioGrupoEditLoadResponse resp = new UsuarioGrupoEditLoadResponse();
		resp.setGrupo( gresp );
		resp.setAcessos( acessosResp );
		return resp;
	}
		
}

