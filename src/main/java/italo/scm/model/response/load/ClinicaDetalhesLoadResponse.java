package italo.scm.model.response.load;

import italo.scm.model.response.ClinicaResponse;
import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.UFResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClinicaDetalhesLoadResponse {

	private ClinicaResponse clinica;
	
	private UFResponse uf;
	
	private MunicipioResponse municipio;
	
}
