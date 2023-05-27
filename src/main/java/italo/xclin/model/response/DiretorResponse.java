package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DiretorResponse {

	private Long id;
	
	private String nome;
	
	private UsuarioResponse usuario;
	
}
