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
import italo.xclin.model.response.ExameResponse;
import italo.xclin.service.ExameService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.ExameValidator;

@RestController
@RequestMapping("/api/exame")
public class ExameController {

	@Autowired
	private ExameService exameService;
	
	@Autowired
	private ExameValidator exameValidator;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('exameWRITE')")
	@PostMapping("/registra/{pacienteId}")
	public ResponseEntity<Object> registra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long pacienteId, 
			@RequestBody ExameSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		exameValidator.validaSave( request );
		exameService.registra( pacienteId, request );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@PostMapping("/filtra/{pacienteId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long pacienteId, 
			@RequestBody ExameFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		exameValidator.validaFiltro( request );
		List<ExameResponse> lista = exameService.filtra( pacienteId, request );
		return ResponseEntity.ok( lista );
	}	
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@GetMapping("/get/{exameId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
		
		autorizador.autorizaSeExameDeClinica( authorizationHeader, exameId );
		
		ExameResponse resp = exameService.get( exameId );
		return ResponseEntity.ok( resp );
	}	
	
	@PreAuthorize("hasAuthority('exameDELETE')")
	@DeleteMapping("/deleta/{exameId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
		
		autorizador.autorizaSeExameDeClinica( authorizationHeader, exameId );
		
		exameService.deleta( exameId );
		return ResponseEntity.ok().build();
	}
	
}
