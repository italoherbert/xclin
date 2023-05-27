package italo.xclin.validator;

import org.springframework.stereotype.Component;

import italo.xclin.exception.Erro;
import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.filtro.RecursoFiltroRequest;
import italo.xclin.model.request.save.RecursoSaveRequest;

@Component
public class RecursoValidator {

	public void validaSave( RecursoSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );		
	}
	
	public void validaFiltro( RecursoFiltroRequest request ) throws ValidationException {
		if ( request.getNomeIni() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		
	}
	
}
