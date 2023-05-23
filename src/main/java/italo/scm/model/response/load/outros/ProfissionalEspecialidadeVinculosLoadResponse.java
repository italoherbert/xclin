package italo.scm.model.response.load.outros;

import java.util.List;

import italo.scm.model.response.ProfissionalEspecialidadeVinculoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalEspecialidadeVinculosLoadResponse {

	private String profissionalNome;
	
	private String profissionalFuncao;
	
	private List<ProfissionalEspecialidadeVinculoResponse> especialidades;
	
}
