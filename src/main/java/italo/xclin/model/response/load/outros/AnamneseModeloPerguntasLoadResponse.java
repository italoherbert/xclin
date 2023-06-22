package italo.xclin.model.response.load.outros;

import java.util.List;

import italo.xclin.model.response.AnamneseModeloPerguntaResponse;
import italo.xclin.model.response.AnamneseModeloResponse;
import italo.xclin.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamneseModeloPerguntasLoadResponse {

	private AnamneseModeloResponse anamneseModelo;
	
	private List<AnamneseModeloPerguntaResponse> perguntas;
	
	private List<TipoResponse> perguntaTipos;

}
