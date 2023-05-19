package italo.scm.loader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.enums.ProfissionalFuncaoEnumManager;
import italo.scm.exception.LoaderException;
import italo.scm.model.Profissional;
import italo.scm.model.Usuario;
import italo.scm.model.request.save.ProfissionalSaveRequest;
import italo.scm.model.response.ProfissionalResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.NaoAdminProfissionalTelaLoadResponse;
import italo.scm.model.response.load.ProfissionalDetalhesLoadResponse;
import italo.scm.model.response.load.ProfissionalEditLoadResponse;
import italo.scm.model.response.load.ProfissionalRegLoadResponse;

@Component
public class ProfissionalLoader {
	
	@Autowired
	private ProfissionalFuncaoEnumManager profissionalFuncaoEnumManager;
	
	public void loadBean( Profissional p, ProfissionalSaveRequest request ) {
		p.setNome( request.getNome() );
		p.setValorConsulta( request.getValorConsulta() );
		p.setFuncao( profissionalFuncaoEnumManager.getEnum( request.getFuncao() ) ); 
	}

	public void loadParcialBean( Profissional p, ProfissionalSaveRequest request ) {
		p.setNome( request.getNome() );
		p.setFuncao( profissionalFuncaoEnumManager.getEnum( request.getFuncao() ) );
	}
	
	public void loadResponse( ProfissionalResponse resp, Profissional p ) throws LoaderException {
		resp.setId( p.getId() );
		resp.setNome( p.getNome() );
		resp.setValorConsulta( p.getValorConsulta() );
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
			ProfissionalResponse profissional, List<String> clinicas ) {
		ProfissionalDetalhesLoadResponse resp = new ProfissionalDetalhesLoadResponse();
		resp.setProfissional( profissional );
		resp.setClinicas( clinicas ); 
		return resp;
	}
	
	public NaoAdminProfissionalTelaLoadResponse novoNaoAdminTelaResponse(
			List<Long> clinicasIDs, List<String> clinicassNomes ) {
		NaoAdminProfissionalTelaLoadResponse resp = new NaoAdminProfissionalTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicassNomes );
		return resp;
	}

}


