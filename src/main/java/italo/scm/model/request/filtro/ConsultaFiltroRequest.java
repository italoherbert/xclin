package italo.scm.model.request.filtro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaFiltroRequest {

	private String dataConsulta;
	
	private boolean incluirRetorno;
	
	private boolean incluirCompleta;
	
	private boolean incluirRegistrada;
	
	private boolean incluirPaga;
	
	private boolean incluirCancelada;
	
	private boolean incluirFinalizada;
	
}
