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
import italo.xclin.model.request.save.DiretorClinicaVinculoListaSaveRequest;
import italo.xclin.model.response.load.outros.DiretorClinicaVinculosLoadResponse;
import italo.xclin.service.DiretorClinicaVinculoService;

@RestController
@RequestMapping("/api/diretor/clinica/vinculos")
public class DiretorClinicaVinculoController {

	@Autowired
	public DiretorClinicaVinculoService diretorClinicaVinculoService;
	
	@PreAuthorize("hasAuthority('diretorWRITE')")
	@PutMapping("/salva/{diretorId}")
	public ResponseEntity<Object> salva( 
			@PathVariable Long diretorId, 
			@RequestBody DiretorClinicaVinculoListaSaveRequest request ) throws SistemaException {
		diretorClinicaVinculoService.salva( diretorId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('diretorREAD')")
	@GetMapping("/get/{diretorId}")
	public ResponseEntity<Object> getVinculosLoad( @PathVariable Long diretorId ) throws SistemaException {
		DiretorClinicaVinculosLoadResponse resp = diretorClinicaVinculoService.getVinculoLoad( diretorId );
		return ResponseEntity.ok( resp );
	}
	
}
