package italo.scm.model.response.load;

import java.util.List;

import italo.scm.model.response.UsuarioGrupoResponse;
import italo.scm.model.response.UsuarioResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioGrupoVinculosLoadResponse {
	
	private UsuarioResponse usuario;
	
	private List<UsuarioGrupoResponse> grupos;
	
	private List<Boolean> vinculadosFlags;
	
}
