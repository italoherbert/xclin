package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProcedimentoSaveRequest {

	private String nome;
	
	private String descricao;
	
	private double valor;
	
}
