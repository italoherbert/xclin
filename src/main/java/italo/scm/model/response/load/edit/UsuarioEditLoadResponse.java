package italo.scm.model.response.load.edit;

import java.util.List;

import italo.scm.model.response.TipoResponse;
import italo.scm.model.response.UsuarioResponse;
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
