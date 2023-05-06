package italo.scm.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioGrupoVinculoResponse {

	private UsuarioResponse usuario;
	
	private UsuarioGrupoResponse grupo;
	
	private boolean adicionado;
	
}
