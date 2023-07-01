package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.model.Exame;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalExameVinculo;
import italo.xclin.model.request.save.ProfissionalClinicaExameSaveRequest;
import italo.xclin.model.response.ProfissionalExameVinculoResponse;
import italo.xclin.model.response.load.edit.ProfissionalExameSaveLoadResponse;

@Component
public class ProfissionalExameVinculoLoader {

	public void loadBean( ProfissionalExameVinculo vinculo, ProfissionalClinicaExameSaveRequest request ) {
		vinculo.setExameValor( request.getExameValor() );
	}
	
	public void loadResponse( ProfissionalExameVinculoResponse resp, ProfissionalExameVinculo vinculo ) {
		resp.setId( vinculo.getId() ); 
		resp.setExameValor( vinculo.getExameValor() ); 
	}
	
	public ProfissionalExameVinculo novoBean( Profissional p, Exame e ) {
		ProfissionalExameVinculo vinculo = new ProfissionalExameVinculo();
		vinculo.setProfissional( p ); 
		vinculo.setExame( e );
		return vinculo;
	}
	
	public ProfissionalExameVinculoResponse novoResponse( Exame e ) {
		ProfissionalExameVinculoResponse resp = new ProfissionalExameVinculoResponse();
		resp.setExameNome( e.getNome() ); 
		return resp;
	}
	
	public ProfissionalExameSaveLoadResponse novoSaveLoadResponse( 
			List<Long> examesIDs,
			List<String> examesNome,
			List<Boolean> examesVinculados ) {
		
		ProfissionalExameSaveLoadResponse resp = new ProfissionalExameSaveLoadResponse();			
		resp.setExamesIDs( examesIDs );
		resp.setExamesNomes( examesNome );
		resp.setExamesVinculados( examesVinculados ); 
		return resp;
	}
	
}
