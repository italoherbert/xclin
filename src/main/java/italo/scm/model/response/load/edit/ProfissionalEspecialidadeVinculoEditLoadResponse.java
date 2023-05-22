package italo.scm.model.response.load.edit;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalEspecialidadeVinculoEditLoadResponse {

	private List<Long> especialidadesIDs;
	
	private List<String> especialidadesNomes;
	
}
