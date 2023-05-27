package italo.xclin.loader;

import org.springframework.stereotype.Component;

import italo.xclin.exception.LoaderException;
import italo.xclin.model.Acesso;
import italo.xclin.model.Recurso;
import italo.xclin.model.UsuarioGrupo;
import italo.xclin.model.request.save.AcessoSaveRequest;
import italo.xclin.model.response.AcessoResponse;

@Component
public class AcessoLoader {

	public void loadBean( Acesso a, AcessoSaveRequest request ) throws LoaderException {
		a.setLeitura( request.isLeitura() );
		a.setEscrita( request.isEscrita() );
		a.setRemocao( request.isRemocao() );
	}
			
	public void loadResponse( AcessoResponse resp, Acesso a ) throws LoaderException {
		resp.setLeitura( a.isLeitura() );
		resp.setEscrita( a.isEscrita() );
		resp.setRemocao( a.isRemocao() );
	}
	
	public Acesso novoBean( UsuarioGrupo g, Recurso r ) {
		Acesso a = new Acesso();
		a.setGrupo( g );
		a.setRecurso( r );		
		a.setLeitura( false );
		a.setEscrita( false );
		a.setRemocao( false );
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
