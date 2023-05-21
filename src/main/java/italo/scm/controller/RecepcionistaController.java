package italo.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.request.filtro.RecepcionistaFiltroRequest;
import italo.scm.model.request.save.RecepcionistaSaveRequest;
import italo.scm.model.response.RecepcionistaResponse;
import italo.scm.model.response.load.RecepcionistaEditLoadResponse;
import italo.scm.model.response.load.RecepcionistaRegLoadResponse;
import italo.scm.service.RecepcionistaService;
import italo.scm.validator.RecepcionistaValidator;

@RestController
@RequestMapping("/api/recepcionista")
public class RecepcionistaController {

	@Autowired
	private RecepcionistaService recepcionistaService;
	
	@Autowired
	private RecepcionistaValidator recepcionistaValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('recepcionistaWRITE')")
	@PostMapping("/registra/{clinicaId}")
	public ResponseEntity<Object> registra( 
			@RequestHeader( "Authorization" ) String authHeader,
			@PathVariable Long clinicaId,
			@RequestBody RecepcionistaSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		recepcionistaValidator.validaSave( request );
		recepcionistaService.registra( logadoUID, clinicaId, request );
		return ResponseEntity.ok().build(); 
	}
	
	@PreAuthorize("hasAuthority('recepcionistaWRITE')")
	@PutMapping("/altera/{clinicaId}/{recepcionistaId}")
	public ResponseEntity<Object> altera( 
			@PathVariable Long clinicaId,
			@PathVariable Long recepcionistaId, 
			@RequestBody RecepcionistaSaveRequest request ) throws SistemaException {
		
		recepcionistaValidator.validaSave( request );
		recepcionistaService.altera( clinicaId, recepcionistaId, request );
		return ResponseEntity.ok().build(); 	
	}
	
	@PreAuthorize("hasAuthority('recepcionistaREAD')")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( 
			@RequestBody RecepcionistaFiltroRequest request ) throws SistemaException {
		
		recepcionistaValidator.validaFiltro( request );
		List<RecepcionistaResponse> lista = recepcionistaService.filtra( request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('recepcionistaREAD')")
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> get( @PathVariable Long id ) throws SistemaException {
		RecepcionistaResponse resp = recepcionistaService.get( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('recepcionistaREAD')")
	@GetMapping("/get/reg")
	public ResponseEntity<Object> getRegLoad() throws SistemaException {
		RecepcionistaRegLoadResponse resp = recepcionistaService.getRegLoad();
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('recepcionistaREAD')")
	@GetMapping("/get/edit/{id}")
	public ResponseEntity<Object> getEditLoad( @PathVariable Long id ) throws SistemaException {
		RecepcionistaEditLoadResponse resp = recepcionistaService.getEditLoad( id );
		return ResponseEntity.ok( resp );
	}
		
	@PreAuthorize("hasAuthority('recepcionistaDELETE')")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta( 
			@PathVariable Long id ) throws SistemaException {
		
		recepcionistaService.deleta( id );
		return ResponseEntity.ok().build();
	}
	
}

