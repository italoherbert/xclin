package italo.scm.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Clinica;
import italo.scm.model.Recepcionista;
import italo.scm.model.Usuario;
import italo.scm.model.request.save.RecepcionistaSaveRequest;
import italo.scm.model.response.RecepcionistaResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.RecepcionistaEditLoadResponse;
import italo.scm.model.response.load.RecepcionistaRegLoadResponse;

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
	
	public RecepcionistaResponse novoResponse( 
			UsuarioResponse uresp, Long clinicaId, String clinicaNome ) {
		RecepcionistaResponse resp = new RecepcionistaResponse();
		resp.setUsuario( uresp );
		resp.setClinicaId( clinicaId );
		resp.setClinicaNome( clinicaNome ); 
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
	
}
