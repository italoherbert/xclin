package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.model.Clinica;
import italo.xclin.model.ClinicaExame;
import italo.xclin.model.request.save.ClinicaExameSaveRequest;
import italo.xclin.model.response.ClinicaExameResponse;
import italo.xclin.model.response.load.edit.ClinicaExameEditLoadResponse;
import italo.xclin.model.response.load.reg.ClinicaExameRegLoadResponse;
import italo.xclin.model.response.load.tela.ClinicaExameTelaLoadResponse;

@Component
public class ClinicaExameLoader {

	public void loadBean( ClinicaExame exame, ClinicaExameSaveRequest request ) {
		exame.setNome( request.getNome() );
		exame.setDescricao( request.getDescricao() );
		exame.setValor( request.getValor() );
	}
	
	public void loadResponse( ClinicaExameResponse resp, ClinicaExame exame ) {
		resp.setId( exame.getId() );
		resp.setNome( exame.getNome() );
		resp.setDescricao( exame.getDescricao() );
		resp.setValor( exame.getValor() ); 
	}
	
	public ClinicaExame novoBean( Clinica clinica ) {
		ClinicaExame exame = new ClinicaExame();
		exame.setClinica( clinica );
		return exame;
	}
	
	public ClinicaExameResponse novoResponse() {
		return new ClinicaExameResponse();
	}
	
	public ClinicaExameTelaLoadResponse novoTelaResponse(
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		ClinicaExameTelaLoadResponse resp = new ClinicaExameTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public ClinicaExameRegLoadResponse novoRegResponse(
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		ClinicaExameRegLoadResponse resp = new ClinicaExameRegLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public ClinicaExameEditLoadResponse novoEditResponse( 
			ClinicaExameResponse eresp,
			List<Long> clinicasIDs,
			List<String> clinicasNomes, 
			Clinica clinica ) {
		
		ClinicaExameEditLoadResponse resp = new ClinicaExameEditLoadResponse();
		resp.setExame( eresp );
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes );
		
		resp.setClinicaId( clinica.getId() );
		resp.setClinicaNome( clinica.getNome() );
		
		return resp;
	}
	
}
