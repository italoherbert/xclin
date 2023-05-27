package italo.xclin.model.response.load.outros;

import java.util.List;

import italo.xclin.model.response.UsuarioGrupoResponse;
import italo.xclin.model.response.UsuarioResponse;
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
