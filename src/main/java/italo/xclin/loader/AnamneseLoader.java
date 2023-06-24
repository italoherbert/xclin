package italo.xclin.loader;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.logica.Converter;
import italo.xclin.model.Anamnese;
import italo.xclin.model.AnamneseModelo;
import italo.xclin.model.Paciente;
import italo.xclin.model.response.AnamnesePerguntaResponse;
import italo.xclin.model.response.AnamneseResponse;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.load.edit.AnamneseEditLoadResponse;
import italo.xclin.model.response.load.reg.AnamneseRegLoadResponse;

@Component
public class AnamneseLoader {

	@Autowired
	private Converter converter;
	
	public void loadBean( Anamnese anamnese, AnamneseModelo modelo ) {
		anamnese.setNome( modelo.getNome() );
		anamnese.setDataCriacao( new Date() );
	}
	
	public void loadResponse( AnamneseResponse resp, Anamnese anamnese ) {
		resp.setId( anamnese.getId() );
		resp.setNome( anamnese.getNome() );
		resp.setDataCriacao( converter.dataToString( anamnese.getDataCriacao() ) ); 
	}
	
	public Anamnese novoBean( Paciente paciente ) {
		Anamnese anamnese = new Anamnese();
		anamnese.setPaciente( paciente ); 
		return anamnese;
	}
	
	public AnamneseResponse novoResponse( List<AnamnesePerguntaResponse> perguntas ) {
		AnamneseResponse resp = new AnamneseResponse();
		resp.setPerguntas( perguntas );
		return resp;
	}
	
	public AnamneseRegLoadResponse novoRegResponse(
			ListaResponse anamneseModelos ) {
		AnamneseRegLoadResponse resp = new AnamneseRegLoadResponse();
		resp.setAnamneseModelos( anamneseModelos ); 
		return resp;
	}
	
	public AnamneseEditLoadResponse novoEditResponse(
			AnamneseResponse aresp, ListaResponse anamneseModelos ) {
		AnamneseEditLoadResponse resp = new AnamneseEditLoadResponse();
		resp.setAnamnese( aresp );
		resp.setAnamneseModelos( anamneseModelos ); 
		return resp;
	}
	
}
