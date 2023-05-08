package italo.scm.model.response.edit;

import java.util.List;

import italo.scm.model.response.ClinicaResponse;
import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.UFResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClinicaEditResponse {
	
	private ClinicaResponse clinica;

	private List<UFResponse> ufs;

	private List<MunicipioResponse> municipios;
	
}
