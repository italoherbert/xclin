package italo.xclin.model.response.load.tela;

import java.util.List;

import italo.xclin.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AtendimentoListaFilaTelaLoadResponse {

	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
	
	private List<TipoResponse> turnos;
	
	private List<TipoResponse> statuses;
	
}
