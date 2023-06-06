package italo.xclin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.exception.ConverterException;
import italo.xclin.exception.Erro;
import italo.xclin.exception.ValidationException;
import italo.xclin.logica.Converter;
import italo.xclin.model.request.filtro.PacienteFiltroRequest;
import italo.xclin.model.request.save.PacienteSaveRequest;

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
			throw new ValidationException( Erro.DATA_NASCIMENTO_INVALIDA );
		}
		
	}
	
	public void validaFiltro( PacienteFiltroRequest request ) throws ValidationException {
		if ( request.getNomeIni() == null )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );
		if ( request.getNomeIni().isBlank() )
			throw new ValidationException( Erro.NOME_INI_OBRIGATORIO );		
	}
	
}
