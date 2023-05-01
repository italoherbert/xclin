package italo.scm.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioRequest {
	
	private String username;
	
	private String senha;
	
	private String perfil;
	
}
