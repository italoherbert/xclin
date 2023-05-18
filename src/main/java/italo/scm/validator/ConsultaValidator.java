package italo.scm.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.enums.ConsultaStatusEnumManager;
import italo.scm.enums.TurnoEnumManager;
import italo.scm.exception.ConverterException;
import italo.scm.exception.Erro;
import italo.scm.exception.ValidationException;
import italo.scm.logica.Converter;
import italo.scm.model.request.filtro.ConsultaFiltroRequest;
import italo.scm.model.request.filtro.ConsultaFilaFiltroRequest;
import italo.scm.model.request.save.ConsultaSaveRequest;

@Component
public class ConsultaValidator {
	
	@Autowired
	private TurnoEnumManager turnoEnumManager;
	
	@Autowired
	private ConsultaStatusEnumManager consultaStatusEnumManager;
	
	@Autowired
	private Converter converter;

	public void validaSave( ConsultaSaveRequest request ) throws ValidationException {
		if ( !turnoEnumManager.enumValida( request.getTurno() ) )
			throw new ValidationException( Erro.TURNO_OBRIGATORIO );
		
		try {
			converter.stringToData( request.getDataAtendimento() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.STRDATA_INVALIDO, request.getDataAtendimento() );
		}
	}
	
	public void validaFiltro( ConsultaFiltroRequest request ) throws ValidationException {
		if ( request.getDataInicio() == null )
			throw new ValidationException( Erro.DATA_INI_OBRIGATORIA );
		if ( request.getDataFim() == null )
			throw new ValidationException( Erro.DATA_FIM_OBRIGATORIA );
		
		try {
			converter.stringToData( request.getDataInicio() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.STRDATA_INVALIDO, request.getDataInicio() );
		}
		
		try {
			converter.stringToData( request.getDataFim() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.STRDATA_INVALIDO, request.getDataFim() );
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
	
	public void validaListaFila( ConsultaFilaFiltroRequest request ) throws ValidationException {
		if ( request.getData() == null )
			throw new ValidationException( Erro.DATA_OBRIGATORIA );
		
		try {
			converter.stringToData( request.getData() );
		} catch ( ConverterException e ) {
			throw new ValidationException( Erro.STRDATA_INVALIDO, request.getData() );
		}
		
		if ( !turnoEnumManager.enumValida( request.getTurno() ) )
			throw new ValidationException( Erro.TURNO_INVALIDO, request.getTurno() );				
	}
	
}
