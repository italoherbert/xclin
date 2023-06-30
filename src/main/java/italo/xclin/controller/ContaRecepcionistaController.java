package italo.xclin.controller;

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

import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.save.RecepcionistaSaveRequest;
import italo.xclin.model.response.RecepcionistaResponse;
import italo.xclin.model.response.load.edit.RecepcionistaEditLoadResponse;
import italo.xclin.service.RecepcionistaService;
import italo.xclin.validator.RecepcionistaValidator;

@RestController
@RequestMapping("/api/conta/recepcionista")
public class ContaRecepcionistaController {

	@Autowired
	private RecepcionistaService recepcionistaService;
	
	@Autowired
	private RecepcionistaValidator recepcionistaValidator;
		
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
		
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/altera/{clinicaId}")
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
	@GetMapping("/load/edit")
	public ResponseEntity<Object> getEditLoadPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		RecepcionistaEditLoadResponse resp = recepcionistaService.getEditLoadPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get")
	public ResponseEntity<Object> getPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		RecepcionistaResponse resp = recepcionistaService.getPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
	
}

