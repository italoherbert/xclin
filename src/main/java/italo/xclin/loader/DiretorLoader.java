package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.exception.LoaderException;
import italo.xclin.model.Diretor;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.save.DiretorSaveRequest;
import italo.xclin.model.response.DiretorResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.detalhes.DiretorDetalhesLoadResponse;
import italo.xclin.model.response.load.tela.NaoAdminDiretorTelaLoadResponse;

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

