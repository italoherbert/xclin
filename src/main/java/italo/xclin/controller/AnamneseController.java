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

import italo.xclin.exception.ServiceException;
import italo.xclin.exception.SistemaException;
import italo.xclin.model.request.save.AnamneseSaveRequest;
import italo.xclin.model.response.AnamneseResponse;
import italo.xclin.model.response.load.edit.AnamneseEditLoadResponse;
import italo.xclin.service.AnamneseService;
import italo.xclin.service.RelatorioService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.AnamneseValidator;

@RestController
@RequestMapping("/api/paciente/anamnese")
public class AnamneseController {

	@Autowired
	private AnamneseService anamneseService;
	
	@Autowired
	private AnamneseValidator anamneseValidator;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('pacienteWRITE')")
	@PostMapping("/salva/{pacienteId}")
	public ResponseEntity<Object> registra(
			@RequestHeader( "Authorization") String authorizationHeader,
			@PathVariable Long pacienteId,
			@RequestBody AnamneseSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		anamneseValidator.validaSave( request );
		anamneseService.salva( pacienteId, request );
		return ResponseEntity.ok().build();
	}
		
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/get/{pacienteId}")
	public ResponseEntity<Object> get(
			@RequestHeader( "Authorization") String authorizationHeader,
			@PathVariable Long pacienteId ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		AnamneseResponse resp = anamneseService.get( pacienteId );
		return ResponseEntity.ok( resp );
	}
		
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/get/edit/{pacienteId}")
	public ResponseEntity<Object> getEditLoad( 
			@RequestHeader( "Authorization") String authorizationHeader,
			@PathVariable Long pacienteId ) throws SistemaException {		

		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		AnamneseEditLoadResponse resp = anamneseService.getEditLoad( pacienteId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping(value="/relatorio/{pacienteId}", produces = MediaType.APPLICATION_PDF_VALUE )
	@ResponseBody
	public byte[] getRelatorioPDF(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long pacienteId ) throws SistemaException {
		 
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		return relatorioService.geraRelatorio( pacienteId );		
	}
	
}
