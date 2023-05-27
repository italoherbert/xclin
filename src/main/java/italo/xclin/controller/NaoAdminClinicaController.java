package italo.xclin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.response.load.detalhes.ClinicaDetalhesLoadResponse;
import italo.xclin.model.response.load.tela.NaoAdminClinicaTelaLoadResponse;
import italo.xclin.service.naoadmin.NaoAdminClinicaService;

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
