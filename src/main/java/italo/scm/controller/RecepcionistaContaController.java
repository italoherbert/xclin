package italo.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.request.save.RecepcionistaSaveRequest;
import italo.scm.model.response.RecepcionistaResponse;
import italo.scm.model.response.load.edit.RecepcionistaEditLoadResponse;
import italo.scm.service.RecepcionistaService;
import italo.scm.validator.RecepcionistaValidator;

@RestController
@RequestMapping("/api/conta/recepcionista")
public class RecepcionistaContaController {

	@Autowired
	private RecepcionistaService recepcionistaService;
	
	@Autowired
	private RecepcionistaValidator recepcionistaValidator;
		
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
		
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/altera/logado/{clinicaId}")
	public ResponseEntity<Object> alteraPorLogadoUID( 
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long clinicaId,
			@RequestBody RecepcionistaSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		recepcionistaValidator.validaSave( request );
		recepcionistaService.alteraPorLogadoUID( logadoUID, clinicaId, request );
		return ResponseEntity.ok().build(); 	
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/edit/logado")
	public ResponseEntity<Object> getEditLoadPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		RecepcionistaEditLoadResponse resp = recepcionistaService.getEditLoadPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/logado")
	public ResponseEntity<Object> getPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		RecepcionistaResponse resp = recepcionistaService.getPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
	
}

