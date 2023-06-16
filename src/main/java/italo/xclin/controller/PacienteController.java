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
import italo.xclin.model.request.filtro.PacienteFiltroRequest;
import italo.xclin.model.request.save.PacienteSaveRequest;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.PacienteResponse;
import italo.xclin.model.response.load.detalhes.PacienteDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.PacienteEditLoadResponse;
import italo.xclin.model.response.load.reg.PacienteRegLoadResponse;
import italo.xclin.model.response.load.tela.PacienteTelaLoadResponse;
import italo.xclin.service.PacienteService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.PacienteValidator;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private PacienteValidator pacienteValidator;
	
	@Autowired
	private Autorizador autorizador;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('pacienteWRITE')")
	@PostMapping("/registra/{clinicaId}")
	public ResponseEntity<Object> registra(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody PacienteSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
				
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
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
				
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

		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
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

	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/lista/limite/{clinicaId}/{nomeIni}/{quant}")
	public ResponseEntity<Object> listaPorNomeEClinica(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable String nomeIni,
			@PathVariable int quant ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
				
		ListaResponse resp = pacienteService.listaPorNome( clinicaId, nomeIni, quant );
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
