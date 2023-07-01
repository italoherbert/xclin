package italo.xclin.model.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AtendimentoResponse {

	private Long id;
	
	private String status;

	private String turno;
	
	private String statusLabel;
	
	private String turnoLabel;

	private String observacoes;

	private String dataAgendamento;
	
	private String dataAtendimento;
	
	private String dataFinalizacao;
	
	private String dataSaveObservacoes;
				
	private Long pacienteId;
	
	private String pacienteNome;
		
	private Long clinicaId;
	
	private String clinicaNome;
	
	private Long profissionalId;
	
	private String profissionalNome;
	
	private Long especialidadeId;
	
	private String especialidadeNome;
	
	private boolean pacienteAnamneseCriada;
	
	private ConsultaResponse consulta;
	
	private List<ExameItemResponse> exames;
		
}
