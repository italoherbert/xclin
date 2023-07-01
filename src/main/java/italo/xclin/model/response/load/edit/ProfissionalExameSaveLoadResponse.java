package italo.xclin.model.response.load.edit;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalExameSaveLoadResponse {

	private List<Long> examesIDs;
	
	private List<String> examesNomes;
	
	private List<Boolean> examesVinculados;
	
}
