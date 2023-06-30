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
import italo.xclin.model.request.filtro.ExameFiltroRequest;
import italo.xclin.model.request.save.ExameSaveRequest;
import italo.xclin.model.response.ExameVinculoResponse;
import italo.xclin.service.ExameVinculoService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.ExameVinculoValidator;

@RestController
@RequestMapping("/api/exame/vinculo")
public class ExameVinculoController {

	@Autowired
	private ExameVinculoService exameVinculoService;
	
	@Autowired
	private ExameVinculoValidator exameVinculoValidator;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('exameWRITE')")
	@PostMapping("/registra/{pacienteId}")
	public ResponseEntity<Object> registra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long pacienteId, 
			@RequestBody ExameSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		exameVinculoValidator.validaSave( request );
		exameVinculoService.registra( pacienteId, request );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@PostMapping("/filtra/{pacienteId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long pacienteId, 
			@RequestBody ExameFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		exameVinculoValidator.validaFiltro( request );
		List<ExameVinculoResponse> lista = exameVinculoService.filtra( pacienteId, request );
		return ResponseEntity.ok( lista );
	}	
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@GetMapping("/get/{exameId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
		
		autorizador.autorizaSeExameDeClinica( authorizationHeader, exameId );
		
		ExameVinculoResponse resp = exameVinculoService.get( exameId );
		return ResponseEntity.ok( resp );
	}	
	
	@PreAuthorize("hasAuthority('exameDELETE')")
	@DeleteMapping("/deleta/{exameId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
		
		autorizador.autorizaSeExameDeClinica( authorizationHeader, exameId );
		
		exameVinculoService.deleta( exameId );
		return ResponseEntity.ok().build();
	}
	
}
