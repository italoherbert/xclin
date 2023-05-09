package italo.scm.model.response.load;

import java.util.List;

import italo.scm.model.response.DiretorResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DiretorClinicaVinculosLoadResponse {

	private DiretorResponse diretor;
	
	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
	
}
