package italo.xclin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import italo.xclin.model.request.filtro.LancamentoFiltroRequest;
import italo.xclin.model.request.save.LancamentoSaveRequest;
import italo.xclin.model.response.LancamentoResponse;
import italo.xclin.model.response.load.reg.LancamentoRegLoadResponse;
import italo.xclin.model.response.load.tela.LancamentoTelaLoadResponse;
import italo.xclin.service.LancamentoService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.LancamentoValidator;

@RestController
@RequestMapping("/api/lancamento")
public class LancamentoController {

	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private LancamentoValidator lancamentoValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('lancamentoWRITE')")
	@PostMapping("/registra/{clinicaId}")
	public ResponseEntity<Object> registra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody LancamentoSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		lancamentoValidator.validaSave( request );
		lancamentoService.registra( logadoUID, clinicaId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('lancamentoREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra( 
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId, 
			@RequestBody LancamentoFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		lancamentoValidator.validaFiltro( request ); 
		List<LancamentoResponse> lista = lancamentoService.filtra( request );
		return ResponseEntity.ok( lista );
	}		
	
	@PreAuthorize("hasAuthority('lancamentoREAD')")
	@GetMapping("/get/{lancamentoId}")
	public ResponseEntity<Object> get(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long lancamentoId ) throws SistemaException {
		
		autorizador.autorizaSeLancamentoDeClinica( authorizationHeader, lancamentoId );
		
		LancamentoResponse resp = lancamentoService.get( lancamentoId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('lancamentoREAD')")
	@GetMapping("/get/tela/load")
	public ResponseEntity<Object> getTelaLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		LancamentoTelaLoadResponse resp = lancamentoService.getLancamentoTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('lancamentoREAD')")
	@GetMapping("/get/reg/load")
	public ResponseEntity<Object> getRegLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		LancamentoRegLoadResponse resp = lancamentoService.getLancamentoRegLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('lancamentoDELETE')")
	@DeleteMapping("/deleta/{lancamentoId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long lancamentoId	) throws SistemaException {
		
		autorizador.autorizaSeLancamentoDeClinica( authorizationHeader, lancamentoId );
		
		lancamentoService.deleta( lancamentoId );
		return ResponseEntity.ok().build();
	}
	
}
