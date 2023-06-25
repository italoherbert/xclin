package italo.xclin.model.response.load.edit;

import italo.xclin.model.response.AnamneseResponse;
import italo.xclin.model.response.ListaResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamneseEditLoadResponse {
	
	private Long pacienteId;
	
	private String pacienteNome;
	
	private AnamneseResponse anamnese;
	
	private ListaResponse anamneseModelos;
	
}
