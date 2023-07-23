package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.model.Clinica;
import italo.xclin.model.Especialidade;
import italo.xclin.model.request.save.EspecialidadeSaveRequest;
import italo.xclin.model.response.EspecialidadeResponse;
import italo.xclin.model.response.load.edit.EspecialidadeEditLoadResponse;
import italo.xclin.model.response.load.reg.EspecialidadeRegLoadResponse;
import italo.xclin.model.response.load.tela.EspecialidadeTelaLoadResponse;

@Component
public class EspecialidadeLoader {
	
	public void loadBean( Especialidade e, EspecialidadeSaveRequest request ) {
		e.setNome( request.getNome() ) ;
	}
	
	public void loadResponse( EspecialidadeResponse resp, Especialidade e ) {
		resp.setId( e.getId() );
		resp.setNome( e.getNome() );
	}
							
	public Especialidade novoBean( Clinica clinica ) {
		Especialidade especialidade = new Especialidade();
		especialidade.setClinica( clinica );
		return especialidade;
	}
	
	public EspecialidadeResponse novoResponse() {
		return new EspecialidadeResponse();
	}
	
	public EspecialidadeTelaLoadResponse novoTelaLoadResponse( 
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		EspecialidadeTelaLoadResponse resp = new EspecialidadeTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public EspecialidadeRegLoadResponse novoRegLoadResponse( 
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		EspecialidadeRegLoadResponse resp = new EspecialidadeRegLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public EspecialidadeEditLoadResponse novoEditLoadResponse(
			Clinica clinica,
			EspecialidadeResponse eresp ) {
		
		EspecialidadeEditLoadResponse resp = new EspecialidadeEditLoadResponse();				
		resp.setEspecialidade( eresp );
		resp.setClinicaId( clinica.getId() );
		resp.setClinicaNome( clinica.getNome() );
		return resp;
	}

}

