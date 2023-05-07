package italo.scm.validator;

import org.springframework.stereotype.Component;

import italo.scm.exception.Erro;
import italo.scm.exception.ValidationException;
import italo.scm.model.request.filtro.ClinicaFiltroRequest;
import italo.scm.model.request.save.ClinicaSaveRequest;

@Component
public class ClinicaValidator {

	public void validaSave( ClinicaSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.CLINICA_NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.CLINICA_NOME_OBRIGATORIO );		
		if ( request.getTelefone() == null )
			throw new ValidationException( Erro.TELEFONE_OBRIGATORIO );
		if ( request.getTelefone().isBlank() )
			throw new ValidationException( Erro.TELEFONE_OBRIGATORIO );
	}
	
	public void validaFiltro( ClinicaFiltroRequest request ) throws ValidationException {
		if ( request.getNomeIni() == null )
			throw new ValidationException( Erro.CLINICA_NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.CLINICA_NOME_INI_OBRIGATORIO );
	}
	
}
