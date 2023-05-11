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
import italo.scm.model.response.load.ClinicaDetalhesLoadResponse;
import italo.scm.model.response.load.ClinicaEditLoadResponse;
import italo.scm.model.response.load.ClinicaRegLoadResponse;

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
	
	public ClinicaRegLoadResponse novoRegResponse( List<UFResponse> ufs ) {
		ClinicaRegLoadResponse resp = new ClinicaRegLoadResponse();
		resp.setUfs( ufs );
		return resp;
	}
	
	public ClinicaEditLoadResponse novoEditResponse( 
			ClinicaResponse clinica, 
			List<UFResponse> ufs, 
			List<MunicipioResponse> municipios ) {
		
		ClinicaEditLoadResponse resp = new ClinicaEditLoadResponse();
		resp.setClinica( clinica ); 
		resp.setUfs( ufs );
		resp.setMunicipios( municipios ); 
		return resp;
	}
	
	public ClinicaDetalhesLoadResponse novoDetalhesResponse(
			ClinicaResponse clinica, 
			UFResponse uf, 
			MunicipioResponse municipio ) {
		ClinicaDetalhesLoadResponse resp = new ClinicaDetalhesLoadResponse();
		resp.setClinica( clinica );
		resp.setUf( uf );
		resp.setMunicipio( municipio ); 
		return resp;
	}
	
}
