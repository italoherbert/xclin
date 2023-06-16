package italo.xclin.loader;

import org.springframework.stereotype.Component;

import italo.xclin.model.Clinica;
import italo.xclin.model.Usuario;
import italo.xclin.model.UsuarioClinicaVinculo;
import italo.xclin.model.response.UsuarioClinicaVinculoResponse;

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
	
}
