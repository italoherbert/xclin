package italo.xclin.loader;

import java.util.List;

import org.springframework.stereotype.Component;

import italo.xclin.model.Paciente;
import italo.xclin.model.PacienteAnexo;
import italo.xclin.model.request.save.PacienteAnexoSaveRequest;
import italo.xclin.model.response.PacienteAnexoArquivoResponse;
import italo.xclin.model.response.PacienteAnexoResponse;
import italo.xclin.model.response.load.tela.PacienteAnexoTelaLoadResponse;

@Component
public class PacienteAnexoLoader {

	public void loadBean( PacienteAnexo anexo, PacienteAnexoSaveRequest request ) {
		anexo.setNome( request.getNome() );
		anexo.setArquivo( request.getArquivo() ); 
	}
		
	public void loadResponse( PacienteAnexoResponse resp, PacienteAnexo anexo ) {
		resp.setId( anexo.getId() );
		resp.setNome( anexo.getNome() );
	}
	
	public void loadResponse( PacienteAnexoArquivoResponse resp, PacienteAnexo anexo ) {
		resp.setId( anexo.getId() );
		resp.setNome( anexo.getNome() );
		resp.setArquivo( anexo.getArquivo() ); 
	}
	
	public PacienteAnexo novoBean( Paciente paciente ) {
		PacienteAnexo anexo = new PacienteAnexo();
		anexo.setPaciente( paciente ); 
		return anexo;		
	}
		
	public PacienteAnexoResponse novoResponse() {
		return new PacienteAnexoResponse();
	}

	public PacienteAnexoArquivoResponse novoArquivoResponse() {
		return new PacienteAnexoArquivoResponse();
	}
	
	public PacienteAnexoTelaLoadResponse novoTelaLoadResponse( 
			Paciente paciente, List<PacienteAnexoResponse> anexosListaResp ) {
		
		PacienteAnexoTelaLoadResponse resp = new PacienteAnexoTelaLoadResponse();
		resp.setPacienteId( paciente.getId() );
		resp.setPacienteNome( paciente.getNome() );
		resp.setAnexos( anexosListaResp ); 
		return resp;		
	}
		
}
