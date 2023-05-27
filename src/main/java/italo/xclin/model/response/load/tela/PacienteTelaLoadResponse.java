package italo.xclin.model.response.load.tela;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PacienteTelaLoadResponse {

	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
	
}
