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
import italo.xclin.model.response.load.relatorio.BalancoDoDiaLoadTelaResponse;
import italo.xclin.model.response.load.relatorio.ProntuarioLoadTelaResponse;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.service.relatorio.BalancoDoDiaRelatorioService;
import italo.xclin.service.relatorio.ProntuarioRelatorioService;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

	@Autowired
	private ProntuarioRelatorioService prontuarioRelatorioService;
	
	@Autowired
	private BalancoDoDiaRelatorioService balancoDoDiaRelatorioService;
	
	@Autowired
	private Autorizador autorizador;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
		
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
		
		BalancoDoDiaLoadTelaResponse resp = balancoDoDiaRelatorioService.loadTelaBalancoDoDia( clinicasIDs );
		return ResponseEntity.ok( resp );		
	}
	
	@PreAuthorize("hasAuthority('anamneseREAD')")
	@GetMapping(value="/prontuario/{pacienteId}", produces = MediaType.APPLICATION_PDF_VALUE )
	@ResponseBody
	public byte[] getProntuarioPDF(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long pacienteId ) throws SistemaException {
		 
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		return prontuarioRelatorioService.geraRelatorio( pacienteId );		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value="/prontuario/load" )
	public ResponseEntity<Object> prontuarioLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ProntuarioLoadTelaResponse resp = prontuarioRelatorioService.loadTelaProntuario( clinicasIDs );
		return ResponseEntity.ok( resp );		
	}
			
}
