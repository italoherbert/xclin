package italo.scm.validator;

import org.springframework.stereotype.Component;

import italo.scm.exception.Erro;
import italo.scm.exception.ValidationException;
import italo.scm.model.request.filtro.PacienteFiltroRequest;
import italo.scm.model.request.save.PacienteSaveRequest;

@Component
public class PacienteValidator {

	public void validaSave( PacienteSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		
		if ( request.getEndereco() == null )
			throw new ValidationException( Erro.ENDERECO_NULL );		
	}
	
	public void validaFiltro( PacienteFiltroRequest request ) throws ValidationException {
		if ( request.getNomeIni() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );		
	}
	
}
