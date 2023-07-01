package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaSaveRequest {
	
	private Long especialidadeId;
	
	private boolean retorno;
	
	private boolean paga;
		
	private double valor;
	
}
