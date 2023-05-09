package italo.scm.model.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioGrupoVinculadosResponse {
	
	private UsuarioResponse usuario;
	
	private List<UsuarioGrupoResponse> grupos;
	
	private List<Boolean> vinculadosFlags;
	
}
