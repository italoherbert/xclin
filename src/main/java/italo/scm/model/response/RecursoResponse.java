package italo.scm.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecursoResponse {

	private Long id;
	
	private String nome;
	
	private boolean leitura;
	
	private boolean escrita;
	
	private boolean remocao;
	
}
