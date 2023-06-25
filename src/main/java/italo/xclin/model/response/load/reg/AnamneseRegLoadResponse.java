package italo.xclin.model.response.load.reg;

import italo.xclin.model.response.ListaResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamneseRegLoadResponse {

	private Long pacienteId;
	
	private String pacienteNome;
	
	private ListaResponse anamneseModelos;
	
}
