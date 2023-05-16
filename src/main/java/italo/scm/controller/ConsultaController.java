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
import italo.scm.model.request.save.ConsultaRemarcarSaveRequest;
import italo.scm.model.request.save.ConsultaSaveRequest;
import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.load.ConsultaAgendaTelaLoadResponse;
import italo.scm.model.response.load.ConsultaRegLoadResponse;
import italo.scm.service.ConsultaService;
import italo.scm.service.auth.Autorizador;
import italo.scm.validator.ConsultaValidator;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private ConsultaValidator consultaValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('consultaWRITE')")
	@PostMapping("/registra/{clinicaId}/{profissionalId}/{pacienteId}")
	public ResponseEntity<Object> registra( 
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId, 
			@PathVariable Long pacienteId, 
			@RequestBody ConsultaSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		consultaValidator.validaSave( request );
		consultaService.registra( clinicaId, profissionalId, pacienteId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('consultaWRITE')")
	@PostMapping("/remarca/{consultaId}")
	public ResponseEntity<Object> remarca(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId,
			@RequestBody ConsultaRemarcarSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorConsulta( authorizationHeader, consultaId );
		
		consultaService.remarca( consultaId, request );
		return ResponseEntity.ok().build();		
	}		
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/{consultaId}")
	public ResponseEntity<Object> get(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId) throws SistemaException {
		
		autorizador.autorizaPorConsulta( authorizationHeader, consultaId );
		
		ConsultaResponse resp = consultaService.get( consultaId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/reg")
	public ResponseEntity<Object> getAgendaTelaLoad() throws SistemaException {				
		ConsultaRegLoadResponse resp = consultaService.getRegLoad();
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/tela/agenda")
	public ResponseEntity<Object> getAgendaTelaLoad( 
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ConsultaAgendaTelaLoadResponse resp = consultaService.getAgendaTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}

	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/quantidades/pordia/{clinicaId}/{profissionalId}/{mes}/{ano}")
	public ResponseEntity<Object> agrupaPorDiaDoMes(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId,
			@PathVariable int mes,
			@PathVariable int ano ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		List<Object[]> resp = consultaService.agrupaPorDiaDeMes( clinicaId, profissionalId, mes, ano );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/quantidades/pordia/cid/{consultaId}/{mes}/{ano}")
	public ResponseEntity<Object> agrupaPorDiaDoMes(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId,
			@PathVariable int mes,
			@PathVariable int ano ) throws SistemaException {
				
		autorizador.autorizaPorConsulta( authorizationHeader, consultaId );
		
		List<Object[]> resp = consultaService.agrupaPorDiaDeMes( consultaId, mes, ano );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/lista/pordata/{clinicaId}/{profissionalId}/{dia}/{mes}/{ano}")
	public ResponseEntity<Object> agrupaPorDiaDoMes(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId,
			@PathVariable int dia,
			@PathVariable int mes,
			@PathVariable int ano ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		List<ConsultaResponse> resp = consultaService.listaPorDia( clinicaId, profissionalId, dia, mes, ano );
		return ResponseEntity.ok( resp );
	}	
	
}
