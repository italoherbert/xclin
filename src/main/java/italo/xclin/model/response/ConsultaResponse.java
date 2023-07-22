package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaResponse {

	private Long id;
	
	private boolean paga;
	
	private boolean retorno;
	
	private double valor;
	
	private boolean concluida;
	
	private Long especialidadeId;
	
	private String especialidadeNome;
	
}
