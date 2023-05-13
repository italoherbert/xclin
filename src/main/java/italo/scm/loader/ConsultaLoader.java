package italo.scm.loader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.enums.tipos.ConsultaStatus;
import italo.scm.exception.ConverterException;
import italo.scm.exception.LoaderException;
import italo.scm.logica.Converter;
import italo.scm.model.Consulta;
import italo.scm.model.Paciente;
import italo.scm.model.Profissional;
import italo.scm.model.request.save.ConsultaSaveRequest;
import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.load.ConsultaAgendaTelaLoadResponse;

@Component
public class ConsultaLoader {

	@Autowired
	private Converter converter;
	
	public void loadBean( Consulta c, ConsultaSaveRequest request ) throws LoaderException {
		c.setDescricao( request.getDescricao() );
		c.setRetorno( request.isRetorno() );
		c.setValor( request.getValor() );
		c.setTempoEstimado( request.getTempoEstimado() );
		
		try {
			c.setDataConsulta( converter.stringToData( request.getDataConsulta() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		} 
		
		c.setPaga( false );
		c.setStatus( ConsultaStatus.REGISTRADA ); 
	}
	
	public void loadResponse( ConsultaResponse resp, Consulta c ) {
		resp.setId( c.getId() );
		resp.setDescricao( c.getDescricao() );
		resp.setRetorno( c.isRetorno() );
		resp.setPaga( c.isPaga() );
		resp.setStatus( c.getStatus().name() );
		resp.setTempoEstimado( c.getTempoEstimado() );
		resp.setDataConsulta( converter.dataHoraToString( c.getDataConsulta() ) ); 
		resp.setValor( c.getValor() ); 
	}
	
	public Consulta novoBean( Profissional profissional, Paciente paciente ) {
		Consulta consulta = new Consulta();
		consulta.setProfissional( profissional );
		consulta.setPaciente( paciente ); 
		return consulta;
	}
	
	public ConsultaResponse novoResponse( Paciente p ) {
		ConsultaResponse resp = new ConsultaResponse();
		resp.setPacienteId( p.getId() );
		resp.setPacienteNome( p.getNome() ); 
		return resp;
	}
	
	public ConsultaAgendaTelaLoadResponse novoAgendaTelaResponse( 
			List<Long> clinicasIDs, 
			List<String> clinicasNomes ) {
		
		ConsultaAgendaTelaLoadResponse resp = new ConsultaAgendaTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
}
