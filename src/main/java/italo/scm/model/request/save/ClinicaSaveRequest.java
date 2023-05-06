package italo.scm.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClinicaSaveRequest {

	private String nome;
	
	private String telefone;
	
	private String email;
	
	private EnderecoSaveRequest endereco;
	
}
