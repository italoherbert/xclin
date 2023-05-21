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
import italo.scm.model.request.save.DiretorSaveRequest;
import italo.scm.model.response.load.DiretorDetalhesLoadResponse;
import italo.scm.service.DiretorService;
import italo.scm.validator.DiretorValidator;

@RestController
@RequestMapping("/api/conta/diretor")
public class DiretorContaController {

	@Autowired
	private DiretorService diretorService;
	
	@Autowired
	private DiretorValidator diretorValidator;
		
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
		
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/altera/logado")
	public ResponseEntity<Object> alteraPorLogadoUID( 
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@RequestBody DiretorSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		diretorValidator.validaSave( request );
		diretorService.alteraPorLogadoUID( logadoUID, request );
		return ResponseEntity.ok().build(); 	
	}
		
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/detalhes/logado")
	public ResponseEntity<Object> getDetalhesLoadPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		DiretorDetalhesLoadResponse resp = diretorService.getDetalhesLoadPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
	
}

