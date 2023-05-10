package italo.scm.model.response.load;

import java.util.List;

import italo.scm.model.response.RecepcionistaResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecepcionistaEditLoadResponse {

	private RecepcionistaResponse recepcionista;
	
	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
	
}
