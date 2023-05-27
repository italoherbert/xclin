package italo.xclin.loader;

import org.springframework.stereotype.Component;

import italo.xclin.exception.LoaderException;
import italo.xclin.model.Recurso;
import italo.xclin.model.request.save.RecursoSaveRequest;
import italo.xclin.model.response.RecursoResponse;

@Component
public class RecursoLoader {
	
	public void loadBean( Recurso r, RecursoSaveRequest request ) {
		r.setNome( request.getNome() ) ;
	}
	
	public void loadResponse( RecursoResponse resp, Recurso r ) throws LoaderException {
		resp.setId( r.getId() );
		resp.setNome( r.getNome() );
	}
							
	public Recurso novoBean() {
		return new Recurso();
	}
	
	public RecursoResponse novoResponse() {
		return new RecursoResponse();
	}

}
