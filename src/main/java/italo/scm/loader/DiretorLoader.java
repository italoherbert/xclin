package italo.scm.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Diretor;
import italo.scm.model.Usuario;
import italo.scm.model.request.save.DiretorSaveRequest;
import italo.scm.model.response.DiretorResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.detalhes.DiretorDetalhesLoadResponse;
import italo.scm.model.response.load.tela.NaoAdminDiretorTelaLoadResponse;

@Component
public class DiretorLoader {
	
	public void loadBean( Diretor d, DiretorSaveRequest request ) {
		d.setNome( request.getNome() );
	}
	
	public void loadResponse( DiretorResponse resp, Diretor d ) throws LoaderException {
		resp.setId( d.getId() );
		resp.setNome( d.getNome() );
	}
							
	public Diretor novoBean( Usuario u ) {
		Diretor d = new Diretor();
		d.setUsuario( u );
		return d;
	}
	
	public DiretorResponse novoResponse( UsuarioResponse uresp ) {
		DiretorResponse resp = new DiretorResponse();
		resp.setUsuario( uresp );
		return resp;
	}
	
	public DiretorDetalhesLoadResponse novoDetalhesResponse( 
			DiretorResponse diretor, List<String> clinicas ) {
		DiretorDetalhesLoadResponse resp = new DiretorDetalhesLoadResponse();
		resp.setDiretor( diretor );
		resp.setClinicas( clinicas ); 
		return resp;
	}
	
	public NaoAdminDiretorTelaLoadResponse novoNaoAdminTelaResponse(
			List<Long> clinicasIDs,
			List<String> clinicasNomes ) {
		NaoAdminDiretorTelaLoadResponse resp = new NaoAdminDiretorTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
}

