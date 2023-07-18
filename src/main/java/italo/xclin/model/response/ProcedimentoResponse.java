package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProcedimentoResponse {

	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private double valor;
	
}
