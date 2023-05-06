package italo.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.model.request.save.AcessoListaSaveRequest;
import italo.scm.model.response.AcessoResponse;
import italo.scm.service.AcessoService;

@RestController
@RequestMapping("/api/usuario/grupo/acessos")
public class AcessoController {

	@Autowired
	private AcessoService acessoService;
	
	@PreAuthorize("hasAuthority('usuarioGrupoREAD')")
	@GetMapping("/sincroniza/{id}")
	public ResponseEntity<Object> sincronizaAcessos( @PathVariable Long id ) throws SistemaException {
		List<AcessoResponse> lista = acessoService.sincronizaAcessos( id );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('usuarioGrupoWRITE')")
	@PostMapping("/salva/{id}")
	public ResponseEntity<Object> salvaAcessos( @PathVariable Long id, @RequestBody AcessoListaSaveRequest request ) throws SistemaException {
		acessoService.salvaAcessos( id, request );
		return ResponseEntity.ok().build();
	}
	
}
