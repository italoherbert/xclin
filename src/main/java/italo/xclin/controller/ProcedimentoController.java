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
import italo.xclin.model.request.filtro.ProcedimentoFiltroRequest;
import italo.xclin.model.request.save.ProcedimentoSaveRequest;
import italo.xclin.model.response.ProcedimentoResponse;
import italo.xclin.model.response.load.edit.ProcedimentoEditLoadResponse;
import italo.xclin.model.response.load.reg.ProcedimentoRegLoadResponse;
import italo.xclin.model.response.load.tela.ProcedimentoTelaLoadResponse;
import italo.xclin.service.ProcedimentoService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.ProcedimentoValidator;

@RestController
@RequestMapping("/api/procedimento")
public class ProcedimentoController {
	
	@Autowired
	private ProcedimentoService procedimentoService;
	
	@Autowired
	private ProcedimentoValidator procedimentoValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;

	@PreAuthorize("hasAuthority('procedimentoWRITE')")
	@PostMapping("/registra/{clinicaId}")
	public ResponseEntity<Object> registra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody ProcedimentoSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		procedimentoValidator.validaSave( request );
		procedimentoService.registra( clinicaId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('procedimentoWRITE')")
	@PutMapping("/altera/{procedimentoId}")
	public ResponseEntity<Object> altera(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long procedimentoId,
			@RequestBody ProcedimentoSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSeProcedimentoDeClinica( authorizationHeader, procedimentoId );
		
		procedimentoValidator.validaSave( request );
		procedimentoService.altera( procedimentoId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('procedimentoREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody ProcedimentoFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId ); 
		
		procedimentoValidator.validaFiltro( request );
		List<ProcedimentoResponse> lista = procedimentoService.filtra( clinicaId, request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('procedimentoREAD')")
	@GetMapping("/get/{procedimentoId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long procedimentoId ) throws SistemaException {
		
		autorizador.autorizaSeProcedimentoDeClinica( authorizationHeader, procedimentoId ); 
		
		ProcedimentoResponse resp = procedimentoService.get( procedimentoId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('procedimentoREAD')")
	@GetMapping("/load/tela")
	public ResponseEntity<Object> telaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ProcedimentoTelaLoadResponse resp = procedimentoService.telaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('procedimentoREAD')")
	@GetMapping("/load/reg")
	public ResponseEntity<Object> regLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ProcedimentoRegLoadResponse resp = procedimentoService.regLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('procedimentoREAD')")
	@GetMapping("/load/edit/{procedimentoId}")
	public ResponseEntity<Object> editLoad(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long procedimentoId ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ProcedimentoEditLoadResponse resp = procedimentoService.editLoad( clinicasIDs, procedimentoId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('procedimentoDELETE')")
	@DeleteMapping("/deleta/{procedimentoId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long procedimentoId ) throws SistemaException {
		
		autorizador.autorizaSeProcedimentoDeClinica( authorizationHeader, procedimentoId ); 
		
		procedimentoService.deleta( procedimentoId );
		return ResponseEntity.ok().build();
	}
	
}
