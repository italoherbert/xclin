package italo.xclin.model.response.load.edit;

import java.util.List;

import italo.xclin.model.response.AnamneseResponse;
import italo.xclin.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamneseEditLoadResponse {

	private boolean anamnesePreenchida;
		
	private AnamneseResponse anamnese;

	private String pacienteNome;

	private List<TipoResponse> pressaoArterialTipos;
	
	private List<TipoResponse> cicatrizacaoTipos;
	
	private List<TipoResponse> sangramentoTipos;
	
	private List<TipoResponse> sangramentoNaGengivaTipos;
	
}
