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
import italo.xclin.model.response.ExameResponse;
import italo.xclin.model.response.load.edit.ExameEditLoadResponse;
import italo.xclin.model.response.load.reg.ExameRegLoadResponse;
import italo.xclin.model.response.load.tela.ExameTelaLoadResponse;
import italo.xclin.service.ExameService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.ClinicaExameValidator;

@RestController
@RequestMapping("/api/exame")
public class ExameController {

	@Autowired
	private ExameService exameService;
	
	@Autowired
	private ClinicaExameValidator exameValidator;
	
	@Autowired
	private Autorizador autorizador;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('exameWRITE')")
	@PostMapping("/registra/{clinicaId}")
	public ResponseEntity<Object> registra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody ClinicaExameSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		exameValidator.validaSave( request );
		exameService.registra( clinicaId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('exameWRITE')")
	@PutMapping("/altera/{exameId}")
	public ResponseEntity<Object> altera(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameId,
			@RequestBody ClinicaExameSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSeClinicaExameDeClinica( authorizationHeader, exameId );
		
		exameValidator.validaSave( request );
		exameService.altera( exameId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody ClinicaExameFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId ); 
		
		exameValidator.validaFiltro( request );
		List<ExameResponse> lista = exameService.filtra( clinicaId, request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@GetMapping("/get/{exameId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
		
		autorizador.autorizaSeClinicaExameDeClinica( authorizationHeader, exameId ); 
		
		ExameResponse resp = exameService.get( exameId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@GetMapping("/load/tela")
	public ResponseEntity<Object> telaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ExameTelaLoadResponse resp = exameService.telaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@GetMapping("/load/reg")
	public ResponseEntity<Object> regLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ExameRegLoadResponse resp = exameService.regLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@GetMapping("/load/edit/{exameId}")
	public ResponseEntity<Object> editLoad(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ExameEditLoadResponse resp = exameService.editLoad( clinicasIDs, exameId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('exameDELETE')")
	@DeleteMapping("/deleta/{exameId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
		
		autorizador.autorizaSeClinicaExameDeClinica( authorizationHeader, exameId ); 
		
		exameService.deleta( exameId );
		return ResponseEntity.ok().build();
	}
	
}
