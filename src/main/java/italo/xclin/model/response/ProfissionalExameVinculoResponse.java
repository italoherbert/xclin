package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalExameVinculoResponse {

	private Long id;
	
	private String exameNome;
	
	private double exameValor;
	
}
