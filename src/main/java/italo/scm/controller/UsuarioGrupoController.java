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
import italo.scm.model.request.filtro.UsuarioGrupoFiltroRequest;
import italo.scm.model.request.save.AcessoListaSaveRequest;
import italo.scm.model.request.save.UsuarioGrupoSaveRequest;
import italo.scm.model.response.AcessoResponse;
import italo.scm.model.response.UsuarioGrupoResponse;
import italo.scm.model.response.detalhes.UsuarioGrupoDetalhesResponse;
import italo.scm.model.response.edit.UsuarioGrupoEditResponse;
import italo.scm.service.UsuarioGrupoService;
import italo.scm.validator.UsuarioGrupoValidator;

@RestController
@RequestMapping( "/api/usuario/grupo")
public class UsuarioGrupoController {

	@Autowired
	private UsuarioGrupoService usuarioGrupoService;
	
	@Autowired
	private UsuarioGrupoValidator usuarioGrupoValidator;
	
	@PreAuthorize("hasAuthority('usuarioGrupoREAD')")
	@GetMapping("/acessos/sincroniza/{id}")
	public ResponseEntity<Object> sincronizaAcessos( @PathVariable Long id ) throws SistemaException {
		List<AcessoResponse> lista = usuarioGrupoService.sincronizaAcessos( id );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('usuarioGrupoWRITE')")
	@PostMapping("/acessos/salva/{id}")
	public ResponseEntity<Object> salvaAcessos( @PathVariable Long id, @RequestBody AcessoListaSaveRequest request ) throws SistemaException {
		usuarioGrupoService.salvaAcessos( id, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('usuarioGrupoWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( @RequestBody UsuarioGrupoSaveRequest request ) throws SistemaException {
		usuarioGrupoValidator.validaSave( request ); 
		usuarioGrupoService.registra( request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('usuarioGrupoWRITE')")
	@PutMapping("/altera/{id}")
	public ResponseEntity<Object> altera( @PathVariable Long id, @RequestBody UsuarioGrupoSaveRequest request ) throws SistemaException {
		usuarioGrupoValidator.validaSave( request ); 
		usuarioGrupoService.altera( id, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('usuarioGrupoREAD')")
	@PostMapping("/filtra") 
	public ResponseEntity<Object> filtra( @RequestBody UsuarioGrupoFiltroRequest request ) throws SistemaException {
		usuarioGrupoValidator.validaFiltro( request );
		List<UsuarioGrupoResponse> lista = usuarioGrupoService.filtra( request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('usuarioGrupoREAD')")
	@GetMapping("/get/{id}") 
	public ResponseEntity<Object> get( @PathVariable Long id ) throws SistemaException {
		UsuarioGrupoResponse resp = usuarioGrupoService.get( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('usuarioGrupoREAD')")
	@GetMapping("/get/detalhes/{id}")
	public ResponseEntity<Object> getDetalhes( @PathVariable Long id ) throws SistemaException {
		UsuarioGrupoDetalhesResponse resp = usuarioGrupoService.getDetalhes( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('usuarioGrupoREAD')")
	@GetMapping("/get/edit/{id}")
	public ResponseEntity<Object> getEdit( @PathVariable Long id ) throws SistemaException {
		UsuarioGrupoEditResponse resp = usuarioGrupoService.getEdit( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('usuarioGrupoDELETE')")
	@DeleteMapping("/deleta/{id}") 
	public ResponseEntity<Object> deleta( @PathVariable Long id ) throws SistemaException {
		usuarioGrupoService.deleta( id );
		return ResponseEntity.ok().build();
	}
	
	
	
}
