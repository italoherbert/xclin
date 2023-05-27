package italo.xclin.model.response.load.edit;

import java.util.List;

import italo.xclin.model.response.TipoResponse;
import italo.xclin.model.response.UsuarioResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioEditLoadResponse {

	private UsuarioResponse usuario;
	
	private List<TipoResponse> perfis;
	
}
