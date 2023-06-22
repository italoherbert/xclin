package italo.xclin.loader;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.PerguntaTipoEnumManager;
import italo.xclin.logica.Converter;
import italo.xclin.model.AnamneseModelo;
import italo.xclin.model.Profissional;
import italo.xclin.model.request.save.AnamneseModeloSaveRequest;
import italo.xclin.model.response.AnamneseModeloPerguntaResponse;
import italo.xclin.model.response.AnamneseModeloResponse;
import italo.xclin.model.response.load.detalhes.AnamneseModeloDetalhesLoadResponse;
import italo.xclin.model.response.load.outros.AnamneseModeloPerguntasLoadResponse;

@Component
public class AnamneseModeloLoader {

	@Autowired
	private PerguntaTipoEnumManager perguntaTipoEnumManager;
	
	@Autowired
	private Converter converter;
	
	public void loadBean( AnamneseModelo anamnese, AnamneseModeloSaveRequest request ) {
		anamnese.setNome( request.getNome() );
		anamnese.setDataCriacao( new Date() );
	}
	
	public void loadResponse( AnamneseModeloResponse resp, AnamneseModelo anamnese ) {
		resp.setId( anamnese.getId() );
		resp.setNome( anamnese.getNome() );
		resp.setDataCriacao( converter.dataToString( anamnese.getDataCriacao() ) );
	}
			
	public void loadDetalhesResponse( AnamneseModeloDetalhesLoadResponse resp ) {
		resp.setPerguntaTipos( perguntaTipoEnumManager.tipoResponses() );
	}
	
	public void loadPerguntasResponse( AnamneseModeloPerguntasLoadResponse resp ) {
		resp.setPerguntaTipos( perguntaTipoEnumManager.tipoResponses() ); 
	}
	
	public AnamneseModelo novoBean( Profissional profissional ) {
		AnamneseModelo modelo = new AnamneseModelo();
		modelo.setProfissional( profissional );
		return modelo;
	}
	
	public AnamneseModeloResponse novoResponse() {
		return new AnamneseModeloResponse();
	}
	
	public AnamneseModeloDetalhesLoadResponse novoDetalhesResponse(
			AnamneseModeloResponse anamneseModelo,
			List<AnamneseModeloPerguntaResponse> perguntas ) {
		
		AnamneseModeloDetalhesLoadResponse resp = new AnamneseModeloDetalhesLoadResponse();
		resp.setAnamneseModelo( anamneseModelo );
		resp.setPerguntas( perguntas ); 
		return resp;		
	}
	
	public AnamneseModeloPerguntasLoadResponse novoPerguntasResponse( 
			AnamneseModeloResponse anamneseModelo,
			List<AnamneseModeloPerguntaResponse> perguntas ) {
		
		AnamneseModeloPerguntasLoadResponse resp = new AnamneseModeloPerguntasLoadResponse();
		resp.setAnamneseModelo( anamneseModelo );
		resp.setPerguntas( perguntas ); 
		return resp;		
	}
		
}
