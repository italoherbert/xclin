package italo.xclin.loader;

import org.springframework.stereotype.Component;

import italo.xclin.model.Consulta;
import italo.xclin.model.ConsultaEspecialidadeVinculo;
import italo.xclin.model.Especialidade;
import italo.xclin.model.request.save.ConsultaSaveRequest;
import italo.xclin.model.response.ConsultaResponse;

@Component
public class ConsultaLoader {

	public void loadBean( Consulta c, ConsultaSaveRequest request ) {
		c.setValor( request.getValor() );
		c.setConcluida( false );
	}
	
	public void loadResponse( ConsultaResponse resp, Consulta c ) {
		resp.setId( c.getId() );
		resp.setValor( c.getValor() );
		resp.setConcluida( c.isConcluida() ); 
	}
		
	public Consulta novoBean( Especialidade especialidade ) {
		Consulta consulta = new Consulta();

		ConsultaEspecialidadeVinculo vinculo = new ConsultaEspecialidadeVinculo();
		vinculo.setConsulta( consulta );
		vinculo.setEspecialidade( especialidade );

		consulta.setConsultaEspecialidadeVinculo( vinculo );

		return consulta;
	}
	
	public ConsultaResponse novoResponse( Especialidade especialidade ) {
		ConsultaResponse resp = new ConsultaResponse();
		resp.setEspecialidadeId( especialidade.getId() );
		resp.setEspecialidadeNome( especialidade.getNome() ); 
		return resp;
	}
		
}
