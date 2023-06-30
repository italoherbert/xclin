package italo.xclin.model.response.load.reg;

import java.util.List;

import italo.xclin.model.response.EspecialidadeResponse;
import italo.xclin.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AtendimentoRegLoadResponse {

	private List<TipoResponse> turnos;
	
	private List<EspecialidadeResponse> especialidades;
	
}
