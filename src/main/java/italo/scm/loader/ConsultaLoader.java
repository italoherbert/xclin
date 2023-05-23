package italo.scm.loader;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.enums.ConsultaStatusEnumManager;
import italo.scm.enums.TurnoEnumManager;
import italo.scm.enums.tipos.ConsultaStatus;
import italo.scm.exception.ConverterException;
import italo.scm.exception.LoaderException;
import italo.scm.logica.Converter;
import italo.scm.model.Clinica;
import italo.scm.model.Consulta;
import italo.scm.model.Especialidade;
import italo.scm.model.Paciente;
import italo.scm.model.Profissional;
import italo.scm.model.request.save.ConsultaAlterSaveRequest;
import italo.scm.model.request.save.ConsultaRemarcarSaveRequest;
import italo.scm.model.request.save.ConsultaSaveRequest;
import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.EspecialidadeResponse;
import italo.scm.model.response.load.edit.ConsultaAlterLoadResponse;
import italo.scm.model.response.load.outros.ConsultaRemarcarLoadResponse;
import italo.scm.model.response.load.outros.NovaConsultaProfissionalSelectLoadResponse;
import italo.scm.model.response.load.reg.ConsultaRegLoadResponse;
import italo.scm.model.response.load.tela.ConsultaFilaTelaLoadResponse;
import italo.scm.model.response.load.tela.ConsultaTelaLoadResponse;

@Component
public class ConsultaLoader {

	@Autowired
	private Converter converter;
	
	@Autowired
	private TurnoEnumManager turnoEnumManager;
	
	@Autowired
	private ConsultaStatusEnumManager consultaStatusEnumManager;
	
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
	
	public void loadBean( Consulta c, ConsultaAlterSaveRequest request ) throws LoaderException {
		c.setRetorno( request.isRetorno() );
		c.setValor( request.getValor() );				
		c.setPaga( request.isPaga() );
		c.setStatus( consultaStatusEnumManager.getEnum( request.getStatus() ) ); 
		c.setObservacoes( request.getObservacoes() );
	}
	
	public void loadResponse( ConsultaResponse resp, Consulta c ) {
		resp.setId( c.getId() );
		resp.setRetorno( c.isRetorno() );
		resp.setPaga( c.isPaga() );
		
		if ( c.getStatus() != null ) {
			resp.setStatus( c.getStatus().name() );
			resp.setStatusLabel( c.getStatus().label() );
		}
		
		if ( c.getTurno() != null ) {
			resp.setTurno( c.getTurno().name() );
			resp.setTurnoLabel( c.getTurno().label() ); 
		}
		
		resp.setDataAgendamento( converter.dataHoraToString( c.getDataAgendamento() ) );
		resp.setDataAtendimento( ( converter.dataHoraToString( c.getDataAtendimento() ) ) );
		
		if ( c.getDataFinalizacao() != null )
			resp.setDataFinalizacao( converter.dataHoraToString( c.getDataFinalizacao() ) );
		
		resp.setValor( c.getValor() ); 
		resp.setObservacoes( c.getObservacoes() );
	}
		
	public void loadRegResponse( ConsultaRegLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() ); 
	}
	
	public void loadRemarcarResponse( ConsultaRemarcarLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
	}
	
	public void loadAlterResponse( ConsultaAlterLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
		resp.setStatuses( consultaStatusEnumManager.tipoResponses() );
	}
	
	public void loadTelaResponse( ConsultaTelaLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
		resp.setStatuses( consultaStatusEnumManager.tipoResponses() );
	}
	
	public void loadFilaTelaResponse( ConsultaFilaTelaLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
		resp.setStatuses( consultaStatusEnumManager.tipoResponses() );
	}
	
	public Consulta novoBean( 
			Profissional profissional,
			Especialidade especialidade,
			Paciente paciente, 
			Clinica clinica ) {
		Consulta consulta = new Consulta();
		consulta.setProfissional( profissional );
		consulta.setEspecialidade( especialidade ); 
		consulta.setPaciente( paciente ); 
		consulta.setClinica( clinica ); 
		return consulta;
	}
	
	public ConsultaResponse novoResponse( Paciente p, Clinica c, Especialidade e ) {
		ConsultaResponse resp = new ConsultaResponse();
		resp.setPacienteId( p.getId() );
		resp.setPacienteNome( p.getNome() ); 
		resp.setClinicaId( c.getId() );
		resp.setClinicaNome( c.getNome() ); 
		resp.setEspecialidadeId( e.getId() );
		resp.setEspecialidadeNome( e.getNome() ); 
		return resp;
	}
	
	public NovaConsultaProfissionalSelectLoadResponse novoProfissionalSelectLoadResponse( 
			List<Long> clinicasIDs, 
			List<String> clinicasNomes ) {
		
		NovaConsultaProfissionalSelectLoadResponse resp = new NovaConsultaProfissionalSelectLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public ConsultaRegLoadResponse novoRegResponse( List<EspecialidadeResponse> especialidades ) {
		ConsultaRegLoadResponse resp = new ConsultaRegLoadResponse();
		resp.setEspecialidades( especialidades );		
		return resp;
	}
	
	public ConsultaAlterLoadResponse novoAlterResponse( ConsultaResponse cresp ) {
		ConsultaAlterLoadResponse resp = new ConsultaAlterLoadResponse();
		resp.setConsulta( cresp ); 
		return resp;
	}
	
	public ConsultaTelaLoadResponse novoTelaResponse( 
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		
		ConsultaTelaLoadResponse resp = new ConsultaTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public ConsultaFilaTelaLoadResponse novoFilaTelaResponse( 
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		
		ConsultaFilaTelaLoadResponse resp = new ConsultaFilaTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public ConsultaRemarcarLoadResponse novoRemarcarResponse() {
		return new ConsultaRemarcarLoadResponse();
	}
	
}
