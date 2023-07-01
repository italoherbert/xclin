package italo.xclin.model.request.filtro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AtendimentoFiltroRequest {

	private String dataInicio;

	private String dataFim;
	
	private String pacienteNomeIni;
	
	private String profissionalNomeIni;
	
	private boolean incluirPaciente;
	
	private boolean incluirProfissional;
		
	private String turno;
	
	private boolean incluirTodosTurnos;

	private String status;
	
	private boolean incluirTodosStatuses;
	
}
