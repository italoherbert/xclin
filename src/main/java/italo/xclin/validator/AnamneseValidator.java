package italo.xclin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.CicatrizacaoEnumManager;
import italo.xclin.enums.PressaoArterialEnumManager;
import italo.xclin.enums.SangramentoEnumManager;
import italo.xclin.exception.ConverterException;
import italo.xclin.exception.Erro;
import italo.xclin.exception.ValidationException;
import italo.xclin.logica.Converter;
import italo.xclin.model.request.save.AnamneseSaveRequest;

@Component
public class AnamneseValidator {

	@Autowired
	private PressaoArterialEnumManager pressaoArterialEnumManager;
	
	@Autowired
	private CicatrizacaoEnumManager cicatrizacaoEnumManager;
	
	@Autowired
	private SangramentoEnumManager sangramentoEnumManager;
	
	@Autowired
	private Converter converter;
	
	public void validaSave( AnamneseSaveRequest request ) throws ValidationException {
		if ( request.getCicatrizacao() == null )
			throw new ValidationException( Erro.CICATRIZACAO_NULA );
		if ( request.getPressaoArterial() == null )
			throw new ValidationException( Erro.PRESSAO_ARTERIAL_NULA );
		if ( request.getSangramentoQuandoSeCorta() == null )
			throw new ValidationException( Erro.TIPO_SANGRAMENTO_NULO );
		
		if ( request.getDataUltimoTratamentoDentario() == null )
			throw new ValidationException( Erro.DATA_ULTIMO_TRATAMENTO_NULA );
		
		if ( !cicatrizacaoEnumManager.enumValida( request.getCicatrizacao() ) )
			throw new ValidationException( Erro.CICATRIZACAO_INVALIDA, request.getCicatrizacao() );
		if ( !pressaoArterialEnumManager.enumValida( request.getPressaoArterial() ) )
			throw new ValidationException( Erro.PRESSAO_ARTERIAL_INVALIDA, request.getPressaoArterial() );
		if ( !sangramentoEnumManager.enumValida( request.getSangramentoQuandoSeCorta() ) )
			throw new ValidationException( Erro.TIPO_SANGRAMENTO_INVALIDO, request.getSangramentoQuandoSeCorta() ); 
		
		try {
			converter.stringToData( request.getDataUltimoTratamentoDentario() );
		} catch (ConverterException e) {
			throw new ValidationException( Erro.DATA_ULTIMO_TRATAMENTO_INVALIDA );
		}			
	}
	
}
