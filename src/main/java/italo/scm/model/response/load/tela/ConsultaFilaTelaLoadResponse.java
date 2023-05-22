package italo.scm.model.response.load.tela;

import java.util.List;

import italo.scm.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaFilaTelaLoadResponse {

	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
	
	private List<TipoResponse> turnos;
	
	private List<TipoResponse> statuses;
	
}
