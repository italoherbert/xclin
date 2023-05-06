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
import italo.scm.model.request.filtro.UsuarioFiltroRequest;
import italo.scm.model.request.save.UsuarioSaveRequest;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.edit.UsuarioEditResponse;
import italo.scm.model.response.reg.UsuarioRegResponse;
import italo.scm.service.UsuarioService;
import italo.scm.validator.UsuarioValidator;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
		
	@Autowired
	private UsuarioValidator usuarioValidator;
	
	@PreAuthorize("hasAuthority('usuarioWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( @RequestBody UsuarioSaveRequest request ) throws SistemaException {
		usuarioValidator.validaRegistro( request );
		usuarioService.registra( request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('usuarioWRITE')")
	@PutMapping("/altera/{id}")
	public ResponseEntity<Object> altera( @PathVariable Long id, @RequestBody UsuarioSaveRequest request ) throws SistemaException {
		usuarioValidator.validaSave( request );
		usuarioService.altera( id, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@GetMapping("/busca/{id}")
	public ResponseEntity<Object> get( @PathVariable Long id ) throws SistemaException {
		UsuarioResponse resp = usuarioService.get( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( @RequestBody UsuarioFiltroRequest request ) throws SistemaException {
		usuarioValidator.validaFiltro( request );
		List<UsuarioResponse> lista = usuarioService.filtra( request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@GetMapping("/get/reg")
	public ResponseEntity<Object> getDadosReg() throws SistemaException {
		UsuarioRegResponse resp = usuarioService.getDadosReg();
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@GetMapping("/get/edit/{id}")
	public ResponseEntity<Object> getDadosEdit( @PathVariable Long id ) throws SistemaException {
		UsuarioEditResponse resp = usuarioService.getDadosEdit( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('usuarioDELETE')")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta( @PathVariable Long id ) throws SistemaException {
		usuarioService.delete( id );
		return ResponseEntity.ok().build();
	}
	
}
