package italo.xclin.validator;

import org.springframework.stereotype.Component;

import italo.xclin.Erro;
import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.save.PacienteAnexoSaveRequest;

@Component
public class PacienteAnexoValidator {

	public final static int maxAnexoLength = 5120000;
	
	public void validaSave( PacienteAnexoSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_ANEXO_NULO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_ANEXO_OBRIGATORIO );
		
		if ( request.getArquivo() == null )
			throw new ValidationException( Erro.ANEXO_NULO );
		
		if ( request.getArquivo().length() > maxAnexoLength )
			throw new ValidationException( Erro.ANEXO_LIMITE_EXCEDIDO, "5MB" );
			
	}
	
}
