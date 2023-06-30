package italo.xclin.loader;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.AtendimentoStatusEnumManager;
import italo.xclin.enums.TurnoEnumManager;
import italo.xclin.enums.tipos.AtendimentoStatus;
import italo.xclin.exception.ConverterException;
import italo.xclin.exception.LoaderException;
import italo.xclin.logica.Converter;
import italo.xclin.model.Clinica;
import italo.xclin.model.Atendimento;
import italo.xclin.model.Especialidade;
import italo.xclin.model.Paciente;
import italo.xclin.model.Profissional;
import italo.xclin.model.request.save.AtendimentoAlterSaveRequest;
import italo.xclin.model.request.save.AtendimentoObservacoesSaveRequest;
import italo.xclin.model.request.save.AtendimentoRemarcarSaveRequest;
import italo.xclin.model.request.save.AtendimentoSaveRequest;
import italo.xclin.model.response.AtendimentoIniciadoResponse;
import italo.xclin.model.response.AtendimentoObservacoesResponse;
import italo.xclin.model.response.AtendimentoResponse;
import italo.xclin.model.response.EspecialidadeResponse;
import italo.xclin.model.response.PacienteAnexoResponse;
import italo.xclin.model.response.load.edit.AtendimentoAlterLoadResponse;
import italo.xclin.model.response.load.edit.AtendimentoRemarcarLoadResponse;
import italo.xclin.model.response.load.reg.AtendimentoRegLoadResponse;
import italo.xclin.model.response.load.reg.NovoAtendimentoLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoAgendaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoIniciadaTelaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoListaFilaTelaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoTelaLoadResponse;

@Component
public class AtendimentoLoader {

	@Autowired
	private Converter converter;
	
	@Autowired
	private TurnoEnumManager turnoEnumManager;
	
	@Autowired
	private AtendimentoStatusEnumManager consultaStatusEnumManager;
	
	public void loadBean( Atendimento c, AtendimentoRemarcarSaveRequest request ) throws LoaderException {
		c.setDataAgendamento( new Date() ); 
		c.setTurno( turnoEnumManager.getEnum( request.getTurno() ) ); 
		try {
			c.setDataAtendimento( converter.stringToData( request.getDataAtendimento() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		}
	}
	
	public void loadBean( Atendimento c, AtendimentoSaveRequest request ) throws LoaderException {
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
		c.setStatus( AtendimentoStatus.REGISTRADO ); 
		c.setObservacoes( request.getObservacoes() );
		c.setDataSaveObservacoes( new Date() ); 
	}
	
	public void loadBean( Atendimento c, AtendimentoAlterSaveRequest request ) throws LoaderException {
		c.setRetorno( request.isRetorno() );
		c.setValor( request.getValor() );				
		c.setStatus( consultaStatusEnumManager.getEnum( request.getStatus() ) ); 
		c.setObservacoes( request.getObservacoes() );
		c.setDataSaveObservacoes( new Date() ); 
	}
	
	public void loadBean( Atendimento c, AtendimentoObservacoesSaveRequest request ) {
		c.setObservacoes( request.getObservacoes() );
		c.setDataSaveObservacoes( new Date() ); 
	}
		
	public void loadResponse( AtendimentoResponse resp, Atendimento c ) {
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
		
	public void loadRegResponse( AtendimentoRegLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() ); 
	}
	
	public void loadRemarcarResponse( AtendimentoRemarcarLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
	}
	
	public void loadAlterResponse( AtendimentoAlterLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
		resp.setStatuses( consultaStatusEnumManager.tipoResponses() );
		
		int size = resp.getStatuses().size();
		
		int index = -1;
		for( int i = 0; index == -1 && i < size; i++ )
			if ( resp.getStatuses().get( i ).getName().equalsIgnoreCase( AtendimentoStatus.INICIADO.name() ) )
				index = i;		
		
		if ( index != -1 )
			resp.getStatuses().remove( index );
	}
	
	public void loadTelaResponse( AtendimentoTelaLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
		resp.setStatuses( consultaStatusEnumManager.tipoResponses() );
	}
	
	public void loadListaFilaTelaResponse( AtendimentoListaFilaTelaLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
		resp.setStatuses( consultaStatusEnumManager.tipoResponses() );
	}
	
	public void loadIniciadaTelaResponse( AtendimentoIniciadaTelaLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );		
	}
	
	public void loadConsultaAgendaResponse( AtendimentoAgendaLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() ); 
	}
	
	public Atendimento novoBean( 
			Profissional profissional,
			Especialidade especialidade,
			Paciente paciente, 
			Clinica clinica ) {
		Atendimento consulta = new Atendimento();
		consulta.setProfissional( profissional );
		consulta.setEspecialidade( especialidade ); 
		consulta.setPaciente( paciente ); 
		consulta.setClinica( clinica ); 
		return consulta;
	}
	
	public AtendimentoResponse novoResponse( Clinica c, Profissional pr, Paciente pa, Especialidade e ) {
		AtendimentoResponse resp = new AtendimentoResponse();
		resp.setPacienteId( pa.getId() );
		resp.setPacienteNome( pa.getNome() ); 
		resp.setClinicaId( c.getId() );
		resp.setClinicaNome( c.getNome() ); 
		resp.setProfissionalId( pr.getId() );
		resp.setProfissionalNome( pr.getNome() );
		resp.setEspecialidadeId( e.getId() );
		resp.setEspecialidadeNome( e.getNome() ); 
		resp.setPacienteAnamneseCriada( pa.isAnamneseCriada() ); 
		return resp;
	}
	
	public NovoAtendimentoLoadResponse novoNovaConsultaLoadResponse( 
			List<Long> clinicasIDs, 
			List<String> clinicasNomes ) {
		
		NovoAtendimentoLoadResponse resp = new NovoAtendimentoLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public AtendimentoAgendaLoadResponse novoConsultaAgendaLoadResponse(
			List<Long> clinicasIDs,
			List<String> clinicasNomes ) {
		AtendimentoAgendaLoadResponse resp = new AtendimentoAgendaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public AtendimentoRegLoadResponse novoRegResponse( List<EspecialidadeResponse> especialidades ) {
		AtendimentoRegLoadResponse resp = new AtendimentoRegLoadResponse();
		resp.setEspecialidades( especialidades );		
		return resp;
	}
	
	public AtendimentoAlterLoadResponse novoAlterResponse( AtendimentoResponse aresp ) {
		AtendimentoAlterLoadResponse resp = new AtendimentoAlterLoadResponse();
		resp.setAtendimento( aresp ); 
		return resp;
	}
	
	public AtendimentoTelaLoadResponse novoTelaResponse( 
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		
		AtendimentoTelaLoadResponse resp = new AtendimentoTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public AtendimentoListaFilaTelaLoadResponse novoFilaTelaResponse( 
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		
		AtendimentoListaFilaTelaLoadResponse resp = new AtendimentoListaFilaTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public AtendimentoRemarcarLoadResponse novoRemarcarResponse( Atendimento c ) {
		AtendimentoRemarcarLoadResponse resp = new AtendimentoRemarcarLoadResponse();
		resp.setDataAtendimento( converter.dataToString( c.getDataAtendimento() ) );
		resp.setTurno( c.getTurno().name() );
		resp.setTurnoLabel( c.getTurno().label() ); 
		return resp;
	}
	
	public AtendimentoIniciadaTelaLoadResponse novoIniciadaTelaResponse(
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		AtendimentoIniciadaTelaLoadResponse resp = new AtendimentoIniciadaTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public AtendimentoIniciadoResponse novoIniciadoResponse( 
			AtendimentoResponse aresp, 
			List<AtendimentoObservacoesResponse> historicoObservacoes, 
			List<PacienteAnexoResponse> anexos,
			int filaQuant ) {
		
		AtendimentoIniciadoResponse resp = new AtendimentoIniciadoResponse();
		resp.setConsulta( aresp );
		resp.setHistoricoObservacoes( historicoObservacoes );
		resp.setPacienteAnexos( anexos ); 
		resp.setQuantPacientesNaFila( filaQuant );
		resp.setConsultaIniciada( true );
		return resp;
	}
	
	public AtendimentoIniciadoResponse novoNenhumaIniciadaResponse( int filaQuant ) {
		AtendimentoIniciadoResponse resp = new AtendimentoIniciadoResponse();
		resp.setQuantPacientesNaFila( filaQuant ); 
		resp.setConsultaIniciada( false ); 
		return resp;
	}
	
	public AtendimentoObservacoesResponse novoObservacoesResponse( Atendimento c ) {
		AtendimentoObservacoesResponse resp = new AtendimentoObservacoesResponse();
		resp.setObservacoes( c.getObservacoes() );
		resp.setDataSaveObservacoes( converter.dataHoraToString( c.getDataSaveObservacoes() ) ); 
		return resp;
	}		
	
}
