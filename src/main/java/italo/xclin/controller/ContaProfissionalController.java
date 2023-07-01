package italo.xclin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.save.ProfissionalSaveRequest;
import italo.xclin.model.response.load.detalhes.ProfissionalDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.ProfissionalEditLoadResponse;
import italo.xclin.service.ProfissionalService;
import italo.xclin.validator.ProfissionalValidator;

@RestController
@RequestMapping("/api/conta/profissional")
public class ContaProfissionalController {

	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private ProfissionalValidator profissionalValidator;
		
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
		
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/altera")
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
	@GetMapping("/load/edit")
	public ResponseEntity<Object> getEditLoadPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalEditLoadResponse resp = profissionalService.getEditLoadPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/load/detalhes")
	public ResponseEntity<Object> getDetalhesLoadPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalDetalhesLoadResponse resp = profissionalService.getDetalhesLoadPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
		
}
