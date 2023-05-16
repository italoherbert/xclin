package italo.scm.loader;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.enums.TurnoEnumManager;
import italo.scm.enums.tipos.ConsultaStatus;
import italo.scm.exception.ConverterException;
import italo.scm.exception.LoaderException;
import italo.scm.logica.Converter;
import italo.scm.model.Clinica;
import italo.scm.model.Consulta;
import italo.scm.model.Paciente;
import italo.scm.model.Profissional;
import italo.scm.model.request.save.ConsultaRemarcarSaveRequest;
import italo.scm.model.request.save.ConsultaSaveRequest;
import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.load.ConsultaAgendaTelaLoadResponse;
import italo.scm.model.response.load.ConsultaRegLoadResponse;

@Component
public class ConsultaLoader {

	@Autowired
	private Converter converter;
	
	@Autowired
	private TurnoEnumManager turnoEnumManager;
	
	public void loadBean( Consulta c, ConsultaRemarcarSaveRequest request ) throws LoaderException {
		c.setDataAgendamento( new Date() ); 
		c.setTurno( turnoEnumManager.getEnum( request.getTurno() ) ); 
		try {
			c.setDataAtendimento( converter.stringToData( request.getDataAtendimento() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		}
	}
	
	public void loadBean( Consulta c, ConsultaSaveRequest request ) throws LoaderException {
		c.setRetorno( request.isRetorno() );
		c.setValor( request.getValor() );
		c.setTempoEstimado( request.getTempoEstimado() );
		c.setTurno( turnoEnumManager.getEnum( request.getTurno() ) );
		c.setDataAgendamento( new Date() ); 
		
		try {
			c.setDataAtendimento( converter.stringToData( request.getDataAtendimento() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		} 
		
		c.setPaga( false );
		c.setStatus( ConsultaStatus.REGISTRADA ); 
		c.setObservacoes( request.getObservacoes() );
	}
	
	public void loadResponse( ConsultaResponse resp, Consulta c ) {
		resp.setId( c.getId() );
		resp.setRetorno( c.isRetorno() );
		resp.setPaga( c.isPaga() );
		
		if ( c.getStatus() != null ) {
			resp.setStatus( c.getTurno().name() );
			resp.setStatusLabel( c.getStatus().label() );
		}
		
		if ( c.getTurno() != null ) {
			resp.setTurno( c.getTurno().name() );
			resp.setTurnoLabel( c.getTurno().label() ); 
		}
		
		resp.setTempoEstimado( c.getTempoEstimado() );
		resp.setDataAtendimento( ( converter.dataHoraToString( c.getDataAgendamento() ) ) ); 
		resp.setValor( c.getValor() ); 
		resp.setObservacoes( c.getObservacoes() );
	}
	
	public Consulta novoBean( Profissional profissional, Paciente paciente, Clinica clinica ) {
		Consulta consulta = new Consulta();
		consulta.setProfissional( profissional );
		consulta.setPaciente( paciente ); 
		consulta.setClinica( clinica ); 
		return consulta;
	}
	
	public void loadRegResponse( ConsultaRegLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() ); 
	}
	
	public ConsultaResponse novoResponse( Paciente p, Clinica c ) {
		ConsultaResponse resp = new ConsultaResponse();
		resp.setPacienteId( p.getId() );
		resp.setPacienteNome( p.getNome() ); 
		resp.setClinicaId( c.getId() );
		resp.setClinicaNome( c.getNome() ); 
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
	
	public ConsultaRegLoadResponse novoRegResponse() {
		return new ConsultaRegLoadResponse();
	}
	
}
