package italo.xclin.model.response.load.detalhes;

import java.util.List;

import italo.xclin.model.response.AcessoResponse;
import italo.xclin.model.response.UsuarioGrupoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioGrupoDetalhesLoadResponse {

	private UsuarioGrupoResponse grupo;
	
	private List<AcessoResponse> acessos;
	
}
