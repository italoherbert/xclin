package italo.xclin.validator;

import org.springframework.stereotype.Component;

import italo.xclin.Erro;
import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.filtro.ExameFiltroRequest;
import italo.xclin.model.request.save.ExameSaveRequest;

@Component
public class ExameValidator {

	public void validaSave( ExameSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );		
	}
	
	public void validaFiltro( ExameFiltroRequest request ) throws ValidationException {
		if ( request.getFiltroNome() == null )
			throw new ValidationException( Erro.NOME_FILTRO_OBRIGATORIO );
		if ( request.getFiltroNome().isBlank() )
			throw new ValidationException( Erro.NOME_FILTRO_OBRIGATORIO );
		
	}
	
}
