package italo.xclin.model.response.load.edit;

import java.util.List;

import italo.xclin.model.response.AtendimentoResponse;
import italo.xclin.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AtendimentoAlterLoadResponse {

	private AtendimentoResponse atendimento;
	
	private List<TipoResponse> turnos;
	
	private List<TipoResponse> statuses;

}
