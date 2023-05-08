package italo.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.jwt.JWTTokenInfo;
import italo.scm.logica.jwt.JWTTokenLogica;
import italo.scm.model.request.filtro.ClinicaFiltroRequest;
import italo.scm.model.request.save.ClinicaSaveRequest;
import italo.scm.model.response.ClinicaResponse;
import italo.scm.model.response.edit.ClinicaEditResponse;
import italo.scm.model.response.reg.ClinicaRegResponse;
import italo.scm.service.ClinicaService;
import italo.scm.validator.ClinicaValidator;

@RestController
@RequestMapping("/api/clinica")
public class ClinicaController {

	@Autowired
	private ClinicaService clinicaService;
	
	@Autowired
	private ClinicaValidator clinicaValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('clinicaWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( 
			@RequestHeader( "Authorization" ) String authHeader,
			@RequestBody ClinicaSaveRequest request ) throws SistemaException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		clinicaValidator.validaSave( request );
		clinicaService.registra( logadoUID, request );
		return ResponseEntity.ok().build(); 
	}
	
	@PreAuthorize("hasAuthority('clinicaWRITE')")
	@PutMapping("/altera/{id}")
	public ResponseEntity<Object> altera( 
			@PathVariable Long id, 
			@RequestBody ClinicaSaveRequest request ) throws SistemaException {
		
		clinicaValidator.validaSave( request );
		clinicaService.altera( id, request );
		return ResponseEntity.ok().build(); 	
	}
	
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( 
			@RequestBody ClinicaFiltroRequest request ) throws SistemaException {
		
		clinicaValidator.validaFiltro( request );
		List<ClinicaResponse> lista = clinicaService.filtra( request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> get( @PathVariable Long id ) throws SistemaException {
		ClinicaResponse resp = clinicaService.get( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/get/edit/{id}")
	public ResponseEntity<Object> getEdit( 
			@PathVariable Long id ) throws SistemaException {
		
		ClinicaEditResponse resp = clinicaService.getEdit( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/get/reg")
	public ResponseEntity<Object> getReg() throws SistemaException {
		ClinicaRegResponse resp = clinicaService.getReg();
		return ResponseEntity.ok( resp );
	}
		
	@PreAuthorize("hasAuthority('clinicaDELETE')")
	@GetMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta( 
			@PathVariable Long id ) throws SistemaException {
		
		clinicaService.deleta( id );
		return ResponseEntity.ok().build();
	}
	
}
