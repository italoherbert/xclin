package italo.scm.model.response.edit;

import java.util.List;

import italo.scm.model.response.UsuarioGrupoVinculoResponse;
import italo.scm.model.response.UsuarioResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioGrupoVinculosEditResponse {
	
	private UsuarioResponse usuario;
	
	private List<UsuarioGrupoVinculoResponse> vinculos;
	
}
