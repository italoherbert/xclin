package italo.xclin.model.request.relatorio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BalancoDoDiaRelatorioRequest {

	private Long usuarioId;
	
	private boolean incluirTodosOsUsuarios;
	
	private String dataDia;
	
}
