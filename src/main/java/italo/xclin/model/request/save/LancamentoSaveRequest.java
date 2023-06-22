package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LancamentoSaveRequest {

	private String tipo;
	
	private double valor;
	
	private String observacoes;
		
}
