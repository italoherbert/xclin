package italo.xclin.model.response.load.detalhes;

import italo.xclin.model.response.ClinicaResponse;
import italo.xclin.model.response.MunicipioResponse;
import italo.xclin.model.response.UFResponse;
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
