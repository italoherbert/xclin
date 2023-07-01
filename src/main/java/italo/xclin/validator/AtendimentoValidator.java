package italo.xclin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.Erro;
import italo.xclin.enums.AtendimentoStatusEnumManager;
import italo.xclin.enums.TurnoEnumManager;
import italo.xclin.exception.ConverterException;
import italo.xclin.exception.ValidationException;
import italo.xclin.logica.Converter;
import italo.xclin.model.request.filtro.AtendimentoFiltroRequest;
import italo.xclin.model.request.filtro.AtendimentoListaFilaCompletaFiltroRequest;
import italo.xclin.model.request.filtro.AtendimentoListaFilaFiltroRequest;
import italo.xclin.model.request.save.AtendimentoAlterSaveRequest;
import italo.xclin.model.request.save.AtendimentoObservacoesSaveRequest;
import italo.xclin.model.request.save.AtendimentoSaveRequest;

@Component
public class AtendimentoValidator {
	
	@Autowired
	private TurnoEnumManager turnoEnumManager;
	
	@Autowired
	private AtendimentoStatusEnumManager consultaStatusEnumManager;
	
	@Autowired
	private Converter converter;

	public void validaSave( AtendimentoSaveRequest request ) throws ValidationException {
		if ( !turnoEnumManager.enumValida( request.getTurno() ) )
			throw new ValidationException( Erro.TURNO_OBRIGATORIO );
		
		try {
			converter.stringToData( request.getDataAtendimento() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.DATA_ATENDIMENTO_INVALIDA );
		}
				
		if ( request.isPago() && request.getValorPago() == 0 )
			throw new ValidationException( Erro.ATENDIMENTO_PAGO_COM_ZERO );
	}
	
	public void validaAlterSave( AtendimentoAlterSaveRequest request ) throws ValidationException {
		if ( !consultaStatusEnumManager.enumValida( request.getStatus() ) )
			throw new ValidationException( Erro.STATUS_OBRIGATORIO );
	}
		
	public void validaAlterObservacoes( AtendimentoObservacoesSaveRequest request ) throws ValidationException {
		if ( request.getObservacoes() == null )
			throw new ValidationException( Erro.OBSERVACOES_NULO );
	}
	
	public void validaFiltro( AtendimentoFiltroRequest request ) throws ValidationException {
		if ( request.getDataInicio() == null )
			throw new ValidationException( Erro.DATA_INI_OBRIGATORIA );
		if ( request.getDataFim() == null )
			throw new ValidationException( Erro.DATA_FIM_OBRIGATORIA );
		
		try {
			converter.stringToData( request.getDataInicio() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.DATA_INI_INVALIDA );
		}
		
		try {
			converter.stringToData( request.getDataFim() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.DATA_FIM_INVALIDA );
		}
		
		if ( request.isIncluirPaciente() ) {
			if ( request.getPacienteNomeIni() == null )
				throw new ValidationException( Erro.PACIENTE_NOME_INI_OBRIGATORIO );
			if ( request.getPacienteNomeIni().isBlank() )
				throw new ValidationException( Erro.PACIENTE_NOME_INI_OBRIGATORIO );
		}
		
		if ( request.isIncluirProfissional() ) {
			if ( request.getProfissionalNomeIni() == null )
				throw new ValidationException( Erro.PROFISSIONAL_NOME_INI_OBRIGATORIO );
			if ( request.getProfissionalNomeIni().isBlank() )
				throw new ValidationException( Erro.PROFISSIONAL_NOME_INI_OBRIGATORIO );
		}
		
		if ( !request.isIncluirTodosStatuses() )
			if ( !consultaStatusEnumManager.enumValida( request.getStatus() ) )
				throw new ValidationException( Erro.CONSULTA_STATUS_INVALIDO, request.getStatus() );
		
		if ( !request.isIncluirTodosTurnos() )
			if ( !turnoEnumManager.enumValida( request.getTurno() ) )
				throw new ValidationException( Erro.TURNO_INVALIDO, request.getTurno() );				
	}
	
	public void validaListaFila( AtendimentoListaFilaFiltroRequest request ) throws ValidationException {
		if ( request.getData() == null )
			throw new ValidationException( Erro.DATA_OBRIGATORIA );
		
		try {
			converter.stringToData( request.getData() );
		} catch ( ConverterException e ) {
			throw new ValidationException( Erro.DATA_CONSULTA_FILA_INVALIDA );
		}
		
		if ( !turnoEnumManager.enumValida( request.getTurno() ) )
			throw new ValidationException( Erro.TURNO_INVALIDO, request.getTurno() );				
	}
	
	public void validaListaFilaCompleta( AtendimentoListaFilaCompletaFiltroRequest request ) throws ValidationException {
		if ( request.getData() == null )
			throw new ValidationException( Erro.DATA_OBRIGATORIA );
		
		try {
			converter.stringToData( request.getData() );
		} catch ( ConverterException e ) {
			throw new ValidationException( Erro.DATA_CONSULTA_FILA_INVALIDA );
		}
		
		if ( !turnoEnumManager.enumValida( request.getTurno() ) )
			throw new ValidationException( Erro.TURNO_INVALIDO, request.getTurno() );				
	}
	
}
