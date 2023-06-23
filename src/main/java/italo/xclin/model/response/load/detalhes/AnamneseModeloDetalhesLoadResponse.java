package italo.xclin.model.response.load.detalhes;

import java.util.List;

import italo.xclin.model.response.AnamneseModeloPerguntaResponse;
import italo.xclin.model.response.AnamneseModeloResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamneseModeloDetalhesLoadResponse {

	private AnamneseModeloResponse anamneseModelo;
	
	private List<AnamneseModeloPerguntaResponse> perguntas;
	
}
