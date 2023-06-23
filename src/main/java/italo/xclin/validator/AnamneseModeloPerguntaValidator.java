package italo.xclin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.Erro;
import italo.xclin.enums.PerguntaTipoEnumManager;
import italo.xclin.enums.tipos.PerguntaTipo;
import italo.xclin.exception.ValidationException;
import italo.xclin.model.request.save.AnamneseModeloPerguntaSaveRequest;

@Component
public class AnamneseModeloPerguntaValidator {

	@Autowired
	private PerguntaTipoEnumManager perguntaTipoEnumManager;
	
	public void validaSave( AnamneseModeloPerguntaSaveRequest request ) throws ValidationException {
		if ( request.getPergunta() == null )
			throw new ValidationException( Erro.PERGUNTA_OBRIGATORIA );
		if ( request.getPergunta().isBlank() )
			throw new ValidationException( Erro.PERGUNTA_OBRIGATORIA );
		
		if ( perguntaTipoEnumManager.enumValida( request.getTipo() ) ) {
			PerguntaTipo pt = perguntaTipoEnumManager.getEnum( request.getTipo() );
			if ( pt == PerguntaTipo.ENUM ) {
				if ( !request.getEnums().matches( "(.*,{0,1})+" ) )
					throw new ValidationException( Erro.PERGUNTA_TIPO_INVALIDA );
			}
		} else {
			throw new ValidationException( Erro.PERGUNTA_TIPO_INVALIDA );
		}
	}
	
}
