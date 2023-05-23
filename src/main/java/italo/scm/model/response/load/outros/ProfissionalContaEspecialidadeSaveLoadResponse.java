package italo.scm.model.response.load.outros;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalContaEspecialidadeSaveLoadResponse {

	private List<Long> especialidadesIDs;
	
	private List<String> especialidadesNomes;
	
	private List<Boolean> especialidadesVinculadas;
	
}
