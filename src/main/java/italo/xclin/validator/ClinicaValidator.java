package italo.xclin.validator;

import org.springframework.stereotype.Component;

import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.filtro.ClinicaFiltroRequest;
import italo.xclin.model.request.save.ClinicaSaveRequest;
import italo.xclin.msg.Erro;

@Component
public class ClinicaValidator {

	public void validaSave( ClinicaSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );		
		if ( request.getTelefone() == null )
			throw new ValidationException( Erro.TELEFONE_OBRIGATORIO );
		if ( request.getTelefone().isBlank() )
			throw new ValidationException( Erro.TELEFONE_OBRIGATORIO );
		
		if ( request.getEndereco() == null )
			throw new ValidationException( Erro.ENDERECO_NULL );			
	}
	
	public void validaFiltro( ClinicaFiltroRequest request ) throws ValidationException {
		if ( request.getNomeIni() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
	}
	
}
