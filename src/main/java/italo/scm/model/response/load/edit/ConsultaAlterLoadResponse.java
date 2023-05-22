package italo.scm.model.response.load.edit;

import java.util.List;

import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaAlterLoadResponse {

	private ConsultaResponse consulta;
	
	private List<TipoResponse> turnos;
	
	private List<TipoResponse> statuses;

}
