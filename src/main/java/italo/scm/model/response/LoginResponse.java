package italo.scm.model.response;

import italo.scm.enums.tipos.UsuarioPerfil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {

	private String token;
	
	private String username; 
	
	private UsuarioPerfil perfil;
		
}
