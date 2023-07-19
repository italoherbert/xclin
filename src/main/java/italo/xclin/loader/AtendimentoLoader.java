package italo.xclin.loader;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.AtendimentoStatusEnumManager;
import italo.xclin.enums.TurnoEnumManager;
import italo.xclin.exception.ConverterException;
import italo.xclin.exception.LoaderException;
import italo.xclin.logica.Converter;
import italo.xclin.model.Atendimento;
import italo.xclin.model.Clinica;
import italo.xclin.model.Orcamento;
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
import italo.xclin.model.response.OrcamentoResponse;
import italo.xclin.model.response.PacienteAnexoResponse;
import italo.xclin.model.response.ProfissionalExameVinculoResponse;
import italo.xclin.model.response.ProfissionalProcedimentoVinculoResponse;
import italo.xclin.model.response.load.edit.AtendimentoAlterLoadResponse;
import italo.xclin.model.response.load.edit.AtendimentoRemarcarLoadResponse;
import italo.xclin.model.response.load.reg.AtendimentoRegLoadResponse;
import italo.xclin.model.response.load.reg.NovoAtendimentoRegLoadResponse;
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
		
	public void loadBean( Atendimento a, AtendimentoSaveRequest request ) throws LoaderException {
		a.setDataAgendamento( new Date() );
		
		try {
			a.setDataAtendimento( converter.stringToData( request.getDataAtendimento() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		}
		
		a.setTurno( turnoEnumManager.getEnum( request.getTurno() ) );
		a.setObservacoes( request.getObservacoes() );		
	}
			
	public void loadBean( Atendimento a, AtendimentoRemarcarSaveRequest request ) throws LoaderException {
		a.setDataAgendamento( new Date() ); 
		a.setTurno( turnoEnumManager.getEnum( request.getTurno() ) ); 
		try {
			a.setDataAtendimento( converter.stringToData( request.getDataAtendimento() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		}	
	}
	
	public void loadBean( Atendimento a, AtendimentoAlterSaveRequest request ) {
		a.setObservacoes( request.getObservacoes() );
	}
		
	public void loadBean( Atendimento a, AtendimentoObservacoesSaveRequest request ) {
		a.setObservacoes( request.getObservacoes() );
		a.setDataSaveObservacoes( new Date() ); 
	}
	
	public void loadBean( Atendimento a ) {
		a.setDataAgendamento( new Date() );	
	}
		
	public void loadResponse( AtendimentoResponse resp, Atendimento a ) {
		resp.setId( a.getId() );
				
		resp.setStatus( a.getStatus().name() );
		resp.setStatusLabel( a.getStatus().label() );		
		
		resp.setTurno( a.getTurno().name() );
		resp.setTurnoLabel( a.getTurno().label() ); 		
		
		resp.setDataAgendamento( converter.dataHoraToString( a.getDataAgendamento() ) );
		resp.setDataAtendimento( ( converter.dataHoraToString( a.getDataAtendimento() ) ) );
		
		if ( a.getDataSaveObservacoes() != null )
			resp.setDataSaveObservacoes( converter.dataHoraToString( a.getDataSaveObservacoes() ) ); 
		
		if ( a.getDataFinalizacao() != null )
			resp.setDataFinalizacao( converter.dataHoraToString( a.getDataFinalizacao() ) );
		
		resp.setObservacoes( a.getObservacoes() );		
	}
		
	public void loadRegResponse( AtendimentoRegLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() ); 
	}
	
	public void loadRemarcarResponse( AtendimentoRemarcarLoadResponse resp ) {
		resp.setTurnos( turnoEnumManager.tipoResponses() );
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
			Paciente paciente, 
			Clinica clinica,
			Orcamento orcamento ) {
		Atendimento atendimento = new Atendimento();
		atendimento.setProfissional( profissional );
		atendimento.setPaciente( paciente ); 
		atendimento.setClinica( clinica );
		atendimento.setOrcamento( orcamento ); 		
		return atendimento;
	}
	
	public AtendimentoResponse novoResponse( 
			Clinica a, 
			Profissional pr, 
			Paciente pa, 
			OrcamentoResponse orcamento ) {
		AtendimentoResponse resp = new AtendimentoResponse();
		resp.setPacienteId( pa.getId() );
		resp.setPacienteNome( pa.getNome() ); 
		resp.setClinicaId( a.getId() );
		resp.setClinicaNome( a.getNome() ); 
		resp.setProfissionalId( pr.getId() );
		resp.setProfissionalNome( pr.getNome() );
		resp.setPacienteAnamneseCriada( pa.isAnamneseCriada() );
		resp.setOrcamento( orcamento ); 						
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
	
	public NovoAtendimentoRegLoadResponse novoAtendimentoRegLoadResponse(
			List<Long> clinicasIDs,
			List<String> clinicasNomes ) {
		NovoAtendimentoRegLoadResponse resp = new NovoAtendimentoRegLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public AtendimentoRegLoadResponse novoRegResponse( 
			List<EspecialidadeResponse> especialidades, 
			List<ProfissionalExameVinculoResponse> profissionalExames,
			List<ProfissionalProcedimentoVinculoResponse> profissionalProcedimentos ) {
		
		AtendimentoRegLoadResponse resp = new AtendimentoRegLoadResponse();
		resp.setEspecialidades( especialidades );
		resp.setProfissionalExames( profissionalExames );
		resp.setProfissionalProcedimentos( profissionalProcedimentos );
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
	
	public AtendimentoRemarcarLoadResponse novoRemarcarResponse( Atendimento a ) {
		AtendimentoRemarcarLoadResponse resp = new AtendimentoRemarcarLoadResponse();
		resp.setDataAtendimento( converter.dataToString( a.getDataAtendimento() ) );
		resp.setTurno( a.getTurno().name() );
		resp.setTurnoLabel( a.getTurno().label() ); 
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
		
		resp.setAtendimento( aresp );
		resp.setHistoricoObservacoes( historicoObservacoes );
		resp.setPacienteAnexos( anexos ); 
		resp.setQuantPacientesNaFila( filaQuant );
		resp.setAtendimentoIniciado( true );
		return resp;
	}
	
	public AtendimentoIniciadoResponse novoNenhumaIniciadaResponse( int filaQuant ) {
		AtendimentoIniciadoResponse resp = new AtendimentoIniciadoResponse();
		resp.setQuantPacientesNaFila( filaQuant ); 
		resp.setAtendimentoIniciado( false ); 
		return resp;
	}
	
	public AtendimentoObservacoesResponse novoObservacoesResponse( Atendimento a ) {
		AtendimentoObservacoesResponse resp = new AtendimentoObservacoesResponse();
		resp.setObservacoes( a.getObservacoes() );
		if ( a.getDataSaveObservacoes() != null )
			resp.setDataSaveObservacoes( converter.dataHoraToString( a.getDataSaveObservacoes() ) ); 
		return resp;
	}		
				
}
