package italo.scm.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaSaveRequest {
	
	private String dataAtendimento;
			
	private int tempoEstimado;
	
	private boolean retorno;

	private double valor;
	
	private String turno;
	
	private String observacoes;

}
