package italo.xclin.model.response.load.edit;

import italo.xclin.model.response.ExameResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExameEditLoadResponse {

	private ExameResponse exame;
	
	private Long clinicaId;
	
	private String clinicaNome;
		
}
