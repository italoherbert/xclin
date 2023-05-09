package italo.scm.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalSaveRequest {

	private String nome;
	
	private long tempoConsulta;
	
	private long tempoConsultaRetorno;
	
	private double valorConsulta;
	
	private String funcao;
	
	private UsuarioSaveRequest usuario;
	
}
