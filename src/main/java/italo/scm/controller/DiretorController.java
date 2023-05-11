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
import italo.scm.model.request.filtro.DiretorFiltroRequest;
import italo.scm.model.request.save.DiretorSaveRequest;
import italo.scm.model.response.DiretorResponse;
import italo.scm.model.response.load.DiretorDetalhesLoadResponse;
import italo.scm.service.DiretorService;
import italo.scm.validator.DiretorValidator;

@RestController
@RequestMapping("/api/diretor")
public class DiretorController {

	@Autowired
	private DiretorService diretorService;
	
	@Autowired
	private DiretorValidator diretorValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('diretorWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( 
			@RequestHeader( "Authorization" ) String authHeader,
			@RequestBody DiretorSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		diretorValidator.validaSave( request );
		diretorService.registra( logadoUID, request );
		return ResponseEntity.ok().build(); 
	}
	
	@PreAuthorize("hasAuthority('diretorWRITE')")
	@PutMapping("/altera/{id}")
	public ResponseEntity<Object> altera( 
			@PathVariable Long id, 
			@RequestBody DiretorSaveRequest request ) throws SistemaException {
		
		diretorValidator.validaSave( request );
		diretorService.altera( id, request );
		return ResponseEntity.ok().build(); 	
	}
	
	@PreAuthorize("hasAuthority('diretorREAD')")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( 
			@RequestBody DiretorFiltroRequest request ) throws SistemaException {
		
		diretorValidator.validaFiltro( request );
		List<DiretorResponse> lista = diretorService.filtra( request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('diretorREAD')")
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> get( @PathVariable Long id ) throws SistemaException {
		DiretorResponse resp = diretorService.get( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('diretorREAD')")
	@GetMapping("/get/detalhes/{id}")
	public ResponseEntity<Object> getDetalhes( @PathVariable Long id ) throws SistemaException {
		DiretorDetalhesLoadResponse resp = diretorService.getDetalhesLoad( id );
		return ResponseEntity.ok( resp );
	}
		
	@PreAuthorize("hasAuthority('diretorDELETE')")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta( 
			@PathVariable Long id ) throws SistemaException {
		
		diretorService.deleta( id );
		return ResponseEntity.ok().build();
	}
	
}
