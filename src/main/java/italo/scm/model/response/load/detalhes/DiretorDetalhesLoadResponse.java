package italo.scm.model.response.load.detalhes;

import java.util.List;

import italo.scm.model.response.DiretorResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DiretorDetalhesLoadResponse {

	private DiretorResponse diretor;
	
	private List<String> clinicas;
	
}
