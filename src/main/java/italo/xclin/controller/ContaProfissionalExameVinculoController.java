package italo.xclin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.xclin.exception.ServiceException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.response.ProfissionalExameVinculoResponse;
import italo.xclin.model.response.load.edit.ProfissionalExameSaveLoadResponse;
import italo.xclin.service.ProfissionalExameVinculoService;

@RestController 
@RequestMapping("/api/conta/profissional/exame/vinculo")
public class ContaProfissionalExameVinculoController {

	@Autowired
	private ProfissionalExameVinculoService profissionalExameVinculoService;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/lista")
	public ResponseEntity<Object> lista(
			@RequestHeader( "Authorization") String authorizationHeader ) throws ServiceException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		List<ProfissionalExameVinculoResponse> resp = profissionalExameVinculoService.lista( logadoUID );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/load/save")
	public ResponseEntity<Object> loadSave(
			@RequestHeader( "Authorization") String authorizationHeader ) throws ServiceException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalExameSaveLoadResponse resp = profissionalExameVinculoService.loadSave( logadoUID );
		return ResponseEntity.ok( resp );
	}
	
}
