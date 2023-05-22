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
import italo.scm.model.request.filtro.NaoAdminProfissionalFiltroRequest;
import italo.scm.model.response.ProfissionalResponse;
import italo.scm.model.response.load.detalhes.ProfissionalDetalhesLoadResponse;
import italo.scm.model.response.load.tela.NaoAdminProfissionalTelaLoadResponse;
import italo.scm.service.auth.Autorizador;
import italo.scm.service.naoadmin.NaoAdminProfissionalService;
import italo.scm.validator.ProfissionalValidator;

@RestController
@RequestMapping("/api/naoadmin/profissional")
public class NaoAdminProfissionalController {

	@Autowired
	private NaoAdminProfissionalService naoAdminProfissionalService;
	
	@Autowired
	private ProfissionalValidator profissionalValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('naoAdminProfissionalREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId, 
			@RequestBody NaoAdminProfissionalFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		profissionalValidator.validaNaoAdminFiltro( request );
		
		List<ProfissionalResponse> lista = naoAdminProfissionalService.filtra( clinicaId, request );
		return ResponseEntity.ok( lista );		
	}
	
	@PreAuthorize("hasAuthority('naoAdminProfissionalREAD')")
	@GetMapping("/get/detalhes/{profissionalId}")
	public ResponseEntity<Object> getDetalhesLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long profissionalId ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ProfissionalDetalhesLoadResponse resp = naoAdminProfissionalService.getDetalhesLoad( profissionalId, clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('naoAdminProfissionalREAD')")
	@GetMapping("/get/tela")
	public ResponseEntity<Object> getTelaLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		NaoAdminProfissionalTelaLoadResponse resp = naoAdminProfissionalService.getTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
}

