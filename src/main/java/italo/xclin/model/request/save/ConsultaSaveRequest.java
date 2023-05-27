package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaSaveRequest {
	
	private String dataAtendimento;
				
	private boolean retorno;

	private double valor;
	
	private String turno;
	
	private String observacoes;

}
