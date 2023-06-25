package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PacienteResponse {

	private Long id;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	private String cpf;
	
	private String rg;
	
	private String dataNascimento;
	
	private boolean anamneseCriada;
	
	private String sexo;
	
	private String estadoCivil;
	
	private String nacionalidade;
	
	private String ocupacao;
	
	private String observacoes;
	
	private Long clinicaId;
	
	private String clinicaNome;
	
	private EnderecoResponse endereco;
	
}
