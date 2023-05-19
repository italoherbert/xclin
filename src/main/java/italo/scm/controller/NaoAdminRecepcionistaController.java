package italo.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.request.filtro.NaoAdminRecepcionistaFiltroRequest;
import italo.scm.model.response.RecepcionistaResponse;
import italo.scm.model.response.load.NaoAdminRecepcionistaTelaLoadResponse;
import italo.scm.service.auth.Autorizador;
import italo.scm.service.naoadmin.NaoAdminRecepcionistaService;
import italo.scm.validator.RecepcionistaValidator;

@RestController
@RequestMapping("/api/naoadmin/recepcionista")				 
public class NaoAdminRecepcionistaController {

	@Autowired
	private NaoAdminRecepcionistaService naoAdminRecepcionistaService;
	
	@Autowired
	private RecepcionistaValidator recepcionistaValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('naoAdminRecepcionistaREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId, 
			@RequestBody NaoAdminRecepcionistaFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		recepcionistaValidator.validaNaoAdminFiltro( request );
		
		List<RecepcionistaResponse> lista = naoAdminRecepcionistaService.filtra( clinicaId, request );
		return ResponseEntity.ok( lista );		
	}
	
	@PreAuthorize("hasAuthority('naoAdminRecepcionistaREAD')")
	@GetMapping("/get/{recepcionistaId}")
	public ResponseEntity<Object> get(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long recepcionistaId ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		RecepcionistaResponse resp = naoAdminRecepcionistaService.get( recepcionistaId, clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('naoAdminRecepcionistaREAD')")
	@GetMapping("/get/tela")
	public ResponseEntity<Object> getTelaLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		NaoAdminRecepcionistaTelaLoadResponse resp = naoAdminRecepcionistaService.getTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
}
