package italo.xclin.model.response.load.tela;

import java.util.List;

import italo.xclin.model.response.PacienteAnexoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PacienteAnexoTelaLoadResponse {

	private Long pacienteId;
	
	private String pacienteNome;
	
	private List<PacienteAnexoResponse> anexos;
	
}
