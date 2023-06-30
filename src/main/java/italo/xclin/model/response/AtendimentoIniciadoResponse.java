package italo.xclin.model.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AtendimentoIniciadoResponse {
	
	private AtendimentoResponse consulta;
	
	private int quantPacientesNaFila;
	
	private List<PacienteAnexoResponse> pacienteAnexos;
	
	private List<AtendimentoObservacoesResponse> historicoObservacoes;
		
	private boolean consultaIniciada;
	
}
