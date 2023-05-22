package italo.scm.model.response.load;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalEspecialidadeVinculosLoadResponse {

	private String profissionalNome;
	
	private String profissionalFuncao;
	
	private List<Long> especialidadesVinculosIDs;
	
	private List<String> especialidadesVinculosNomes;
	
}
