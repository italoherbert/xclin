package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AtendimentoSaveRequest {
	
	private String dataAtendimento;
	
	private String turno;
	
	private String observacoes;
		
	private OrcamentoSaveRequest orcamento;

}
