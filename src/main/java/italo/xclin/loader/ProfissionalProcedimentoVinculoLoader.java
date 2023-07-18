package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.model.Procedimento;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalProcedimentoVinculo;
import italo.xclin.model.request.save.ProfissionalProcedimentoSaveRequest;
import italo.xclin.model.response.ProfissionalProcedimentoVinculoResponse;
import italo.xclin.model.response.load.edit.ProfissionalProcedimentoSaveLoadResponse;

@Component
public class ProfissionalProcedimentoVinculoLoader {

	public void loadBean( ProfissionalProcedimentoVinculo vinculo, ProfissionalProcedimentoSaveRequest request ) {
		vinculo.setProcedimentoValor( request.getProcedimentoValor() );
	}
	
	public void loadResponse( ProfissionalProcedimentoVinculoResponse resp, ProfissionalProcedimentoVinculo vinculo ) {
		resp.setId( vinculo.getId() );
		resp.setProcedimentoValor( vinculo.getProcedimentoValor() ); 
	}
	
	public ProfissionalProcedimentoVinculo novoBean( Profissional p, Procedimento proc ) {
		ProfissionalProcedimentoVinculo vinculo = new ProfissionalProcedimentoVinculo();
		vinculo.setProfissional( p ); 
		vinculo.setProcedimento( proc );
		return vinculo;
	}
	
	public ProfissionalProcedimentoVinculoResponse novoResponse( Procedimento p ) {
		ProfissionalProcedimentoVinculoResponse resp = new ProfissionalProcedimentoVinculoResponse();
		resp.setProcedimentoId( p.getId() ); 
		resp.setProcedimentoNome( p.getNome() ); 
		return resp;
	}
	
	public ProfissionalProcedimentoSaveLoadResponse novoSaveLoadResponse( 
			List<Long> procedimentosIDs,
			List<String> procedimentosNome,
			List<Boolean> procedimentosVinculados ) {
		
		ProfissionalProcedimentoSaveLoadResponse resp = new ProfissionalProcedimentoSaveLoadResponse();			
		resp.setProcedimentosIDs( procedimentosIDs );
		resp.setProcedimentosNomes( procedimentosNome );
		resp.setProcedimentosVinculados( procedimentosVinculados ); 
		return resp;
	}
	
}
