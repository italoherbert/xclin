package italo.scm.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Clinica;
import italo.scm.model.Endereco;
import italo.scm.model.Usuario;
import italo.scm.model.request.save.ClinicaSaveRequest;
import italo.scm.model.response.ClinicaResponse;
import italo.scm.model.response.EnderecoResponse;
import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.UFResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.edit.ClinicaEditResponse;
import italo.scm.model.response.reg.ClinicaRegResponse;

@Component
public class ClinicaLoader {
	
	public void loadBean( Clinica c, ClinicaSaveRequest request ) throws LoaderException {
		c.setNome( request.getNome() );
		c.setTelefone( request.getTelefone() );
		c.setEmail( request.getEmail() ); 
	}
	
	public void loadResponse( ClinicaResponse resp, Clinica c ) throws LoaderException {
		resp.setId( c.getId() );
		resp.setNome( c.getNome() );
		resp.setTelefone( c.getTelefone() );
		resp.setEmail( c.getEmail() ); 
	}
	
	public Clinica novoBean( Endereco ender, Usuario criador ) {
		Clinica clinica = new Clinica();
		clinica.setEndereco( ender );
		clinica.setCriador( criador ); 
		return clinica;
	}
	
	public ClinicaResponse novoResponse( EnderecoResponse ender, UsuarioResponse criador ) {
		ClinicaResponse resp = new ClinicaResponse();
		resp.setEndereco( ender );
		resp.setCriador( criador ); 
		return resp;
	}
	
	public ClinicaRegResponse novoRegResponse( List<UFResponse> ufs ) {
		ClinicaRegResponse resp = new ClinicaRegResponse();
		resp.setUfs( ufs );
		return resp;
	}
	
	public ClinicaEditResponse novoEditResponse( 
			ClinicaResponse clinica, 
			List<UFResponse> ufs, 
			List<MunicipioResponse> municipios ) {
		
		ClinicaEditResponse resp = new ClinicaEditResponse();
		resp.setClinica( clinica ); 
		resp.setUfs( ufs );
		resp.setMunicipios( municipios ); 
		return resp;
	}
	
}
