package italo.xclin.controller;

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

import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.filtro.NaoAdminDiretorFiltroRequest;
import italo.xclin.model.response.DiretorResponse;
import italo.xclin.model.response.load.detalhes.DiretorDetalhesLoadResponse;
import italo.xclin.model.response.load.tela.NaoAdminDiretorTelaLoadResponse;
import italo.xclin.service.auth.Autorizador;
import italo.xclin.service.naoadmin.NaoAdminDiretorService;
import italo.xclin.validator.DiretorValidator;

@RestController
@RequestMapping("/api/naoadmin/diretor")
public class NaoAdminDiretorController {

	@Autowired
	private NaoAdminDiretorService naoAdminDiretorService;
	
	@Autowired
	private DiretorValidator diretorValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('naoAdminDiretorREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId, 
			@RequestBody NaoAdminDiretorFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		diretorValidator.validaNaoAdminFiltro( request );
		
		List<DiretorResponse> lista = naoAdminDiretorService.filtra( clinicaId, request );
		return ResponseEntity.ok( lista );		
	}
	
	@PreAuthorize("hasAuthority('naoAdminDiretorREAD')")
	@GetMapping("/get/detalhes/{diretorId}")
	public ResponseEntity<Object> getDetalhesLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long diretorId ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		DiretorDetalhesLoadResponse resp = naoAdminDiretorService.getDetalhesLoad( diretorId, clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('naoAdminDiretorREAD')")
	@GetMapping("/get/tela")
	public ResponseEntity<Object> getTelaLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		NaoAdminDiretorTelaLoadResponse resp = naoAdminDiretorService.getTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
}
