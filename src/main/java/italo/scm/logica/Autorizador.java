package italo.scm.logica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.exception.ClinicaAuthorizationException;
import italo.scm.exception.Erro;

@Component
public class Autorizador {

	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	public void autorizaPorClinica( String authorizationHeader, Long clinicaId ) throws ClinicaAuthorizationException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		for( Long cid : clinicasIDs )
			if ( clinicaId == cid )
				return;
		
		throw new ClinicaAuthorizationException( Erro.CLINICA_ACESSO_NAO_AUTORIZADO );
	}
		
}
