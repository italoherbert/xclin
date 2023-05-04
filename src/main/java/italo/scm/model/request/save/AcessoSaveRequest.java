package italo.scm.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AcessoSaveRequest {

	private Long grupoId;
	
	private Long recursoId;
	
	private boolean leitura;
	
	private boolean escrita;
	
	private boolean remocao;
	
}
