package italo.scm.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaAlterSaveRequest {
	
	private double valor;

	private boolean retorno;
	
	private boolean paga;
	
	private String status;
		
	private String observacoes;
	
}
