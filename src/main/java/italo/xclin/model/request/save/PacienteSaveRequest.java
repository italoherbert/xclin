package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PacienteSaveRequest {
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	private String cpf;
	
	private String rg;
	
	private String dataNascimento;
	
	private String sexo;
	
	private String estadoCivil;
	
	private String nacionalidade;
	
	private String ocupacao;
	
	private String observacoes;
	
	private EnderecoSaveRequest endereco;
	
}
