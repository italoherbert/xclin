package italo.xclin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.xclin.exception.SistemaException;
import italo.xclin.model.response.load.vinculos.UsuarioClinicaVinculosLoadResponse;
import italo.xclin.service.UsuarioClinicaVinculoService;

@RestController
@RequestMapping("/api/usuario/clinica/vinculos")
public class UsuarioClinicaVinculoController {

	@Autowired
	public UsuarioClinicaVinculoService usuarioClinicaVinculoService;
	
	@PreAuthorize("hasAuthority('usuarioWRITE')")
	@PostMapping("/vincula/{usuarioId}/{clinicaId}")
	public ResponseEntity<Object> vincula( 
			@PathVariable Long usuarioId,
			@PathVariable Long clinicaId ) throws SistemaException {
		
		usuarioClinicaVinculoService.vincula( usuarioId, clinicaId );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@GetMapping("/lista/{usuarioId}")
	public ResponseEntity<Object> lista( @PathVariable Long usuarioId ) throws SistemaException {
		UsuarioClinicaVinculosLoadResponse resp = usuarioClinicaVinculoService.vinculadas( usuarioId ); 
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('usuarioDELETE')")
	@DeleteMapping("/deleta/{vinculoId}")
	public ResponseEntity<Object> deleta( @PathVariable Long vinculoId ) throws SistemaException {
		usuarioClinicaVinculoService.deleta( vinculoId );
		return ResponseEntity.ok().build();
	}
		
}
