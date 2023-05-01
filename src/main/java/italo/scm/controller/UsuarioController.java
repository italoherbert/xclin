package italo.scm.controller;

import java.util.List;

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
import italo.scm.model.request.UsuarioRequest;
import italo.scm.model.request.filtro.UsuarioFiltroRequest;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.service.UsuarioService;
import italo.scm.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

	private UsuarioService usuarioService;
	
	private UsuarioValidator usuarioValidator;
	
	@PreAuthorize("usuarioWRITE")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( @RequestBody UsuarioRequest request ) throws SistemaException {
		usuarioValidator.validaRegistro( request );
		usuarioService.registra( request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("usuarioWRITE")
	@PutMapping("/altera/{id}")
	public ResponseEntity<Object> altera( @PathVariable Long id, @RequestBody UsuarioRequest request ) throws SistemaException {
		usuarioValidator.validaSave( request );
		usuarioService.altera( id, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("usuarioREAD")
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> get( @PathVariable Long id ) throws SistemaException {
		UsuarioResponse resp = usuarioService.get( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("usuarioREAD")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( @RequestBody UsuarioFiltroRequest request ) throws SistemaException {
		usuarioValidator.validaFiltro( request );
		List<UsuarioResponse> lista = usuarioService.filtra( request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("usuarioDELETE")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta( @PathVariable Long id ) throws SistemaException {
		usuarioService.delete( id );
		return ResponseEntity.ok().build();
	}
	
}
