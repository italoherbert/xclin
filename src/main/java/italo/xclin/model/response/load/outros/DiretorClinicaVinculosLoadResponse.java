package italo.xclin.model.response.load.outros;

import java.util.List;

import italo.xclin.model.response.DiretorResponse;
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
