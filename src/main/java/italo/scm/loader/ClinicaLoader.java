package italo.scm.loader;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Clinica;
import italo.scm.model.Endereco;
import italo.scm.model.Usuario;
import italo.scm.model.request.save.ClinicaSaveRequest;
import italo.scm.model.response.ClinicaResponse;
import italo.scm.model.response.EnderecoResponse;
import italo.scm.model.response.UsuarioResponse;

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
	
}
