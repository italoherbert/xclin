package italo.xclin.model.response.load.edit;

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
	
	private String clinicaNome;
	
}
