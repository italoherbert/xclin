package italo.scm.model.response;

import italo.scm.enums.tipos.ProfissionalFuncao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalResponse {

	private Long id;
	
	private String nome;
		
	private double valorConsulta;
	
	private ProfissionalFuncao funcao;
	
	private String funcaoLabel;
	
	private UsuarioResponse usuario;
	
}
