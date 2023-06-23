package italo.xclin.model.response.load.edit;

import java.util.List;

import italo.xclin.model.response.AnamneseModeloPerguntaResponse;
import italo.xclin.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamneseModeloPerguntaEditLoadResponse {

	private AnamneseModeloPerguntaResponse pergunta;
		
	private List<TipoResponse> perguntaTipos;
	
}
