package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AcessoResponse {

	private Long grupoId;
	
	private Long recursoId;
	
	private String grupoNome;
	
	private String recursoNome;
	
	private boolean leitura;
	
	private boolean escrita;
	
	private boolean remocao;
	
}
