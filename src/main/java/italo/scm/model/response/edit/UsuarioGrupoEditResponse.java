package italo.scm.model.response.edit;

import java.util.List;

import italo.scm.model.response.AcessoResponse;
import italo.scm.model.response.UsuarioGrupoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioGrupoEditResponse {

	private UsuarioGrupoResponse grupo;
	
	private List<AcessoResponse> acessos;

}
