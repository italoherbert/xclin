package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AtendimentoAlterSaveRequest {
	
	private double valor;

	private boolean retorno;
		
	private String status;
		
	private String observacoes;
	
}
