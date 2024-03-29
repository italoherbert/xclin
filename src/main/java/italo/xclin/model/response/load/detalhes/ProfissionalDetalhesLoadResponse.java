package italo.xclin.model.response.load.detalhes;

import java.util.List;

import italo.xclin.model.response.ProfissionalResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalDetalhesLoadResponse {

	private ProfissionalResponse profissional;
	
	private List<String> clinicas;
			
	private List<String> especialidades;

}
