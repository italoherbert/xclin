package italo.scm.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.exception.ConverterException;
import italo.scm.exception.LoaderException;
import italo.scm.logica.Converter;
import italo.scm.model.Endereco;
import italo.scm.model.Pessoa;
import italo.scm.model.request.save.PessoaSaveRequest;
import italo.scm.model.response.EnderecoResponse;
import italo.scm.model.response.PessoaResponse;

@Component
public class PessoaLoader {

	@Autowired
	private Converter converter;
	
	public void loadBean( Pessoa p, PessoaSaveRequest request ) throws LoaderException {
		p.setNome( request.getNome() );
		p.setTelefone( request.getTelefone() );
		p.setEmail( request.getEmail() );
		p.setCpf( request.getCpf() );
		p.setRg( request.getRg() );
		try {
			p.setDataNascimento( converter.stringToData( request.getDataNascimento() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		} 
	}
	
	public void loadResponse( PessoaResponse resp, Pessoa p ) throws LoaderException {
		resp.setId( p.getId() );
		resp.setNome( p.getNome() );
		resp.setTelefone( p.getTelefone() );
		resp.setEmail( p.getEmail() );
		resp.setCpf( p.getCpf() );
		resp.setRg( p.getRg() );
		resp.setDataNascimento( converter.dataToString( p.getDataNascimento() ) );
	}
							
	public Pessoa novoBean( Endereco e ) {
		Pessoa pessoa = new Pessoa();
		pessoa.setEndereco( e );
		return pessoa;
	}
	
	public PessoaResponse novoResponse( EnderecoResponse eresp ) {
		PessoaResponse resp = new PessoaResponse();
		resp.setEndereco( eresp );
		return resp;
	}
	
}
