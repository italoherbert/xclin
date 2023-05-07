package italo.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.jwt.JWTTokenInfo;
import italo.scm.logica.jwt.JWTTokenLogica;
import italo.scm.model.request.save.ClinicaSaveRequest;
import italo.scm.service.ClinicaService;
import italo.scm.validator.ClinicaValidator;

@RestController
@RequestMapping("/api/clinica")
public class ClinicaController {

	@Autowired
	private ClinicaService clinicaService;
	
	@Autowired
	private ClinicaValidator clinicaValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('clinicaWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( 
			@RequestHeader( "Authorization") String authHeader,
			@RequestBody ClinicaSaveRequest request ) throws SistemaException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		clinicaValidator.validaSave( request );
		clinicaService.registra( logadoUID, request );
		return ResponseEntity.ok().build(); 
	}
	
}
