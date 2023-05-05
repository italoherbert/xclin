package italo.scm.loader;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Recurso;
import italo.scm.model.request.save.RecursoSaveRequest;
import italo.scm.model.response.RecursoResponse;

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
