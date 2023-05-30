package italo.xclin.controller;

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

import italo.xclin.enums.tipos.UsuarioPerfil;
import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.filtro.ConsultaFiltroRequest;
import italo.xclin.model.request.filtro.ConsultaListaFilaRequest;
import italo.xclin.model.request.save.ConsultaAlterSaveRequest;
import italo.xclin.model.request.save.ConsultaObservacoesSaveRequest;
import italo.xclin.model.request.save.ConsultaRemarcarSaveRequest;
import italo.xclin.model.request.save.ConsultaSaveRequest;
import italo.xclin.model.response.ConsultaIniciadaResponse;
import italo.xclin.model.response.ConsultaResponse;
import italo.xclin.model.response.load.edit.ConsultaAlterLoadResponse;
import italo.xclin.model.response.load.outros.ConsultaRemarcarLoadResponse;
import italo.xclin.model.response.load.outros.NovaConsultaProfissionalSelectLoadResponse;
import italo.xclin.model.response.load.reg.ConsultaRegLoadResponse;
import italo.xclin.model.response.load.tela.ConsultaIniciadaTelaLoadResponse;
import italo.xclin.model.response.load.tela.ConsultaListaFilaTelaLoadResponse;
import italo.xclin.model.response.load.tela.ConsultaTelaLoadResponse;
import italo.xclin.service.ConsultaService;
import italo.xclin.service.auth.Autorizador;
import italo.xclin.validator.ConsultaValidator;

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
		
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
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
		
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
		consultaService.remarca( consultaId, request );
		return ResponseEntity.ok().build();		
	}	
	
	@PreAuthorize("hasAuthority('consultaWRITE')")
	@PatchMapping("/paga/{consultaId}")
	public ResponseEntity<Object> registraPagamento(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId ) throws SistemaException {
		
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
		consultaService.registraPagamento( consultaId );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('consultaWRITE')")
	@PatchMapping("/finaliza/{consultaId}")
	public ResponseEntity<Object> finalizaConsulta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId ) throws SistemaException {
				
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
		consultaService.finalizaConsulta( consultaId );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('consultaWRITE')")
	@PatchMapping("/altera/observacoes/{consultaId}")
	public ResponseEntity<Object> salvaObservacoes(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId,
			@RequestBody ConsultaObservacoesSaveRequest request ) throws SistemaException {
				
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
		consultaValidator.validaAlterObservacoes( request );
		consultaService.salvaObservacoes( consultaId, request );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('consultaWRITE')")
	@PatchMapping("/inicia/{clinicaId}/{profissionalId}/{consultaId}/{turno}")
	public ResponseEntity<Object> iniciaConsulta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId,
			@PathVariable Long consultaId, 
			@PathVariable String turno ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		String perfil = tokenInfo.getPerfil();		
		if ( perfil.equalsIgnoreCase( UsuarioPerfil.PROFISSIONAL.name() ) )
			autorizador.autorizaSeProfissionalUsuario( authorizationHeader, profissionalId );  
		
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
		consultaService.iniciaConsulta( clinicaId, profissionalId, consultaId, turno );
		return ResponseEntity.ok().build();		
	}		
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/{consultaId}")
	public ResponseEntity<Object> get(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId) throws SistemaException {
		
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
		ConsultaResponse resp = consultaService.get( consultaId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/iniciada/{clinicaId}/{turno}")
	public ResponseEntity<Object> getIniciada(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable String turno ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		ConsultaIniciadaResponse resp = consultaService.getIniciada( logadoUID, clinicaId, turno );
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
		
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
		ConsultaRemarcarLoadResponse resp = consultaService.getRemarcarLoad( consultaId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/alter/{consultaId}")
	public ResponseEntity<Object> getAlterLoad(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId ) throws SistemaException {
		
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
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
	public ResponseEntity<Object> getListaFilaTelaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ConsultaListaFilaTelaLoadResponse resp = consultaService.getFiltroResumidoTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaREAD')")
	@GetMapping("/get/iniciada/tela")
	public ResponseEntity<Object> getIniciadaTelaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ConsultaIniciadaTelaLoadResponse resp = consultaService.getIniciadaTelaLoad( clinicasIDs );
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
			@RequestBody ConsultaListaFilaRequest request ) throws SistemaException {
				
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		autorizador.autorizaSeProfissionalDeClinica( authorizationHeader, clinicaId, profissionalId );
		
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
		autorizador.autorizaSeProfissionalDeClinica( authorizationHeader, clinicaId, profissionalId );
		
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
				
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
		List<Object[]> resp = consultaService.agrupaPorDiaDeMes( consultaId, mes, ano );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('consultaDELETE')")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId ) throws SistemaException {
		
		autorizador.autorizaPorConsultaEClinica( authorizationHeader, consultaId );
		
		consultaService.deleta( consultaId );
		return ResponseEntity.ok().build();
	}
}
