package italo.scm.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalSaveRequest {

	private String nome;
			
	private String funcao;
	
	private UsuarioSaveRequest usuario;
	
}
