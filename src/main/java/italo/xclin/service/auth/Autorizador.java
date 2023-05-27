package italo.xclin.service.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.exception.AutorizadorException;
import italo.xclin.exception.Erro;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.Clinica;
import italo.xclin.model.Consulta;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalClinicaVinculo;
import italo.xclin.repository.ConsultaRepository;
import italo.xclin.repository.ProfissionalRepository;

@Component
public class Autorizador {

	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
			
	public void autorizaSeProfissionalDeClinica( String authorizationHeader, Long clinicaId, Long profissionalId ) throws AutorizadorException {				
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		if ( !profissionalOp.isPresent() )
			throw new AutorizadorException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
				
		Profissional profissional = profissionalOp.get();
				
		List<ProfissionalClinicaVinculo> vinculos = profissional.getProfissionalClinicaVinculos();
		for( ProfissionalClinicaVinculo v : vinculos ) {
			Clinica c = v.getClinica();
			if ( c.getId() == clinicaId )
				return;
		}
		throw new AutorizadorException( Erro.CLINICA_ACESSO_NAO_AUTORIZADO );
	}
	
	public void autorizaSeProfissionalUsuario( String authorizationHeader, Long profissionalId ) throws AutorizadorException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( profissionalOp.isPresent() ) {
			Profissional p = profissionalOp.get();
			if( p.getId() != profissionalId )
				throw new AutorizadorException( Erro.PROFISSIONAL_ACESSO_NAO_AUTORIZADO );
			
			return;
		}
			
		throw new AutorizadorException( Erro.PROFISSIONAL_ACESSO_NAO_AUTORIZADO );
	}
	
	public void autorizaPorConsultaEClinica( String authorizationHeader, Long consultaId ) throws AutorizadorException {
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
