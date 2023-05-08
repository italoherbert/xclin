package italo.scm.loader;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Endereco;
import italo.scm.model.request.save.EnderecoSaveRequest;
import italo.scm.model.response.EnderecoResponse;
import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.UFResponse;

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
	}
	
	public Endereco novoBean() {
		return new Endereco();
	}
	
	public EnderecoResponse novoResponse( MunicipioResponse municipio, UFResponse uf ) {
		EnderecoResponse resp = new EnderecoResponse();
		resp.setMunicipio( municipio );
		resp.setUf( uf ); 
		return resp;
	}
	
}
