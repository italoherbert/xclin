package italo.xclin.model.response.load.edit;

import java.util.List;

import italo.xclin.model.response.AcessoResponse;
import italo.xclin.model.response.UsuarioGrupoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioGrupoEditLoadResponse {

	private UsuarioGrupoResponse grupo;
	
	private List<AcessoResponse> acessos;

}
