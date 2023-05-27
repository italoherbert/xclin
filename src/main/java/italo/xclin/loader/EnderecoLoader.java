package italo.xclin.loader;

import org.springframework.stereotype.Component;

import italo.xclin.exception.LoaderException;
import italo.xclin.model.Endereco;
import italo.xclin.model.request.save.EnderecoSaveRequest;
import italo.xclin.model.response.EnderecoResponse;

@Component
public class EnderecoLoader {
	
	public void loadBean( Endereco e, EnderecoSaveRequest request ) throws LoaderException {
		e.setLogradouro( request.getLogradouro() );
		e.setNumero( request.getNumero() );
		e.setBairro( request.getBairro() );
		e.setCodigoMunicipio( request.getCodigoMunicipio() );
		e.setCodigoUf( request.getCodigoUf() );		
	}
	
	public void loadResponse( EnderecoResponse resp, Endereco e ) throws LoaderException {		
		resp.setId( e.getId() );
		resp.setLogradouro( e.getLogradouro() );
		resp.setNumero( e.getNumero() );
		resp.setBairro( e.getBairro() );
		resp.setCodigoMunicipio( e.getCodigoMunicipio() );
		resp.setCodigoUf( e.getCodigoUf() ); 
	}
	
	public Endereco novoBean() {
		return new Endereco();
	}
	
	public EnderecoResponse novoResponse() {
		return new EnderecoResponse();		
	}
	
}
