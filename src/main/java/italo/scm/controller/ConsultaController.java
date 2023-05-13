package italo.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.response.load.ConsultaAgendaTelaLoadResponse;
import italo.scm.service.ConsultaService;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/tela/agenda")
	public ResponseEntity<Object> getAgendaTelaLoad( 
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ConsultaAgendaTelaLoadResponse resp = consultaService.getAgendaTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
}
