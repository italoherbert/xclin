package italo.xclin.model.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamneseResponse {

	private Long id;
	
	private String nome;
	
	private String dataCriacao;
	
	private List<AnamnesePerguntaResponse> perguntas;
	
}
