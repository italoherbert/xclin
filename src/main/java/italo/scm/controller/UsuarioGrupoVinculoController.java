package italo.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.model.request.save.UsuarioGrupoVinculadoListSaveRequest;
import italo.scm.model.response.UsuarioGrupoVinculadosResponse;
import italo.scm.service.UsuarioGrupoVinculoService;

@RestController
@RequestMapping("/api/usuario/grupo/vinculos")
public class UsuarioGrupoVinculoController {

	@Autowired
	private UsuarioGrupoVinculoService usuarioGrupoVinculoService;
	
	@PreAuthorize("hasAuthority('usuarioWRITE')")
	@PutMapping("/salva/{id}")
	public ResponseEntity<Object> salva( 
			@PathVariable Long id, 
			@RequestBody UsuarioGrupoVinculadoListSaveRequest request ) 
					throws SistemaException {		
		usuarioGrupoVinculoService.salva( id, request );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@GetMapping("/vinculados/{id}")
	public ResponseEntity<Object> vinculados( @PathVariable Long id ) throws SistemaException {
		UsuarioGrupoVinculadosResponse resp = usuarioGrupoVinculoService.vinculados( id );
		return ResponseEntity.ok( resp );
	}
	
}
