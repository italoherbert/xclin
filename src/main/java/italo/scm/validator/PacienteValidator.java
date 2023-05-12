package italo.scm.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.exception.ConverterException;
import italo.scm.exception.Erro;
import italo.scm.exception.ValidationException;
import italo.scm.logica.Converter;
import italo.scm.model.request.filtro.PacienteFiltroRequest;
import italo.scm.model.request.save.PacienteSaveRequest;

@Component
public class PacienteValidator {
	
	@Autowired
	private Converter converter;

	public void validaSave( PacienteSaveRequest request ) throws ValidationException {
		if ( request.getNome() == null )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		if ( request.getNome().isBlank() )
			throw new ValidationException( Erro.NOME_OBRIGATORIO );
		
		if ( request.getEndereco() == null )
			throw new ValidationException( Erro.ENDERECO_NULL );	
		
		try {
			converter.stringToData( request.getDataNascimento() );	
		} catch ( ConverterException e ) {
			throw new ValidationException( Erro.STRDATA_INVALIDO, request.getDataNascimento() );
		}
		
	}
	
	public void validaFiltro( PacienteFiltroRequest request ) throws ValidationException {
		if ( request.getNomeIni() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );		
	}
	
}
