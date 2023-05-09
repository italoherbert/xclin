package italo.scm.model.response.load;

import java.util.List;

import italo.scm.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalRegLoadResponse {
	
	private List<TipoResponse> funcoes;
	
}
