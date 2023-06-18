package italo.xclin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.relatorio.BalancoDoDiaRelatorioRequest;
import italo.xclin.model.response.load.relatorio.BalancoDoDiaLoadResponse;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.service.relatorio.AnamneseRelatorioService;
import italo.xclin.service.relatorio.BalancoDoDiaRelatorioService;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

	@Autowired
	private AnamneseRelatorioService anamneseRelatorioService;
	
	@Autowired
	private BalancoDoDiaRelatorioService balancoDoDiaRelatorioService;
	
	@Autowired
	private Autorizador autorizador;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping(value="/anamnese/{pacienteId}", produces = MediaType.APPLICATION_PDF_VALUE )
	@ResponseBody
	public byte[] getAnamnesePDF(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long pacienteId ) throws SistemaException {
		 
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		return anamneseRelatorioService.geraRelatorio( pacienteId );		
	}
	
	@PreAuthorize("hasAuthority('lancamentoREAD')")
	@PostMapping(value="/balanco-do-dia/{clinicaId}", produces = MediaType.APPLICATION_PDF_VALUE )
	@ResponseBody
	public byte[] getBalancoDoDiaPDF(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId, 
			@RequestBody BalancoDoDiaRelatorioRequest request ) throws SistemaException {
		 
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		return balancoDoDiaRelatorioService.geraRelatorio( clinicaId, request );		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value="/balanco-do-dia/load" )
	public ResponseEntity<Object> balancoDoDiaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		BalancoDoDiaLoadResponse resp = balancoDoDiaRelatorioService.loadTelaBalancoDoDia( clinicasIDs );
		return ResponseEntity.ok( resp );		
	}
		
}
