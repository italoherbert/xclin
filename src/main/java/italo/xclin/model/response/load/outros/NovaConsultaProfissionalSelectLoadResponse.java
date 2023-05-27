package italo.xclin.model.response.load.outros;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NovaConsultaProfissionalSelectLoadResponse {

	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
	
}
