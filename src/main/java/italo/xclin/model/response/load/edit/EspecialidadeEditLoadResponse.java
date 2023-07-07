package italo.xclin.model.response.load.edit;

import java.util.List;

import italo.xclin.model.response.EspecialidadeResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EspecialidadeEditLoadResponse {

	private EspecialidadeResponse especialidade;
	
	private Long clinicaId;
	
	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
	
}
