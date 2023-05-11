package italo.scm.model.response.load;

import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.PacienteResponse;
import italo.scm.model.response.UFResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PacienteDetalhesLoadResponse {

	private PacienteResponse paciente;
	
	private UFResponse uf;
	
	private MunicipioResponse municipio;
	
}
