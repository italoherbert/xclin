package italo.xclin.model.response.load.edit;

import italo.xclin.model.response.ClinicaExameResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClinicaExameEditLoadResponse {

	private ClinicaExameResponse exame;
	
	private Long clinicaId;
	
	private String clinicaNome;
		
}
