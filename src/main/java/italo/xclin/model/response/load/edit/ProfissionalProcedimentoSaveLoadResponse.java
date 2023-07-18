package italo.xclin.model.response.load.edit;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalProcedimentoSaveLoadResponse {

	private List<Long> procedimentosIDs;
	
	private List<String> procedimentosNomes;
	
	private List<Boolean> procedimentosVinculados;
	
}
