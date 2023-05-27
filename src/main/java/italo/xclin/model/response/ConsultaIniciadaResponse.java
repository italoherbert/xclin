package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaIniciadaResponse {
	
	private ConsultaResponse consulta;
	
	private boolean consultaIniciada;
	
}
