package italo.scm.model.response.detalhes;

import java.util.List;

import italo.scm.model.response.AcessoResponse;
import italo.scm.model.response.UsuarioGrupoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioGrupoDetalhesResponse {

	private UsuarioGrupoResponse grupo;
	
	private List<AcessoResponse> acessos;
	
}
