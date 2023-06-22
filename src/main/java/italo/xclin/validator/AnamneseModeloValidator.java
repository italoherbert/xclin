package italo.xclin.validator;

import org.springframework.stereotype.Component;

import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.filtro.AnamneseModeloFiltroRequest;
import italo.xclin.model.request.save.AnamneseModeloSaveRequest;
import italo.xclin.msg.Erro;

@Component
public class AnamneseModeloValidator {

	public void validaSave( AnamneseModeloSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
	}
	
	public void validaFiltro( AnamneseModeloFiltroRequest request ) throws ValidationException {
		if ( request.getFiltroNome() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getFiltroNome().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
	}
	
}
