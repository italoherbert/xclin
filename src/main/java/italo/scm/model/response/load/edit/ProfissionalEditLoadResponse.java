package italo.scm.model.response.load.edit;

import java.util.List;

import italo.scm.model.response.ProfissionalResponse;
import italo.scm.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalEditLoadResponse {

	private ProfissionalResponse profissional;
	
	private List<TipoResponse> funcoes;
	
}
