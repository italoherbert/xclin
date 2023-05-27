package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalEspecialidadeVinculoResponse {

	private Long id;
	
	private String especialidade;
	
	private double consultaValor;
	
}
