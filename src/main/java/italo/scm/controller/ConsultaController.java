package italo.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.request.filtro.ConsultaFilaFiltroRequest;
import italo.scm.model.request.filtro.ConsultaFiltroRequest;
import italo.scm.model.request.save.ConsultaAlterSaveRequest;
import italo.scm.model.request.save.ConsultaRemarcarSaveRequest;
import italo.scm.model.request.save.ConsultaSaveRequest;
import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.load.edit.ConsultaAlterLoadResponse;
import italo.scm.model.response.load.outros.ConsultaRemarcarLoadResponse;
import italo.scm.model.response.load.outros.NovaConsultaProfissionalSelectLoadResponse;
import italo.scm.model.response.load.reg.ConsultaRegLoadResponse;
import italo.scm.model.response.load.tela.ConsultaFilaTelaLoadResponse;
import italo.scm.model.response.load.tela.ConsultaTelaLoadResponse;
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
	@PostMapping("/registra/{clinicaId}/{profissionalId}/{especialidadeId}/{pacienteId}")
	public ResponseEntity<Object> registra( 
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId, 
			@PathVariable Long especialidadeId,
			@PathVariable Long pacienteId, 
			@RequestBody ConsultaSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		consultaValidator.validaSave( request );
		consultaService.registra( clinicaId, profissionalId, especialidadeId, pacienteId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('consultaWRITE')")
	@PatchMapping("/altera/{consultaId}")
	public ResponseEntity<Object> altera(
			@RequestHeader("Authorization") String authorizationHeader, 
			@PathVariable Long consultaId, 
			@RequestBody ConsultaAlterSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorConsulta( authorizationHeader, consultaId );
		
		consultaValidator.validaAlterSave( request );
		consultaService.altera( consultaId, request ); 
		return ResponseEntity.ok().build();				
	}
	
	@PreAuthorize("hasAuthority('consultaWRITE')")
	@PatchMapping("/remarca/{consultaId}")
	public ResponseEntity<Object> remarca(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId,
			@RequestBody ConsultaRemarcarSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorConsulta( authorizationHeader, consultaId );
		
		consultaService.remarca( consultaId, request );
		return ResponseEntity.ok().build();		
	}	
	
	@PreAuthorize("hasAuthority('consultaWRITE')")
	@PatchMapping("/paga/{consultaId}")
	public ResponseEntity<Object> registraPagamento(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId ) throws SistemaException {
		
		autorizador.autorizaPorConsulta( authorizationHeader, consultaId );
		
		consultaService.registraPagamento( consultaId );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('consultaWRITE')")
	@PatchMapping("/finaliza/{consultaId}")
	public ResponseEntity<Object> finalizaConsulta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId ) throws SistemaException {
				
		autorizador.autorizaPorConsulta( authorizationHeader, consultaId );
		
		consultaService.finalizaConsulta( consultaId );
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
	@GetMapping("/get/reg/{profissionalId}")
	public ResponseEntity<Object> getRegLoad( 
			@PathVariable Long profissionalId ) throws SistemaException {
		
		ConsultaRegLoadResponse resp = consultaService.getRegLoad( profissionalId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/remarcar/{consultaId}")
	public ResponseEntity<Object> getRemarcadrLoad(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId ) throws SistemaException {		
		
		autorizador.autorizaPorConsulta( authorizationHeader, consultaId );
		
		ConsultaRemarcarLoadResponse resp = consultaService.getRemarcarLoad( consultaId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/alter/{consultaId}")
	public ResponseEntity<Object> getAlterLoad(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId ) throws SistemaException {
		
		autorizador.autorizaPorConsulta( authorizationHeader, consultaId );
		
		ConsultaAlterLoadResponse resp = consultaService.getAlterLoad( consultaId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/tela")
	public ResponseEntity<Object> getTelaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ConsultaTelaLoadResponse resp = consultaService.getTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/fila/tela")
	public ResponseEntity<Object> getFilaTelaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ConsultaFilaTelaLoadResponse resp = consultaService.getFilaTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody ConsultaFiltroRequest request ) throws SistemaException {
				
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		consultaValidator.validaFiltro( request );

		List<ConsultaResponse> lista = consultaService.filtra( clinicaId, request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@PostMapping("/lista/fila/{clinicaId}/{profissionalId}")
	public ResponseEntity<Object> listaFila(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId,
			@RequestBody ConsultaFilaFiltroRequest request ) throws SistemaException {
				
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		autorizador.verificaSeProfissionalDeClinica( authorizationHeader, clinicaId, profissionalId );
		
		consultaValidator.validaListaFila( request );
		
		List<ConsultaResponse> lista = consultaService.listaFila( 
				clinicaId, profissionalId, request );
		
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/novaconsulta/profissional/select")
	public ResponseEntity<Object> getNovaConsultaProfissionalSelectLoad( 
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
				
		NovaConsultaProfissionalSelectLoadResponse resp = consultaService.getNovaConsultaProfissionalSelectLoad( clinicasIDs );
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
		autorizador.verificaSeProfissionalDeClinica( authorizationHeader, clinicaId, profissionalId );
		
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
	
	@PreAuthorize("hasAuthority('consultaDELETE')")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId ) throws SistemaException {
		
		autorizador.autorizaPorConsulta( authorizationHeader, consultaId );
		
		consultaService.deleta( consultaId );
		return ResponseEntity.ok().build();
	}
}
