package italo.scm.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioSaveRequest {
	
	private String username;
	
	private String senha;
	
	private String perfil;
	
}
