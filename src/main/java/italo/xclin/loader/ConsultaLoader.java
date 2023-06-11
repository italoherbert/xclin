package italo.xclin.loader;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.ConsultaStatusEnumManager;
import italo.xclin.enums.TurnoEnumManager;
import italo.xclin.enums.tipos.ConsultaStatus;
import italo.xclin.exception.ConverterException;
import italo.xclin.exception.LoaderException;
import italo.xclin.logica.Converter;
import italo.xclin.model.Clinica;
import italo.xclin.model.Consulta;
import italo.xclin.model.Especialidade;
import italo.xclin.model.Paciente;
import italo.xclin.model.Profissional;
import italo.xclin.model.request.save.ConsultaAlterSaveRequest;
import italo.xclin.model.request.save.ConsultaObservacoesSaveRequest;
import italo.xclin.model.request.save.ConsultaRemarcarSaveRequest;
import italo.xclin.model.request.save.ConsultaSaveRequest;
import italo.xclin.model.response.ConsultaIniciadaResponse;
import italo.xclin.model.response.ConsultaObservacoesResponse;
import italo.xclin.model.response.ConsultaResponse;
import italo.xclin.model.response.EspecialidadeResponse;
import italo.xclin.model.response.load.edit.ConsultaAlterLoadResponse;
import italo.xclin.model.response.load.outros.ConsultaAgendaLoadResponse;
import italo.xclin.model.response.load.outros.ConsultaRemarcarLoadResponse;
import italo.xclin.model.response.load.outros.NovaConsultaLoadResponse;
import italo.xclin.model.response.load.reg.ConsultaRegLoadResponse;
import italo.xclin.model.response.load.tela.ConsultaIniciadaTelaLoadResponse;
import italo.xclin.model.response.load.tela.ConsultaListaFilaTelaLoadResponse;
import italo.xclin.model.response.load.tela.ConsultaTelaLoadResponse;

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
		c.setDataSaveObservacoes( new Date() ); 
	}
	
	public void loadBean( Consulta c, ConsultaAlterSaveRequest request ) throws LoaderException {
		c.setRetorno( request.isRetorno() );
		c.setValor( request.getValor() );				
		c.setPaga( request.isPaga() );
		c.setStatus( consultaStatusEnumManager.getEnum( request.getStatus() ) ); 
		c.setObservacoes( request.getObservacoes() );
		c.setDataSaveObservacoes( new Date() ); 
	}
	
	public void loadBean( Consulta c, ConsultaObservacoesSaveRequest request ) {
		c.setObservacoes( request.getObservacoes() );
		c.setDataSaveObservacoes( new Date() ); 
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
		resp.setDataSaveObservacoes( converter.dataHoraToString( c.getDataSaveObservacoes() ) ); 
		
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
		
		int size = resp.getStatuses().size();
		
		int index = -1;
		for( int i = 0; index == -1 && i < size; i++ )
			if ( resp.getStatuses().get( i ).getName().equalsIgnoreCase( ConsultaStatus.INICIADA.name() ) )
				index = i;		
		
		if ( index != -1 )
			resp.getStatuses().remove( index );
	}
	
	public void loadTelaResponse( ConsultaTelaLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
		resp.setStatuses( consultaStatusEnumManager.tipoResponses() );
	}
	
	public void loadListaFilaTelaResponse( ConsultaListaFilaTelaLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
		resp.setStatuses( consultaStatusEnumManager.tipoResponses() );
	}
	
	public void loadIniciadaTelaResponse( ConsultaIniciadaTelaLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );		
	}
	
	public void loadConsultaAgendaResponse( ConsultaAgendaLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() ); 
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
	
	public ConsultaResponse novoResponse( Clinica c, Profissional pr, Paciente pa, Especialidade e ) {
		ConsultaResponse resp = new ConsultaResponse();
		resp.setPacienteId( pa.getId() );
		resp.setPacienteNome( pa.getNome() ); 
		resp.setClinicaId( c.getId() );
		resp.setClinicaNome( c.getNome() ); 
		resp.setProfissionalId( pr.getId() );
		resp.setProfissionalNome( pr.getNome() );
		resp.setEspecialidadeId( e.getId() );
		resp.setEspecialidadeNome( e.getNome() ); 
		resp.setPacienteAnamnesePreenchida( pa.isAnamnesePreenchida() ); 
		return resp;
	}
	
	public NovaConsultaLoadResponse novoNovaConsultaLoadResponse( 
			List<Long> clinicasIDs, 
			List<String> clinicasNomes ) {
		
		NovaConsultaLoadResponse resp = new NovaConsultaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public ConsultaAgendaLoadResponse novoConsultaAgendaLoadResponse(
			List<Long> clinicasIDs,
			List<String> clinicasNomes ) {
		ConsultaAgendaLoadResponse resp = new ConsultaAgendaLoadResponse();
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
	
	public ConsultaListaFilaTelaLoadResponse novoFilaTelaResponse( 
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		
		ConsultaListaFilaTelaLoadResponse resp = new ConsultaListaFilaTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public ConsultaRemarcarLoadResponse novoRemarcarResponse( Consulta c ) {
		ConsultaRemarcarLoadResponse resp = new ConsultaRemarcarLoadResponse();
		resp.setDataAtendimento( converter.dataToString( c.getDataAtendimento() ) );
		resp.setTurno( c.getTurno().name() );
		resp.setTurnoLabel( c.getTurno().label() ); 
		return resp;
	}
	
	public ConsultaIniciadaTelaLoadResponse novoIniciadaTelaResponse(
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		ConsultaIniciadaTelaLoadResponse resp = new ConsultaIniciadaTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public ConsultaIniciadaResponse novoIniciadaResponse( 
			ConsultaResponse cresp, 
			List<ConsultaObservacoesResponse> historicoObservacoes, 
			int filaQuant ) {
		ConsultaIniciadaResponse resp = new ConsultaIniciadaResponse();
		resp.setConsulta( cresp );
		resp.setHistoricoObservacoes( historicoObservacoes );
		resp.setQuantPacientesNaFila( filaQuant );
		resp.setConsultaIniciada( true );
		return resp;
	}
	
	public ConsultaIniciadaResponse novoNenhumaIniciadaResponse( int filaQuant ) {
		ConsultaIniciadaResponse resp = new ConsultaIniciadaResponse();
		resp.setQuantPacientesNaFila( filaQuant ); 
		resp.setConsultaIniciada( false ); 
		return resp;
	}
	
	public ConsultaObservacoesResponse novoObservacoesResponse( Consulta c ) {
		ConsultaObservacoesResponse resp = new ConsultaObservacoesResponse();
		resp.setObservacoes( c.getObservacoes() );
		resp.setDataSaveObservacoes( converter.dataHoraToString( c.getDataSaveObservacoes() ) ); 
		return resp;
	}
	
}
