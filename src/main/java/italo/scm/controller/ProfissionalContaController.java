package italo.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.request.save.ProfissionalSaveRequest;
import italo.scm.model.response.load.ProfissionalDetalhesLoadResponse;
import italo.scm.model.response.load.ProfissionalEditLoadResponse;
import italo.scm.service.ProfissionalService;
import italo.scm.validator.ProfissionalValidator;

@RestController
@RequestMapping("/api/conta/profissional")
public class ProfissionalContaController {

	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private ProfissionalValidator profissionalValidator;
		
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
		
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/altera/logado")
	public ResponseEntity<Object> alteraPorLogadoUID( 
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@RequestBody ProfissionalSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalValidator.validaSave( request );
		profissionalService.alteraPorLogadoUID( logadoUID, request );
		return ResponseEntity.ok().build(); 	
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/edit/logado")
	public ResponseEntity<Object> getEditLoadPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalEditLoadResponse resp = profissionalService.getEditLoadPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/detalhes/logado")
	public ResponseEntity<Object> getDetalhesLoadPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalDetalhesLoadResponse resp = profissionalService.getDetalhesLoadPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
	
}
