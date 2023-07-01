package italo.xclin.loader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.ProfissionalFuncaoEnumManager;
import italo.xclin.exception.LoaderException;
import italo.xclin.model.Profissional;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.save.ProfissionalSaveRequest;
import italo.xclin.model.response.ProfissionalResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.detalhes.ProfissionalDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.ProfissionalEspecialidadeSaveLoadResponse;
import italo.xclin.model.response.load.edit.ProfissionalEditLoadResponse;
import italo.xclin.model.response.load.reg.ProfissionalRegLoadResponse;
import italo.xclin.model.response.load.tela.NaoAdminProfissionalTelaLoadResponse;

@Component
public class ProfissionalLoader {
	
	@Autowired
	private ProfissionalFuncaoEnumManager profissionalFuncaoEnumManager;
	
	public void loadBean( Profissional p, ProfissionalSaveRequest request ) {
		p.setNome( request.getNome() );
		p.setFuncao( profissionalFuncaoEnumManager.getEnum( request.getFuncao() ) ); 
	}
		
	public void loadResponse( ProfissionalResponse resp, Profissional p ) throws LoaderException {
		resp.setId( p.getId() );
		resp.setNome( p.getNome() );
		resp.setFuncao( p.getFuncao() ); 
		resp.setFuncaoLabel( p.getFuncao().label() ); 
	}
		
	public void loadRegResponse( ProfissionalRegLoadResponse resp ) throws LoaderException {
		resp.setFuncoes( profissionalFuncaoEnumManager.tipoResponses() ); 
	}
	
	public void loadEditResponse( ProfissionalEditLoadResponse resp ) throws LoaderException {
		resp.setFuncoes( profissionalFuncaoEnumManager.tipoResponses() ); 
	}
							
	public Profissional novoBean( Usuario u ) {
		Profissional p = new Profissional();
		p.setUsuario( u );
		return p;
	}
	
	public ProfissionalResponse novoResponse( UsuarioResponse uresp ) {
		ProfissionalResponse resp = new ProfissionalResponse();
		resp.setUsuario( uresp );
		return resp;
	}
	
	public ProfissionalRegLoadResponse novoRegLoadResponse() {
		return new ProfissionalRegLoadResponse();
	}
	
	public ProfissionalEditLoadResponse novoEditLoadResponse( ProfissionalResponse presp ) {
		ProfissionalEditLoadResponse resp = new ProfissionalEditLoadResponse();
		resp.setProfissional( presp );
		return resp;
	}
	
	public ProfissionalDetalhesLoadResponse novoDetalhesResponse( 
			ProfissionalResponse profissional, 
			List<String> clinicas, 			 
			List<String> especialidades ) {
		ProfissionalDetalhesLoadResponse resp = new ProfissionalDetalhesLoadResponse();
		resp.setProfissional( profissional );
		resp.setClinicas( clinicas ); 
		resp.setEspecialidades( especialidades ); 
		return resp;
	}
	
	public NaoAdminProfissionalTelaLoadResponse novoNaoAdminTelaResponse(
			List<Long> clinicasIDs, List<String> clinicassNomes ) {
		NaoAdminProfissionalTelaLoadResponse resp = new NaoAdminProfissionalTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicassNomes );
		return resp;
	}
	
	public ProfissionalEspecialidadeSaveLoadResponse novoContaEspecialidadeSaveResponse(
			List<Long> especialidadesIDs,
			List<String> especialidadesNomes,
			List<Boolean> especialidadesVinculadas ) {
		ProfissionalEspecialidadeSaveLoadResponse resp = new ProfissionalEspecialidadeSaveLoadResponse();
		resp.setEspecialidadesIDs( especialidadesIDs );
		resp.setEspecialidadesNomes( especialidadesNomes );
		resp.setEspecialidadesVinculadas( especialidadesVinculadas ); 
		return resp;
	}
	
}


