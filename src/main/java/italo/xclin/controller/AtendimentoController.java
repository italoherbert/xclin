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
import italo.xclin.model.request.filtro.AtendimentoFiltroRequest;
import italo.xclin.model.request.filtro.AtendimentoListaFilaCompletaFiltroRequest;
import italo.xclin.model.request.filtro.AtendimentoListaFilaFiltroRequest;
import italo.xclin.model.request.save.AtendimentoAlterSaveRequest;
import italo.xclin.model.request.save.AtendimentoObservacoesSaveRequest;
import italo.xclin.model.request.save.AtendimentoRemarcarSaveRequest;
import italo.xclin.model.request.save.AtendimentoRetornoSaveRequest;
import italo.xclin.model.request.save.AtendimentoSaveRequest;
import italo.xclin.model.request.save.OrcamentoPagamentoSaveRequest;
import italo.xclin.model.response.AtendimentoIniciadoResponse;
import italo.xclin.model.response.AtendimentoResponse;
import italo.xclin.model.response.load.edit.AtendimentoAlterLoadResponse;
import italo.xclin.model.response.load.edit.AtendimentoRemarcarLoadResponse;
import italo.xclin.model.response.load.edit.AtendimentoRetornoLoadResponse;
import italo.xclin.model.response.load.edit.OrcamentoPagamentoLoadResponse;
import italo.xclin.model.response.load.reg.AtendimentoRegLoadResponse;
import italo.xclin.model.response.load.reg.NovoAtendimentoRegLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoAgendaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoIniciadaTelaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoListaFilaTelaLoadResponse;
import italo.xclin.model.response.load.tela.AtendimentoTelaLoadResponse;
import italo.xclin.service.AtendimentoService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.AtendimentoValidator;

@RestController
@RequestMapping("/api/atendimento")
public class AtendimentoController {

	@Autowired
	private AtendimentoService atendimentoService;
	
	@Autowired
	private AtendimentoValidator atendimentoValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PostMapping("/registra/{clinicaId}/{profissionalId}/{pacienteId}")
	public ResponseEntity<Object> registra( 
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId, 
			@PathVariable Long pacienteId, 
			@RequestBody AtendimentoSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		atendimentoValidator.validaSave( request );
		atendimentoService.registra( logadoUID, clinicaId, profissionalId, pacienteId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/altera/{atendimentoId}")
	public ResponseEntity<Object> altera(
			@RequestHeader("Authorization") String authorizationHeader, 
			@PathVariable Long atendimentoId, 
			@RequestBody AtendimentoAlterSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		atendimentoValidator.validaAlterSave( request );
		atendimentoService.altera( atendimentoId, request ); 
		return ResponseEntity.ok().build();				
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/remarca/{atendimentoId}")
	public ResponseEntity<Object> remarca(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId,
			@RequestBody AtendimentoRemarcarSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		atendimentoValidator.validaRemarcar( request );
		
		atendimentoService.remarca( atendimentoId, request );
		return ResponseEntity.ok().build();		
	}	
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/retorno/{atendimentoId}")
	public ResponseEntity<Object> remarca(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId,
			@RequestBody AtendimentoRetornoSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		atendimentoValidator.validaRetorno( request );
		
		atendimentoService.registraRetorno( atendimentoId, request );
		return ResponseEntity.ok().build();		
	}
		
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/efetua/pagamento/{atendimentoId}")
	public ResponseEntity<Object> registraPagamento(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId,
			@RequestBody OrcamentoPagamentoSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
				
		atendimentoService.efetuaPagamento( logadoUID, atendimentoId, request );
		return ResponseEntity.ok().build();		
	}	
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/desfaz/pagamento/{atendimentoId}")
	public ResponseEntity<Object> registraPagamento(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId ) throws SistemaException {
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
				
		atendimentoService.desfazPagamento( logadoUID, atendimentoId );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/finaliza/{atendimentoId}")
	public ResponseEntity<Object> finalizaAtendimento(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId ) throws SistemaException {
				
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		atendimentoService.finalizaAtendimento( atendimentoId );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/cancela/{atendimentoId}")
	public ResponseEntity<Object> cancelaAtendimento(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId ) throws SistemaException {
				
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		atendimentoService.cancelaAtendimento( atendimentoId );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/registrado/{atendimentoId}")
	public ResponseEntity<Object> setaParaRegistrado(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId ) throws SistemaException {
				
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		atendimentoService.setaParaRegistrado( atendimentoId );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/esperando/{atendimentoId}")
	public ResponseEntity<Object> setaParaEsperando(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId ) throws SistemaException {
				
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		atendimentoService.setaParaEsperando( atendimentoId );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/altera/observacoes/{atendimentoId}")
	public ResponseEntity<Object> salvaObservacoes(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId,
			@RequestBody AtendimentoObservacoesSaveRequest request ) throws SistemaException {
				
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		atendimentoValidator.validaAlterObservacoes( request );
		atendimentoService.salvaObservacoes( atendimentoId, request );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/alter/consulta/concluida/{consultaId}/{concluida}")
	public ResponseEntity<Object> alterConsultaConcluida(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long consultaId, 
			@PathVariable boolean concluida ) throws SistemaException {
		
		autorizador.autorizaSeConsultaDeClinica( authorizationHeader, consultaId );
		
		atendimentoService.alterConsultaConcluida( consultaId, concluida );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/alter/exameitem/concluido/{exameItemId}/{concluido}")
	public ResponseEntity<Object> alterExameItemConcluido(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameItemId, 
			@PathVariable boolean concluido ) throws SistemaException {
		
		autorizador.autorizaSeExameItemDeClinica( authorizationHeader, exameItemId );
		
		atendimentoService.alterExameItemConcluido( exameItemId, concluido );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/alter/procitem/concluido/{procItemId}/{concluido}")
	public ResponseEntity<Object> alterProcItemConcluido(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long procItemId, 
			@PathVariable boolean concluido ) throws SistemaException {
		
		autorizador.autorizaSeProcedimentoItemDeClinica( authorizationHeader, procItemId );
		
		atendimentoService.alterProcedimentoItemConcluido( procItemId, concluido );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('atendimentoWRITE')")
	@PatchMapping("/inicia/{clinicaId}/{profissionalId}/{atendimentoId}/{turno}")
	public ResponseEntity<Object> iniciaAtendimento(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId,
			@PathVariable Long atendimentoId, 
			@PathVariable String turno ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		String perfil = tokenInfo.getPerfil();		
		if ( perfil.equalsIgnoreCase( UsuarioPerfil.PROFISSIONAL.name() ) )
			autorizador.autorizaSeProfissionalUsuario( authorizationHeader, profissionalId );  
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		atendimentoService.iniciaAtendimento( clinicaId, profissionalId, atendimentoId, turno );
		return ResponseEntity.ok().build();		
	}		
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/get/{atendimentoId}")
	public ResponseEntity<Object> get(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId) throws SistemaException {
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		AtendimentoResponse resp = atendimentoService.get( atendimentoId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/get/iniciado/{clinicaId}/{turno}/{histObsPageSize}")
	public ResponseEntity<Object> getIniciada(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable String turno,
			@PathVariable int histObsPageSize ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		AtendimentoIniciadoResponse resp = 
				atendimentoService.getIniciado( logadoUID, clinicaId, turno, histObsPageSize );
		return ResponseEntity.ok( resp );	
	}
		
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/load/pagamento/{atendimentoId}")
	public ResponseEntity<Object> loadPagamento(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId ) throws SistemaException {
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		OrcamentoPagamentoLoadResponse resp = atendimentoService.getPagamentoLoad( atendimentoId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/load/reg/{profissionalId}")
	public ResponseEntity<Object> getRegLoad( 
			@PathVariable Long profissionalId ) throws SistemaException {
		
		AtendimentoRegLoadResponse resp = atendimentoService.getRegLoad( profissionalId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/load/remarcar/{atendimentoId}")
	public ResponseEntity<Object> loadRemarcar(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId ) throws SistemaException {		
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		AtendimentoRemarcarLoadResponse resp = atendimentoService.getRemarcarLoad( atendimentoId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/load/retorno")
	public ResponseEntity<Object> loadRetorno() throws SistemaException {						
		AtendimentoRetornoLoadResponse resp = atendimentoService.retornoLoad();
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/load/alter/{atendimentoId}")
	public ResponseEntity<Object> getAlterLoad(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId ) throws SistemaException {
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		AtendimentoAlterLoadResponse resp = atendimentoService.getAlterLoad( atendimentoId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/load/tela")
	public ResponseEntity<Object> getTelaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		AtendimentoTelaLoadResponse resp = atendimentoService.getTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/load/fila/tela")
	public ResponseEntity<Object> getListaFilaTelaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		AtendimentoListaFilaTelaLoadResponse resp = atendimentoService.getFiltroResumidoTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/load/iniciado/tela")
	public ResponseEntity<Object> getIniciadaTelaLoad(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		AtendimentoIniciadaTelaLoadResponse resp = atendimentoService.getIniciadoTelaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody AtendimentoFiltroRequest request ) throws SistemaException {
				
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		atendimentoValidator.validaFiltro( request );

		List<AtendimentoResponse> lista = atendimentoService.filtra( clinicaId, request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@PostMapping("/lista/fila/{clinicaId}/{profissionalId}")
	public ResponseEntity<Object> listaFila(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId,
			@RequestBody AtendimentoListaFilaFiltroRequest request ) throws SistemaException {
				
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		autorizador.autorizaSeProfissionalDeClinica( authorizationHeader, clinicaId, profissionalId );
		
		atendimentoValidator.validaListaFila( request );
		
		List<AtendimentoResponse> lista = atendimentoService.listaFila( 
				clinicaId, profissionalId, request );
		
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@PostMapping("/lista/fila/completa/{clinicaId}/{profissionalId}")
	public ResponseEntity<Object> listaFilaCompleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId,
			@RequestBody AtendimentoListaFilaCompletaFiltroRequest request ) throws SistemaException {
				
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		autorizador.autorizaSeProfissionalDeClinica( authorizationHeader, clinicaId, profissionalId );
		
		atendimentoValidator.validaListaFilaCompleta( request );
		
		List<AtendimentoResponse> lista = atendimentoService.listaFilaCompleta( 
				clinicaId, profissionalId, request );
		
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/load/novoatendimento/tela")
	public ResponseEntity<Object> getNovaConsultaLoad( 
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
				
		NovoAtendimentoRegLoadResponse resp = atendimentoService.getNovoAtendimentoRegLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}

	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/load/agenda/tela")
	public ResponseEntity<Object> getConsultaAgendaLoad( 
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
				
		AtendimentoAgendaLoadResponse resp = atendimentoService.getAtendimentoAgendaLoad( clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/get/quantidades/pordia/{clinicaId}/{profissionalId}/{mes}/{ano}")
	public ResponseEntity<Object> agrupaPorDiaDoMes(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId,
			@PathVariable Long profissionalId,
			@PathVariable int mes,
			@PathVariable int ano ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		autorizador.autorizaSeProfissionalDeClinica( authorizationHeader, clinicaId, profissionalId );
		
		List<Object[]> resp = atendimentoService.agrupaPorDiaDeMes( clinicaId, profissionalId, mes, ano );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('atendimentoREAD')")
	@GetMapping("/get/quantidades/pordia/aid/{atendimentoId}/{mes}/{ano}")
	public ResponseEntity<Object> agrupaPorDiaDoMes(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId,
			@PathVariable int mes,
			@PathVariable int ano ) throws SistemaException {
				
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		List<Object[]> resp = atendimentoService.agrupaPorDiaDeMes( atendimentoId, mes, ano );
		return ResponseEntity.ok( resp );
	}
		
	@PreAuthorize("hasAuthority('atendimentoDELETE')")
	@DeleteMapping("/deleta/{atendimentoId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long atendimentoId ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		autorizador.autorizaPorAtendimentoEClinica( authorizationHeader, atendimentoId );
		
		atendimentoService.deleta( logadoUID, atendimentoId );
		return ResponseEntity.ok().build();
	}
}
