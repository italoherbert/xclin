package italo.xclin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.xclin.exception.SistemaException;
import italo.xclin.model.request.save.UsuarioGrupoVinculoListaSaveRequest;
import italo.xclin.model.response.load.outros.UsuarioGrupoVinculosLoadResponse;
import italo.xclin.service.UsuarioGrupoVinculoService;

@RestController
@RequestMapping("/api/usuario/grupo/vinculos")
public class UsuarioGrupoVinculoController {

	@Autowired
	private UsuarioGrupoVinculoService usuarioGrupoVinculoService;
	
	@PreAuthorize("hasAuthority('usuarioWRITE')")
	@PutMapping("/salva/{id}")
	public ResponseEntity<Object> salva( 
			@PathVariable Long id, 
			@RequestBody UsuarioGrupoVinculoListaSaveRequest request ) 
					throws SistemaException {		
		usuarioGrupoVinculoService.salva( id, request );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> vinculados( @PathVariable Long id ) throws SistemaException {
		UsuarioGrupoVinculosLoadResponse resp = usuarioGrupoVinculoService.vinculados( id );
		return ResponseEntity.ok( resp );
	}
	
}
