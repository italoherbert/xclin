package italo.scm.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaResponse {

	private Long id;
	
	private boolean retorno;
	
	private boolean paga;
	
	private double valor;

	private String status;

	private String turno;
	
	private String statusLabel;
	
	private String turnoLabel;

	private String observacoes;

	private String dataHoraAgendamento;
			
	private int tempoEstimado;
	
	private Long pacienteId;
	
	private String pacienteNome;
	
	private Long clinicaId;
	
	private String clinicaNome;
		
}
