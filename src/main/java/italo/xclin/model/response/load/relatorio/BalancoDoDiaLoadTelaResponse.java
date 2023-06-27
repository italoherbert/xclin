package italo.xclin.model.response.load.relatorio;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BalancoDoDiaLoadTelaResponse {

	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
	
}