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
import italo.xclin.model.request.save.ProfissionalClinicaVinculoListaSaveRequest;
import italo.xclin.model.response.load.outros.ProfissionalClinicaVinculosLoadResponse;
import italo.xclin.service.ProfissionalClinicaVinculoService;

@RestController
@RequestMapping("/api/profissional/clinica/vinculos")
public class ProfissionalClinicaVinculoController {

	@Autowired
	public ProfissionalClinicaVinculoService profissionalClinicaVinculoService;
	
	@PreAuthorize("hasAuthority('profissionalWRITE')")
	@PutMapping("/salva/{profissionalId}")
	public ResponseEntity<Object> salva( 
			@PathVariable Long profissionalId, 
			@RequestBody ProfissionalClinicaVinculoListaSaveRequest request ) throws SistemaException {
		profissionalClinicaVinculoService.salva( profissionalId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('profissionalREAD')")
	@GetMapping("/get/{profissionalId}")
	public ResponseEntity<Object> getVinculosLoad( @PathVariable Long profissionalId ) throws SistemaException {
		ProfissionalClinicaVinculosLoadResponse resp = profissionalClinicaVinculoService.getVinculoLoad( profissionalId );
		return ResponseEntity.ok( resp );
	}
	
}

