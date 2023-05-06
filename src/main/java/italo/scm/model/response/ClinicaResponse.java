package italo.scm.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClinicaResponse {

	private Long id;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	private EnderecoResponse endereco;
	
	private UsuarioResponse criador;
	
}
