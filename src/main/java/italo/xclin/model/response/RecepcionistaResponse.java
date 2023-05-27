package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecepcionistaResponse {

	private Long id;
	
	private String nome;
		
	private Long clinicaId;
	
	private String clinicaNome;
	
	private UsuarioResponse usuario;

}
