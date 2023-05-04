package italo.scm.loader;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Acesso;
import italo.scm.model.Recurso;
import italo.scm.model.UsuarioGrupo;
import italo.scm.model.request.save.AcessoSaveRequest;
import italo.scm.model.response.AcessoResponse;

@Component
public class AcessoLoader {

	public void loadBean( Acesso a, AcessoSaveRequest request ) throws LoaderException {
		a.setLeitura( request.isLeitura() );
		a.setEscrita( request.isEscrita() );
		a.setRemocao( request.isRemocao() );
	}
	
	public void loadGetResponse( AcessoResponse resp, Acesso a ) throws LoaderException {
		resp.setLeitura( a.isLeitura() );
		resp.setEscrita( a.isEscrita() );
		resp.setRemocao( a.isRemocao() );
	}
	
	public Acesso novoBean( UsuarioGrupo g, Recurso r ) {
		Acesso a = new Acesso();
		a.setGrupo( g );
		a.setRecurso( r );		
		return a;
	}
	
	public AcessoResponse novoAcessoResponse( UsuarioGrupo g, Recurso r ) {
		AcessoResponse resp = new AcessoResponse();
		resp.setGrupoId( g.getId() );
		resp.setRecursoId( r.getId() );
		resp.setGrupoNome( g.getNome() );
		resp.setRecursoNome( r.getNome() ); 
		return resp;
	}
	
}
