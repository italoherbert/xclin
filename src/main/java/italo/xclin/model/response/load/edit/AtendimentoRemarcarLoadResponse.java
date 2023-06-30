package italo.xclin.model.response.load.edit;

import java.util.List;

import italo.xclin.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AtendimentoRemarcarLoadResponse {

	private List<TipoResponse> turnos;
	
	private String dataAtendimento;
	
	private String turno;
	
	private String turnoLabel;

}
