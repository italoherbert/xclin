package italo.xclin.model.response.load.edit;

import italo.xclin.model.response.ProcedimentoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProcedimentoEditLoadResponse {

	private ProcedimentoResponse procedimento;
	
	private Long clinicaId;
	
	private String clinicaNome;
	
}
