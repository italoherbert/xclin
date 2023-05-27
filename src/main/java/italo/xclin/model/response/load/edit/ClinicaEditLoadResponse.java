package italo.xclin.model.response.load.edit;

import java.util.List;

import italo.xclin.model.response.ClinicaResponse;
import italo.xclin.model.response.MunicipioResponse;
import italo.xclin.model.response.UFResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClinicaEditLoadResponse {
	
	private ClinicaResponse clinica;

	private List<UFResponse> ufs;

	private List<MunicipioResponse> municipios;
	
}
