package italo.scm.logica.jwt;

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
	
	private boolean expirado;
	
}
