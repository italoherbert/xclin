package italo.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.request.filtro.PacienteFiltroRequest;
import italo.scm.model.request.save.PacienteSaveRequest;
import italo.scm.model.response.PacienteResponse;
import italo.scm.model.response.load.PacienteDetalhesLoadResponse;
import italo.scm.model.response.load.PacienteEditLoadResponse;
import italo.scm.model.response.load.PacienteRegLoadResponse;
import italo.scm.model.response.load.PacienteTelaLoadResponse;
import italo.scm.service.AuthorizationService;
import italo.scm.service.PacienteService;
import italo.scm.validator.PacienteValidator;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private PacienteValidator pacienteValidator;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('pacienteWRITE')")
	@PostMapping("/registra/{clinicaId}")
	public ResponseEntity<Object> registra(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody PacienteSaveRequest request ) throws SistemaException {
		
		authorizationService.autoriza( authorizationHeader, clinicaId );
				
		pacienteValidator.validaSave( request );
		pacienteService.registra( clinicaId, request ); 		
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('pacienteWRITE')")
	@PutMapping("/altera/{clinicaId}/{pacienteId}")
	public ResponseEntity<Object> altera(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long pacienteId,
			@RequestBody PacienteSaveRequest request ) throws SistemaException {
		
		authorizationService.autoriza( authorizationHeader, clinicaId );
				
		pacienteValidator.validaSave( request );
		pacienteService.altera( clinicaId, pacienteId, request ); 		
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody PacienteFiltroRequest request ) throws SistemaException {

		authorizationService.autoriza( authorizationHeader, clinicaId );
		
		pacienteValidator.validaFiltro( request );
		List<PacienteResponse> lista = pacienteService.filtra( clinicaId, request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/get/{pacienteId}")
	public ResponseEntity<Object> get(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long pacienteId ) throws SistemaException {

		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		PacienteResponse resp = pacienteService.get( pacienteId, clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/get/edit/{pacienteId}")
	public ResponseEntity<Object> getEditLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long pacienteId ) throws SistemaException {

		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
				
		PacienteEditLoadResponse resp = pacienteService.getEditLoad( pacienteId, clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/get/detalhes/{pacienteId}")
	public ResponseEntity<Object> getDetalhesLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long pacienteId ) throws SistemaException {

		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		PacienteDetalhesLoadResponse resp = pacienteService.getDetalhesLoad( pacienteId, clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/get/tela")
	public ResponseEntity<Object> getTelaLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {

		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs(); 
				
		PacienteTelaLoadResponse resp = pacienteService.getTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/get/reg")
	public ResponseEntity<Object> getRegLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		PacienteRegLoadResponse resp = pacienteService.getRegLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('pacienteDELETE')")
	@DeleteMapping("/deleta/{pacienteId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long pacienteId ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		pacienteService.delete( pacienteId, clinicasIDs );
		return ResponseEntity.ok().build();
	}
	
}
