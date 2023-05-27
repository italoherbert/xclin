package italo.xclin.model.response;

import italo.xclin.enums.tipos.ProfissionalFuncao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalResponse {

	private Long id;
	
	private String nome;
			
	private ProfissionalFuncao funcao;
	
	private String funcaoLabel;
	
	private UsuarioResponse usuario;
	
}
