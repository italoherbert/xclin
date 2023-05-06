package italo.scm.loader;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Endereco;
import italo.scm.model.request.save.EnderecoSaveRequest;
import italo.scm.model.response.EnderecoResponse;

@Component
public class EnderecoLoader {

	public void loadBean( Endereco e, EnderecoSaveRequest request ) throws LoaderException {
		e.setLogradouro( request.getLogradouro() );
		e.setNumero( request.getNumero() );
		e.setBairro( request.getBairro() );
		e.setCidade( request.getCidade() );
		e.setUf( request.getUf() );
	}
	
	public void loadResponse( EnderecoResponse resp, Endereco e ) throws LoaderException {
		resp.setId( e.getId() );
		resp.setLogradouro( e.getLogradouro() );
		resp.setNumero( e.getNumero() );
		resp.setBairro( e.getBairro() );
		resp.setCidade( e.getCidade() );
		resp.setUf( e.getUf() );
	}
	
	public Endereco novoBean() {
		return new Endereco();
	}
	
	public EnderecoResponse novoResponse() {
		return new EnderecoResponse();
	}
	
}
