package italo.xclin.validator.relatorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.exception.ConverterException;
import italo.xclin.exception.ValidationException;
import italo.xclin.logica.Converter;
import italo.xclin.model.request.relatorio.BalancoDoDiaRelatorioRequest;
import italo.xclin.msg.Erro;

@Component
public class BalancoDoDiaRelatorioValidator {

	@Autowired
	private Converter converter;
	
	public void validaRelatorioRequest( BalancoDoDiaRelatorioRequest request ) throws ValidationException {
		if ( request.getDataDia() == null )
			throw new ValidationException( Erro.DATA_DIA_NULA );
		if ( request.getDataDia().isBlank() )
			throw new ValidationException( Erro.DATA_DIA_OBRIGATORIA );
				
		try {
			converter.stringToData( request.getDataDia() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.DATA_DIA_INVALIDA );
		}
			
	}
	
}
