package italo.xclin.validator;

import org.springframework.stereotype.Component;

import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.filtro.EspecialidadeFiltroRequest;
import italo.xclin.model.request.save.EspecialidadeSaveRequest;
import italo.xclin.msg.Erro;

@Component
public class EspecialidadeValidator {

	public void validaSave( EspecialidadeSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );		
	}
	
	public void validaFiltro( EspecialidadeFiltroRequest request ) throws ValidationException {
		if ( request.getNomeIni() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		
	}
	
}
