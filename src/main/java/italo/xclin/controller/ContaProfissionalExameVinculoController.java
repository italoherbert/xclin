package italo.xclin.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.xclin.exception.ServiceException;
import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.save.ProfissionalExameSaveRequest;
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
	@PutMapping("/salva/{exameId}")
	public ResponseEntity<Object> salva(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long exameId,
			@RequestBody ProfissionalExameSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalExameVinculoService.salva( logadoUID, exameId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/vincula/{exameId}")
	public ResponseEntity<Object> vincula(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalExameVinculoService.vincula( logadoUID, exameId );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/{exameId}")
	public ResponseEntity<Object> getVinculo(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalExameVinculoResponse resp = profissionalExameVinculoService.getVinculo( logadoUID, exameId );		
		return ResponseEntity.ok( resp );		
	}
	
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
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		ProfissionalExameSaveLoadResponse resp = profissionalExameVinculoService.loadSave( logadoUID, clinicasIDs );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/deleta/{exameId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalExameVinculoService.deleta( logadoUID, exameId );
		return ResponseEntity.ok().build();
	}
	
}
