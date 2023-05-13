package italo.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.Autorizador;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.load.ConsultaAgendaTelaLoadResponse;
import italo.scm.service.ConsultaService;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/tela/agenda")
	public ResponseEntity<Object> getAgendaTelaLoad( 
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ConsultaAgendaTelaLoadResponse resp = consultaService.getAgendaTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}

	@PreAuthorize("isAuthenticated()")
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
	
	@PreAuthorize("isAuthenticated()")
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
