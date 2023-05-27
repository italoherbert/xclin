package italo.xclin.model.response;

import italo.xclin.enums.tipos.UsuarioPerfil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponse {

	private Long id;
	
	private String username;
	
	private UsuarioPerfil perfil;
	
	private String perfilLabel;
				
}
