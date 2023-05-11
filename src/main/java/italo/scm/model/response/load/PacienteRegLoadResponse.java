package italo.scm.model.response.load;

import java.util.List;

import italo.scm.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PacienteRegLoadResponse {

	private List<TipoResponse> sexos;
	
	private List<TipoResponse> nacionalidades;
	
	private List<TipoResponse> estadosCivis;
	
}
