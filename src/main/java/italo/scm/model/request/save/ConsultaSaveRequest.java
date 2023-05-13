package italo.scm.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaSaveRequest {

	private String descricao;
	
	private String dataConsulta;
	
	private boolean retorno;
		
	private int tempoEstimado;
	
	private double valor;
	
}
