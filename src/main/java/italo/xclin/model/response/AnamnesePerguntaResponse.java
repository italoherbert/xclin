package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamnesePerguntaResponse {

	private Long id;
	
	private String pergunta;
	
	private String tipo;
	
	private String tipoLabel;
	
	private String respostaString;
	
	private boolean respostaBoolean;
	
	private String respostaEnum;
	
}
