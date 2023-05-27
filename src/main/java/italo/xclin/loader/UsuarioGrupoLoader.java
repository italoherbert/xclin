package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.exception.LoaderException;
import italo.xclin.model.UsuarioGrupo;
import italo.xclin.model.request.save.UsuarioGrupoSaveRequest;
import italo.xclin.model.response.AcessoResponse;
import italo.xclin.model.response.UsuarioGrupoResponse;
import italo.xclin.model.response.load.detalhes.UsuarioGrupoDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.UsuarioGrupoEditLoadResponse;

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

