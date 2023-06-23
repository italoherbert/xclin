package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamnesePerguntaSaveRequest {

	private String pergunta;
	
	private String tipo;
		
	private String respostaString;
	
	private boolean respostaBoolean;
		
	private String respostaEnum;

}
