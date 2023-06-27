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
import italo.xclin.model.request.save.PacienteAnexoSaveRequest;
import italo.xclin.model.response.PacienteAnexoArquivoResponse;
import italo.xclin.model.response.PacienteAnexoResponse;
import italo.xclin.model.response.load.tela.PacienteAnexoTelaLoadResponse;
import italo.xclin.service.PacienteAnexoService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.PacienteAnexoValidator;

@RestController
@RequestMapping("/api/paciente/anexo")
public class PacienteAnexoController {

	@Autowired
	private PacienteAnexoService pacienteAnexoService;
	
	@Autowired
	private PacienteAnexoValidator pacienteAnexoValidator;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('pacienteWRITE')")
	@PostMapping("/registra/{pacienteId}")
	public ResponseEntity<Object> registra( 
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long pacienteId, 
			@RequestBody PacienteAnexoSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		pacienteAnexoValidator.validaSave( request );
		pacienteAnexoService.registra( pacienteId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/get/arquivo/{pacienteAnexoId}")
	public ResponseEntity<Object> getArquivo(
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long pacienteAnexoId ) throws SistemaException {
		
		autorizador.autorizaSeAnexoDeClinica( authorizationHeader, pacienteAnexoId );
		
		PacienteAnexoArquivoResponse resp = pacienteAnexoService.get( pacienteAnexoId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/load/tela/{pacienteId}")
	public ResponseEntity<Object> telaLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long pacienteId ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		PacienteAnexoTelaLoadResponse resp = pacienteAnexoService.telaLoad( pacienteId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('pacienteREAD')")
	@GetMapping("/lista/{pacienteId}")
	public ResponseEntity<Object> lista(
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long pacienteId ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		List<PacienteAnexoResponse> lista = pacienteAnexoService.lista( pacienteId );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('pacienteDELETE')")
	@DeleteMapping("/deleta/{pacienteAnexoId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long pacienteAnexoId ) throws SistemaException {
		
		autorizador.autorizaSeAnexoDeClinica( authorizationHeader, pacienteAnexoId );
		
		pacienteAnexoService.deleta( pacienteAnexoId  );
		return ResponseEntity.ok().build();
	}
	
}
