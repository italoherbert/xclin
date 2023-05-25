package italo.scm.model.response.load.reg;

import java.util.List;

import italo.scm.model.response.EspecialidadeResponse;
import italo.scm.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaRegLoadResponse {

	private List<TipoResponse> turnos;
	
	private List<EspecialidadeResponse> especialidades;
	
}