package italo.xclin.controller;

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

import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.filtro.ClinicaExameFiltroRequest;
import italo.xclin.model.request.save.ClinicaExameSaveRequest;
import italo.xclin.model.response.ClinicaExameResponse;
import italo.xclin.model.response.load.edit.ClinicaExameEditLoadResponse;
import italo.xclin.model.response.load.reg.ClinicaExameRegLoadResponse;
import italo.xclin.model.response.load.tela.ClinicaExameTelaLoadResponse;
import italo.xclin.service.ClinicaExameService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.ClinicaExameValidator;

@RestController
@RequestMapping("/api/clinica/exame")
public class ClinicaExameController {

	@Autowired
	private ClinicaExameService clinicaExameService;
	
	@Autowired
	private ClinicaExameValidator clinicaExameValidator;
	
	@Autowired
	private Autorizador autorizador;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('clinicaExameWRITE')")
	@PostMapping("/registra/{clinicaId}")
	public ResponseEntity<Object> registra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody ClinicaExameSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		clinicaExameValidator.validaSave( request );
		clinicaExameService.registra( clinicaId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('clinicaExameWRITE')")
	@PutMapping("/altera/{clinicaExameId}")
	public ResponseEntity<Object> altera(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaExameId,
			@RequestBody ClinicaExameSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSeClinicaExameDeClinica( authorizationHeader, clinicaExameId );
		
		clinicaExameValidator.validaSave( request );
		clinicaExameService.altera( clinicaExameId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('clinicaExameREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody ClinicaExameFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId ); 
		
		clinicaExameValidator.validaFiltro( request );
		List<ClinicaExameResponse> lista = clinicaExameService.filtra( clinicaId, request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('clinicaExameREAD')")
	@GetMapping("/get/{clinicaExameId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaExameId ) throws SistemaException {
		
		autorizador.autorizaSeClinicaExameDeClinica( authorizationHeader, clinicaExameId ); 
		
		ClinicaExameResponse resp = clinicaExameService.get( clinicaExameId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('clinicaExameREAD')")
	@GetMapping("/load/tela")
	public ResponseEntity<Object> telaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ClinicaExameTelaLoadResponse resp = clinicaExameService.telaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('clinicaExameREAD')")
	@GetMapping("/load/reg")
	public ResponseEntity<Object> regLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ClinicaExameRegLoadResponse resp = clinicaExameService.regLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('clinicaExameREAD')")
	@GetMapping("/load/edit/{clinicaExameId}")
	public ResponseEntity<Object> editLoad(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaExameId ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ClinicaExameEditLoadResponse resp = clinicaExameService.editLoad( clinicasIDs, clinicaExameId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('clinicaExameDELETE')")
	@DeleteMapping("/deleta/{clinicaExameId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaExameId ) throws SistemaException {
		
		autorizador.autorizaSeClinicaExameDeClinica( authorizationHeader, clinicaExameId ); 
		
		clinicaExameService.deleta( clinicaExameId );
		return ResponseEntity.ok().build();
	}
	
}
