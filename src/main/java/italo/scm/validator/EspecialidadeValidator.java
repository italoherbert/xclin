package italo.scm.validator;

import org.springframework.stereotype.Component;

import italo.scm.exception.Erro;
import italo.scm.exception.ValidationException;
import italo.scm.model.request.filtro.EspecialidadeFiltroRequest;
import italo.scm.model.request.save.EspecialidadeSaveRequest;

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
