package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfissionalProcedimentoVinculoResponse {

	private Long id;
	
	private Long procedimentoId;
	
	private String procedimentoNome;
	
	private double procedimentoValor;
	
}
