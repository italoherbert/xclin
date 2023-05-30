package italo.xclin.model.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaIniciadaResponse {
	
	private ConsultaResponse consulta;
	
	private List<ConsultaObservacoesResponse> historicoObservacoes;
		
	private boolean consultaIniciada;
	
}
