package italo.scm.model.request.save;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioGrupoVinculadoListSaveRequest {

	private List<Long> grupos;
	
}
