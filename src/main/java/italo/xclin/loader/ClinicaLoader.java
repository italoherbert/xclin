package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.exception.LoaderException;
import italo.xclin.model.Clinica;
import italo.xclin.model.Endereco;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.save.ClinicaSaveRequest;
import italo.xclin.model.response.ClinicaResponse;
import italo.xclin.model.response.EnderecoResponse;
import italo.xclin.model.response.MunicipioResponse;
import italo.xclin.model.response.UFResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.detalhes.ClinicaDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.ClinicaEditLoadResponse;
import italo.xclin.model.response.load.reg.ClinicaRegLoadResponse;
import italo.xclin.model.response.load.tela.NaoAdminClinicaTelaLoadResponse;

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
	
	public NaoAdminClinicaTelaLoadResponse novoNaoAdminTelaResponse(
			List<Long> clinicasIDs, List<String> clinicasNomes ) {
		NaoAdminClinicaTelaLoadResponse resp = new NaoAdminClinicaTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
		
}
