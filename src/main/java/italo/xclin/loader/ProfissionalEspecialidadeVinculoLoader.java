package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.model.Especialidade;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalEspecialidadeVinculo;
import italo.xclin.model.request.save.ProfissionalEspecialidadeSaveRequest;
import italo.xclin.model.response.ProfissionalEspecialidadeVinculoResponse;
import italo.xclin.model.response.load.vinculos.ProfissionalEspecialidadeVinculosLoadResponse;

@Component
public class ProfissionalEspecialidadeVinculoLoader {

	public void loadBean( ProfissionalEspecialidadeVinculo vinculo, ProfissionalEspecialidadeSaveRequest request ) {
		vinculo.setConsultaValor( request.getConsultaValor() );
	}
	
	public void loadResponse( ProfissionalEspecialidadeVinculoResponse resp, ProfissionalEspecialidadeVinculo vinculo ) {
		resp.setId( vinculo.getId() ); 
		resp.setConsultaValor( vinculo.getConsultaValor() ); 
	}
	
	public ProfissionalEspecialidadeVinculo novoBean( Profissional p, Especialidade e ) {
		ProfissionalEspecialidadeVinculo vinculo = new ProfissionalEspecialidadeVinculo();
		vinculo.setProfissional( p ); 
		vinculo.setEspecialidade( e );
		return vinculo;
	}
	
	public ProfissionalEspecialidadeVinculoResponse novoResponse( Especialidade e ) {
		ProfissionalEspecialidadeVinculoResponse resp = new ProfissionalEspecialidadeVinculoResponse();
		resp.setEspecialidade( e.getNome() ); 
		return resp;
	}
	
	public ProfissionalEspecialidadeVinculosLoadResponse novoEspecialidadeVinculosLoadResponse(
			Profissional p, 
			List<ProfissionalEspecialidadeVinculoResponse> especialidades ) {
		ProfissionalEspecialidadeVinculosLoadResponse resp = new ProfissionalEspecialidadeVinculosLoadResponse();
		resp.setProfissionalNome( p.getNome() );
		resp.setProfissionalFuncao( p.getFuncao().label() ); 
		resp.setEspecialidades( especialidades ); 
		return resp;
	}
	
}
