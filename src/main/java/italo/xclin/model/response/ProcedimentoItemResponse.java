package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProcedimentoItemResponse {

	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private double valor;
	
	private boolean concluido;
	
}
