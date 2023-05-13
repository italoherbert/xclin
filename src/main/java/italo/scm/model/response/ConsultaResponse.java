package italo.scm.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaResponse {

	private Long id;
	
	private String descricao;
	
	private String status;

	private boolean retorno;
	
	private boolean paga;
	
	private double valor;
	
	private String dataConsulta;
		
	private int tempoEstimado;
	
	private Long pacienteId;
	
	private String pacienteNome;
	
}
