package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.model.Clinica;
import italo.xclin.model.Procedimento;
import italo.xclin.model.request.save.ProcedimentoSaveRequest;
import italo.xclin.model.response.ProcedimentoResponse;
import italo.xclin.model.response.load.edit.ProcedimentoEditLoadResponse;
import italo.xclin.model.response.load.reg.ProcedimentoRegLoadResponse;
import italo.xclin.model.response.load.tela.ProcedimentoTelaLoadResponse;

@Component
public class ProcedimentoLoader {

	public void loadBean( Procedimento p, ProcedimentoSaveRequest request ) {
		p.setNome( request.getNome() ); 
		p.setDescricao( request.getDescricao() );
		p.setValor( request.getValor() ); 
	}
	
	public void loadResponse( ProcedimentoResponse resp, Procedimento p ) {
		resp.setId( p.getId() );
		resp.setNome( p.getNome() );
		resp.setDescricao( p.getDescricao() );
		resp.setValor( p.getValor() ); 
	}
	
	public Procedimento novoBean( Clinica clinica ) {
		Procedimento procedimento = new Procedimento();
		procedimento.setClinica( clinica );
		return procedimento;
	}
	
	public ProcedimentoResponse novoResponse() {
		return new ProcedimentoResponse();
	}
	
	public ProcedimentoTelaLoadResponse novoTelaResponse(
			List<Long> clinicasIDs, 
			List<String> clinicasNomes ) {
		
		ProcedimentoTelaLoadResponse resp = new ProcedimentoTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;		
	}
	
	public ProcedimentoRegLoadResponse novoRegResponse(
			List<Long> clinicasIDs, 
			List<String> clinicasNomes ) {
		
		ProcedimentoRegLoadResponse resp = new ProcedimentoRegLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;	
	}
	
	public ProcedimentoEditLoadResponse novoEditResponse(
			ProcedimentoResponse presp,
			List<Long> clinicasIDs, 
			List<String> clinicasNomes ) {
		
		ProcedimentoEditLoadResponse resp = new ProcedimentoEditLoadResponse();
		resp.setProcedimento( presp ); 
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;	
	}
	
}
