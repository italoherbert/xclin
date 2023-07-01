package italo.xclin.loader;

import org.springframework.stereotype.Component;

import italo.xclin.model.Consulta;
import italo.xclin.model.Especialidade;
import italo.xclin.model.request.save.ConsultaSaveRequest;
import italo.xclin.model.response.ConsultaResponse;

@Component
public class ConsultaLoader {

	public void loadBean( Consulta c, ConsultaSaveRequest request ) {
		c.setRetorno( request.isRetorno() );
		c.setValor( request.getValor() );			
	}
	
	public void loadResponse( ConsultaResponse resp, Consulta c ) {
		resp.setId( c.getId() );
		resp.setRetorno( c.isRetorno() );
		resp.setValor( c.getValor() );
	}
		
	public Consulta novoBean( Especialidade especialidade ) {
		Consulta consulta = new Consulta();
		consulta.setEspecialidade( especialidade );
		return consulta;
	}
	
	public ConsultaResponse novoResponse( Especialidade especialidade ) {
		ConsultaResponse resp = new ConsultaResponse();
		resp.setEspecialidadeId( especialidade.getId() );
		resp.setEspecialidadeNome( especialidade.getNome() ); 
		return resp;
	}
		
}
