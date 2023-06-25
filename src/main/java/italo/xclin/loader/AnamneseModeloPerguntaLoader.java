package italo.xclin.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.PerguntaTipoEnumManager;
import italo.xclin.model.AnamneseModelo;
import italo.xclin.model.AnamneseModeloPergunta;
import italo.xclin.model.request.save.AnamneseModeloPerguntaSaveRequest;
import italo.xclin.model.response.AnamneseModeloPerguntaResponse;
import italo.xclin.model.response.load.edit.AnamneseModeloPerguntaEditLoadResponse;
import italo.xclin.model.response.load.reg.AnamneseModeloPerguntaRegLoadResponse;

@Component
public class AnamneseModeloPerguntaLoader {
	
	@Autowired
	private PerguntaTipoEnumManager perguntaTipoEnumManager;
	
	public void loadBean( AnamneseModeloPergunta pergunta, AnamneseModeloPerguntaSaveRequest request ) {
		pergunta.setPergunta( request.getPergunta() );
		pergunta.setTipo( perguntaTipoEnumManager.getEnum( request.getTipo() ) );
		pergunta.setEnumValues( request.getEnumValues() );
	}
	
	public void loadResponse( AnamneseModeloPerguntaResponse resp, AnamneseModeloPergunta pergunta ) {
		resp.setId( pergunta.getId() );
		resp.setPergunta( pergunta.getPergunta() );
		resp.setTipo( pergunta.getTipo().name() );
		resp.setTipoLabel( pergunta.getTipo().label() );
		resp.setEnumValues( pergunta.getEnumValues() );
	}
	
	public void loadRegResponse( AnamneseModeloPerguntaRegLoadResponse resp ) {
		resp.setPerguntaTipos( perguntaTipoEnumManager.tipoResponses() ); 
	}
	
	public void loadEditResponse( AnamneseModeloPerguntaEditLoadResponse resp ) {
		resp.setPerguntaTipos( perguntaTipoEnumManager.tipoResponses() ); 
	}
	
	public AnamneseModeloPergunta novoBean( AnamneseModelo anModelo) {
		AnamneseModeloPergunta pergunta = new AnamneseModeloPergunta();
		pergunta.setAnamneseModelo( anModelo ); 
		return pergunta;
	}
	
	public AnamneseModeloPerguntaResponse novoResponse() {
		return new AnamneseModeloPerguntaResponse();
	}
			
	public AnamneseModeloPerguntaEditLoadResponse novoEditResponse( 
			AnamneseModeloPerguntaResponse pergunta ) {
		
		AnamneseModeloPerguntaEditLoadResponse resp = new AnamneseModeloPerguntaEditLoadResponse();
		resp.setPergunta( pergunta );
		return resp;		
	}
	
	public AnamneseModeloPerguntaRegLoadResponse novoRegResponse() {		
		return new AnamneseModeloPerguntaRegLoadResponse();		
	}
	
}
