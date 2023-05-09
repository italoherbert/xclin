package italo.scm.loader;

import org.springframework.stereotype.Component;

import italo.scm.exception.LoaderException;
import italo.scm.model.Diretor;
import italo.scm.model.Usuario;
import italo.scm.model.request.save.DiretorSaveRequest;
import italo.scm.model.response.DiretorResponse;
import italo.scm.model.response.UsuarioResponse;

@Component
public class DiretorLoader {
	
	public void loadBean( Diretor d, DiretorSaveRequest request ) {
		d.setNome( request.getNome() );
	}
	
	public void loadResponse( DiretorResponse resp, Diretor d ) throws LoaderException {
		resp.setId( d.getId() );
		resp.setNome( d.getNome() );
	}
							
	public Diretor novoBean( Usuario u ) {
		Diretor d = new Diretor();
		d.setUsuario( u );
		return d;
	}
	
	public DiretorResponse novoResponse( UsuarioResponse uresp ) {
		DiretorResponse resp = new DiretorResponse();
		resp.setUsuario( uresp );
		return resp;
	}

}

