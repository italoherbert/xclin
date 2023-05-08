package italo.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.scm.exception.SistemaException;
import italo.scm.integracao.LocalidadesIBGEIntegracao;
import italo.scm.model.response.MunicipioResponse;

@RestController
@RequestMapping("/api/localidade")
public class LocalidadeController {
	
	@Autowired
	private LocalidadesIBGEIntegracao localidadesIBGEIntegracao;
	
	@GetMapping("/uf/{ufid}/municipios")
	public ResponseEntity<Object> get( 
			@PathVariable int ufid ) throws SistemaException {
		
		List<MunicipioResponse> resp = localidadesIBGEIntegracao.listaMunicipios( ufid );
		return ResponseEntity.ok( resp );
	}

}
