package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.model.Clinica;
import italo.xclin.model.Exame;
import italo.xclin.model.request.save.ClinicaExameSaveRequest;
import italo.xclin.model.response.ExameResponse;
import italo.xclin.model.response.load.edit.ExameEditLoadResponse;
import italo.xclin.model.response.load.reg.ExameRegLoadResponse;
import italo.xclin.model.response.load.tela.ExameTelaLoadResponse;

@Component
public class ExameLoader {

	public void loadBean( Exame exame, ClinicaExameSaveRequest request ) {
		exame.setNome( request.getNome() );
		exame.setDescricao( request.getDescricao() );
		exame.setValor( request.getValor() );
	}
	
	public void loadResponse( ExameResponse resp, Exame exame ) {
		resp.setId( exame.getId() );
		resp.setNome( exame.getNome() );
		resp.setDescricao( exame.getDescricao() );
		resp.setValor( exame.getValor() ); 
	}
	
	public Exame novoBean( Clinica clinica ) {
		Exame exame = new Exame();
		exame.setClinica( clinica );
		return exame;
	}
	
	public ExameResponse novoResponse() {
		return new ExameResponse();
	}
	
	public ExameTelaLoadResponse novoTelaResponse(
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		ExameTelaLoadResponse resp = new ExameTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public ExameRegLoadResponse novoRegResponse(
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		ExameRegLoadResponse resp = new ExameRegLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public ExameEditLoadResponse novoEditResponse( 
			ExameResponse eresp,
			Clinica clinica ) {
		
		ExameEditLoadResponse resp = new ExameEditLoadResponse();
		resp.setExame( eresp );
		
		resp.setClinicaId( clinica.getId() );
		resp.setClinicaNome( clinica.getNome() );
		
		return resp;
	}
	
}
