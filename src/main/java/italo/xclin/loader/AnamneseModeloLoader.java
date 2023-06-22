package italo.xclin.loader;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.logica.Converter;
import italo.xclin.model.AnamneseModelo;
import italo.xclin.model.Profissional;
import italo.xclin.model.request.save.AnamneseModeloSaveRequest;
import italo.xclin.model.response.AnamneseModeloResponse;

@Component
public class AnamneseModeloLoader {

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
	
	public AnamneseModelo novoBean( Profissional profissional ) {
		AnamneseModelo modelo = new AnamneseModelo();
		modelo.setProfissional( profissional );
		return modelo;
	}
	
	public AnamneseModeloResponse novoResponse() {
		return new AnamneseModeloResponse();
	}
	
}
