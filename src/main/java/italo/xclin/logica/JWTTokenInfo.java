package italo.xclin.logica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JWTTokenInfo {

	private String subject;
	
	private String[] roles;
	
	private String perfil;
			
	private Long usuarioId;

	private Long[] clinicasIDs;

	private boolean expirado;
	
}
