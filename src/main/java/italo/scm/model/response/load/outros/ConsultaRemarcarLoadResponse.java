package italo.scm.model.response.load.outros;

import java.util.List;

import italo.scm.model.response.TipoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaRemarcarLoadResponse {

	private List<TipoResponse> turnos;

}
