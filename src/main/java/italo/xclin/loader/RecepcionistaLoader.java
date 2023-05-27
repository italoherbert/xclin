package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.exception.LoaderException;
import italo.xclin.model.Clinica;
import italo.xclin.model.Recepcionista;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.save.RecepcionistaSaveRequest;
import italo.xclin.model.response.RecepcionistaResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.edit.RecepcionistaEditLoadResponse;
import italo.xclin.model.response.load.reg.RecepcionistaRegLoadResponse;
import italo.xclin.model.response.load.tela.NaoAdminRecepcionistaTelaLoadResponse;

@Component
public class RecepcionistaLoader {
	
	public void loadBean( Recepcionista r, RecepcionistaSaveRequest request ) {
		r.setNome( request.getNome() );		 
	}
	
	public void loadResponse( RecepcionistaResponse resp, Recepcionista r ) throws LoaderException {
		resp.setId( r.getId() );
		resp.setNome( r.getNome() );
	}
								
	public Recepcionista novoBean( Usuario u, Clinica c ) {
		Recepcionista r = new Recepcionista();
		r.setUsuario( u );
		r.setClinica( c );
		return r;
	}
	
	public RecepcionistaResponse novoResponse( UsuarioResponse uresp, Clinica c ) {
		RecepcionistaResponse resp = new RecepcionistaResponse();
		resp.setUsuario( uresp );
		resp.setClinicaId( c.getId() );
		resp.setClinicaNome( c.getNome() ); 
		return resp;
	}
		
	public RecepcionistaRegLoadResponse novoRegResponse( 
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		RecepcionistaRegLoadResponse resp = new RecepcionistaRegLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public RecepcionistaEditLoadResponse novoEditResponse( 
			RecepcionistaResponse rresp,
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		RecepcionistaEditLoadResponse resp = new RecepcionistaEditLoadResponse();
		resp.setRecepcionista( rresp ); 
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
	public NaoAdminRecepcionistaTelaLoadResponse novoNaoAdminTelaResponse( 
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		NaoAdminRecepcionistaTelaLoadResponse resp = new NaoAdminRecepcionistaTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
}
