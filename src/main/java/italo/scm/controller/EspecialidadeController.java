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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.model.request.filtro.EspecialidadeFiltroRequest;
import italo.scm.model.request.save.EspecialidadeSaveRequest;
import italo.scm.model.response.EspecialidadeResponse;
import italo.scm.service.EspecialidadeService;
import italo.scm.validator.EspecialidadeValidator;

@RestController
@RequestMapping("/api/especialidade")
public class EspecialidadeController {
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Autowired
	private EspecialidadeValidator especialidadeValidator;
	
	@PreAuthorize("hasAuthority('especialidadeWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( @RequestBody EspecialidadeSaveRequest request ) throws SistemaException {
		especialidadeValidator.validaSave( request );
		especialidadeService.registra( request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('especialidadeWRITE')")
	@PutMapping("/altera/{id}")
	public ResponseEntity<Object> altera( @PathVariable Long id, @RequestBody EspecialidadeSaveRequest request ) throws SistemaException {
		especialidadeValidator.validaSave( request );
		especialidadeService.altera( id, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('especialidadeREAD')")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( @RequestBody EspecialidadeFiltroRequest request ) throws SistemaException {
		especialidadeValidator.validaFiltro( request );
		List<EspecialidadeResponse> lista = especialidadeService.filtra( request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('especialidadeREAD')")
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> get( @PathVariable Long id ) throws SistemaException {
		EspecialidadeResponse resp = especialidadeService.get( id );
		return ResponseEntity.ok( resp );
	}
		
	@PreAuthorize("hasAuthority('especialidadeDELETE')")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta( @PathVariable Long id ) throws SistemaException {
		especialidadeService.deleta( id );
		return ResponseEntity.ok().build();
	}

}

