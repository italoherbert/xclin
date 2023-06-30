package italo.xclin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.Erro;
import italo.xclin.exception.ConverterException;
import italo.xclin.exception.ValidationException;
import italo.xclin.logica.Converter;
import italo.xclin.model.request.filtro.ExameFiltroRequest;
import italo.xclin.model.request.save.ExameSaveRequest;

@Component
public class ExameVinculoValidator {

	@Autowired
	private Converter converter;
	
	public void validaSave( ExameSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_EXAME_NULO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		
		if ( request.getDataExame() == null )
			throw new ValidationException( Erro.DATA_EXAME_INVALIDA );
		
		try {
			converter.stringToDataHora( request.getDataExame() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.DATA_EXAME_INVALIDA );
		}				
	}
	
	public void validaFiltro( ExameFiltroRequest request ) throws ValidationException {
		if ( request.getFiltroNome() == null )
			throw new ValidationException( Erro.NOME_FILTRO_OBRIGATORIO );
		if ( request.getFiltroNome().isBlank() )
			throw new ValidationException( Erro.NOME_FILTRO_OBRIGATORIO );
	}
	
}
