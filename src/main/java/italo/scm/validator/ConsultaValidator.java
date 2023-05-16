package italo.scm.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.enums.TurnoEnumManager;
import italo.scm.exception.ConverterException;
import italo.scm.exception.Erro;
import italo.scm.exception.ValidationException;
import italo.scm.logica.Converter;
import italo.scm.model.request.filtro.ConsultaFiltroRequest;
import italo.scm.model.request.save.ConsultaSaveRequest;

@Component
public class ConsultaValidator {
	
	@Autowired
	private TurnoEnumManager turnoEnumManager;
	
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
		if ( !turnoEnumManager.enumValida( request.getTurno() ) )
			throw new ValidationException( Erro.TURNO_OBRIGATORIO );
		
		try {
			converter.stringToDataHora( request.getDataHoraAgendamento() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.STRDATA_INVALIDO, request.getDataHoraAgendamento() );
		}
	}
	
}
