package italo.scm.model.request.filtro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaResumidaFiltroRequest {

	private String data;
	
	private String turno;
	
	private String status;
	
}