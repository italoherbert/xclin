package italo.scm.service.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.exception.AutorizadorException;
import italo.scm.exception.Erro;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.Consulta;
import italo.scm.repository.ConsultaRepository;

@Component
public class Autorizador {

	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	public void autorizaPorConsulta( String authorizationHeader, Long consultaId ) throws AutorizadorException {
		Optional<Consulta> consultaOp = consultaRepository.findById( consultaId );
		if ( !consultaOp.isPresent() )
			throw new AutorizadorException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		Long clinicaId = consulta.getClinica().getId();
		
		this.autorizaPorClinica( authorizationHeader, clinicaId ); 
	}
	
	public void autorizaPorClinica( String authorizationHeader, Long clinicaId ) throws AutorizadorException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		for( Long cid : clinicasIDs )
			if ( clinicaId == cid )
				return;
		
		throw new AutorizadorException( Erro.CLINICA_ACESSO_NAO_AUTORIZADO );
	}
			
}
