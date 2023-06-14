package italo.xclin.model.request.filtro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LancamentoFiltroRequest {

	private String filtroUsername;
	
	private boolean incluirUsername;
	
	private String dataInicio;
	
	private String dataFim;
}
