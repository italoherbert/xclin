package italo.xclin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.LancamentoTipoEnumManager;
import italo.xclin.exception.ConverterException;
import italo.xclin.exception.Erro;
import italo.xclin.exception.ValidationException;
import italo.xclin.logica.Converter;
import italo.xclin.model.request.filtro.LancamentoFiltroRequest;
import italo.xclin.model.request.save.LancamentoSaveRequest;

@Component
public class LancamentoValidator {

	@Autowired
	private LancamentoTipoEnumManager lancamentoTipoEnumManager;
	
	@Autowired
	private Converter converter;
	
	public void validaSave( LancamentoSaveRequest request ) throws ValidationException {		
		if ( !lancamentoTipoEnumManager.enumValida( request.getTipo() ) )
			throw new ValidationException( Erro.LANCAMENTO_TIPO_INVALIDO );
	}
	
	public void validaFiltro( LancamentoFiltroRequest request ) throws ValidationException {
		if ( request.getDataInicio() == null )
			throw new ValidationException( Erro.DATA_INI_OBRIGATORIA );
		if ( request.getDataFim() == null )
			throw new ValidationException( Erro.DATA_FIM_OBRIGATORIA );
		
		try {
			converter.stringToData( request.getDataInicio() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.DATA_INI_INVALIDA );
		}
		
		try {
			converter.stringToData( request.getDataFim() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.DATA_FIM_INVALIDA );
		}
		
		if ( request.isIncluirUsername() ) {
			if ( request.getFiltroUsername() == null )
				throw new ValidationException( Erro.USERNAME_FILTRO_OBRIGATORIO );
			if ( request.getFiltroUsername().isBlank() )
				throw new ValidationException( Erro.USERNAME_FILTRO_OBRIGATORIO );
		}
	}
	
}
