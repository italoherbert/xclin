package italo.scm.model.response.load.outros;

import java.util.List;

import italo.scm.model.response.ProfissionalResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalClinicaVinculosLoadResponse {

	private ProfissionalResponse profissional;
	
	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;	
	
}
