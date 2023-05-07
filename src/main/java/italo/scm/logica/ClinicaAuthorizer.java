package italo.scm.logica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.logica.jwt.JWTTokenLogica;

@Component
public class ClinicaAuthorizer {

	@Autowired
	private JWTTokenLogica jwtLogica;
	
	public void authorize( String authorizationHeader ) {
		
	}
	
}
