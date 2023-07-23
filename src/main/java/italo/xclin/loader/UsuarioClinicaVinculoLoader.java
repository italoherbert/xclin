package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.model.Clinica;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioClinicaVinculo;
import italo.xclin.model.response.UsuarioClinicaVinculoResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.vinculos.UsuarioClinicaVinculosLoadResponse;

@Component
public class UsuarioClinicaVinculoLoader {
	
	public void loadResponse( UsuarioClinicaVinculoResponse resp, UsuarioClinicaVinculo vinculo ) {
		resp.setId( vinculo.getId() ); 
	}
	
	public UsuarioClinicaVinculo novoBean( Usuario u, Clinica c ) {
		UsuarioClinicaVinculo vinculo = new UsuarioClinicaVinculo();
		vinculo.setUsuario( u ); 
		vinculo.setClinica( c );
		return vinculo;
	}
	
	public UsuarioClinicaVinculoResponse novoResponse( Usuario u, Clinica c ) {
		UsuarioClinicaVinculoResponse resp = new UsuarioClinicaVinculoResponse();
		
		resp.setUsuarioId( u.getId() );
		resp.setUsuarioUsername( u.getUsername() );

		resp.setClinicaId( c.getId() );
		resp.setClinicaNome( c.getNome() );
		
		return resp;
	}
	
	public UsuarioClinicaVinculosLoadResponse novoVinculosResponse(
			UsuarioResponse uresp, 
			List<UsuarioClinicaVinculoResponse> vinculos ) {
		UsuarioClinicaVinculosLoadResponse resp = new UsuarioClinicaVinculosLoadResponse();
		resp.setUsuario( uresp );
		resp.setVinculos( vinculos ); 
		return resp;
	}
	
}
