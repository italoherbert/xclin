package italo.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.response.load.ClinicaDetalhesLoadResponse;
import italo.scm.model.response.load.NaoAdminClinicaTelaLoadResponse;
import italo.scm.service.naoadmin.NaoAdminClinicaService;

@RestController
@RequestMapping("/api/naoadmin/clinica")
public class NaoAdminClinicaController {

	@Autowired
	private NaoAdminClinicaService naoAdminClinicaService;
		
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
			
	@PreAuthorize("hasAuthority('naoAdminClinicaREAD')")
	@GetMapping("/get/detalhes/{clinicaId}")
	public ResponseEntity<Object> getDetalhesLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ClinicaDetalhesLoadResponse resp = naoAdminClinicaService.getDetalhesLoad( clinicaId, clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('naoAdminClinicaREAD')")
	@GetMapping("/get/tela")
	public ResponseEntity<Object> getTelaLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		NaoAdminClinicaTelaLoadResponse resp = naoAdminClinicaService.getTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
}
