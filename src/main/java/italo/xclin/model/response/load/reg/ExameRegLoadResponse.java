package italo.xclin.model.response.load.reg;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExameRegLoadResponse {

	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
	
}