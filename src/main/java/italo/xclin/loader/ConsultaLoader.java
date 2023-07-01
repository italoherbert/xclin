package italo.xclin.loader;

import org.springframework.stereotype.Component;

import italo.xclin.model.Consulta;
import italo.xclin.model.request.save.ConsultaSaveRequest;
import italo.xclin.model.response.ConsultaResponse;

@Component
public class ConsultaLoader {

	public void loadBean( Consulta c, ConsultaSaveRequest request ) {
		c.setRetorno( request.isRetorno() );
		c.setValor( request.getValor() );			
		c.setPaga( false );	
	}
	
	public void loadResponse( ConsultaResponse resp, Consulta c ) {
		resp.setId( c.getId() );
		resp.setPaga( c.isPaga() );
		resp.setRetorno( c.isRetorno() );
		resp.setValor( c.getValor() );
	}
		
	public Consulta novoBean() {
		return new Consulta();
	}
	
	public ConsultaResponse novoResponse() {
		return new ConsultaResponse();
	}
		
}
