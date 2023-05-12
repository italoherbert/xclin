package italo.scm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.ClinicaAuthorizationException;
import italo.scm.exception.Erro;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;

@Service
public class AuthorizationService {

	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	public void autoriza( String authorizationHeader, Long clinicaId ) throws ClinicaAuthorizationException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		for( Long cid : clinicasIDs )
			if ( clinicaId == cid )
				return;
		
		throw new ClinicaAuthorizationException( Erro.CLINICA_ACESSO_NAO_AUTORIZADO );
	}
		
}
