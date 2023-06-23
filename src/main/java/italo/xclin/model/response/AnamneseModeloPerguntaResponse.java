package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamneseModeloPerguntaResponse {

	private Long id;
	
	private String pergunta;
	
	private String tipo;
	
	private String enums;
	
	private String tipoLabel;
		
}
