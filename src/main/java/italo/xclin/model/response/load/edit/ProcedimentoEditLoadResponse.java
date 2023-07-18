package italo.xclin.model.response.load.edit;

import java.util.List;

import italo.xclin.model.response.ProcedimentoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProcedimentoEditLoadResponse {

	private ProcedimentoResponse procedimento;
	
	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
	
}
