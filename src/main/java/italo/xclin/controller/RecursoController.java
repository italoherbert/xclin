package italo.xclin.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.xclin.exception.SistemaException;
import italo.xclin.model.request.filtro.RecursoFiltroRequest;
import italo.xclin.model.request.save.RecursoSaveRequest;
import italo.xclin.model.response.RecursoResponse;
import italo.xclin.service.RecursoService;
import italo.xclin.validator.RecursoValidator;

@RestController
@RequestMapping("/api/recurso")
public class RecursoController {
	
	@Autowired
	private RecursoService recursoService;
	
	@Autowired
	private RecursoValidator recursoValidator;
	
	@PreAuthorize("hasAuthority('recursoWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( @RequestBody RecursoSaveRequest request ) throws SistemaException {
		recursoValidator.validaSave( request );
		recursoService.registra( request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('recursoWRITE')")
	@PutMapping("/altera/{id}")
	public ResponseEntity<Object> altera( @PathVariable Long id, @RequestBody RecursoSaveRequest request ) throws SistemaException {
		recursoValidator.validaSave( request );
		recursoService.altera( id, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('recursoREAD')")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( @RequestBody RecursoFiltroRequest request ) throws SistemaException {
		recursoValidator.validaFiltro( request );
		List<RecursoResponse> lista = recursoService.filtra( request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('recursoREAD')")
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> get( @PathVariable Long id ) throws SistemaException {
		RecursoResponse resp = recursoService.get( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('recursoDELETE')")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta( @PathVariable Long id ) throws SistemaException {
		recursoService.deleta( id );
		return ResponseEntity.ok().build();
	}

}
