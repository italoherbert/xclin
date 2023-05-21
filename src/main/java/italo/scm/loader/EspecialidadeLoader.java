package italo.scm.loader;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Especialidade;
import italo.scm.model.request.save.EspecialidadeSaveRequest;
import italo.scm.model.response.EspecialidadeResponse;

@Component
public class EspecialidadeLoader {
	
	public void loadBean( Especialidade e, EspecialidadeSaveRequest request ) {
		e.setNome( request.getNome() ) ;
	}
	
	public void loadResponse( EspecialidadeResponse resp, Especialidade e ) throws LoaderException {
		resp.setId( e.getId() );
		resp.setNome( e.getNome() );
	}
							
	public Especialidade novoBean() {
		return new Especialidade();
	}
	
	public EspecialidadeResponse novoResponse() {
		return new EspecialidadeResponse();
	}

}

