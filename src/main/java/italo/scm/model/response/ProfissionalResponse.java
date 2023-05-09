package italo.scm.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalResponse {

	private Long id;
	
	private String nome;
	
	private long tempoConsulta;
	
	private long tempoConsultaRetorno;
	
	private double valorConsulta;
	
	private UsuarioResponse usuario;
	
}
