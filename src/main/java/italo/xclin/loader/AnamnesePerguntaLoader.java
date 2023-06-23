package italo.xclin.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.PerguntaTipoEnumManager;
import italo.xclin.model.Anamnese;
import italo.xclin.model.AnamnesePergunta;
import italo.xclin.model.request.save.AnamnesePerguntaSaveRequest;
import italo.xclin.model.response.AnamnesePerguntaResponse;

@Component
public class AnamnesePerguntaLoader {

	@Autowired
	private PerguntaTipoEnumManager perguntaTipoEnumManager;
	
	public void loadBean( AnamnesePergunta pergunta, AnamnesePerguntaSaveRequest request ) {
		pergunta.setPergunta( request.getPergunta() );
		pergunta.setTipo( perguntaTipoEnumManager.getEnum( request.getTipo() ) );
		switch ( pergunta.getTipo() ) {
			case STRING:
				pergunta.setRespostaString( request.getRespostaString() );
				break;
			case BOOLEAN:
				pergunta.setRespostaBoolean( request.isRespostaBoolean() );
				break;
			case ENUM:
				pergunta.setRespostaEnum( request.getRespostaEnum() ); 
				break;
		}	
	}
	
	public void loadResponse( AnamnesePerguntaResponse resp, AnamnesePergunta pergunta ) {
		resp.setId( pergunta.getId() );
		resp.setPergunta( pergunta.getPergunta() );
		resp.setTipo( pergunta.getTipo().name() );
		resp.setTipoLabel( pergunta.getTipo().label() );
		resp.setRespostaString( pergunta.getRespostaString() );
		resp.setRespostaBoolean( pergunta.isRespostaBoolean() );
		resp.setRespostaEnum( pergunta.getRespostaEnum() ); 
	}
	
	public AnamnesePergunta novoBean( Anamnese anamnese ) {
		AnamnesePergunta pergunta = new AnamnesePergunta();
		pergunta.setAnamnese( anamnese );
		return pergunta;
	}
	
	public AnamnesePerguntaResponse novoResponse() {
		return new AnamnesePerguntaResponse();
	}
	
}
