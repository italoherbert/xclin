package italo.xclin.loader;

import org.springframework.stereotype.Component;

import italo.xclin.model.Especialidade;
import italo.xclin.model.request.save.EspecialidadeSaveRequest;
import italo.xclin.model.response.EspecialidadeResponse;

@Component
public class EspecialidadeLoader {
	
	public void loadBean( Especialidade e, EspecialidadeSaveRequest request ) {
		e.setNome( request.getNome() ) ;
	}
	
	public void loadResponse( EspecialidadeResponse resp, Especialidade e ) {
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

