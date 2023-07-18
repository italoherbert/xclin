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
import italo.xclin.model.request.save.ProfissionalProcedimentoSaveRequest;
import italo.xclin.model.response.ProfissionalProcedimentoVinculoResponse;
import italo.xclin.model.response.load.edit.ProfissionalProcedimentoSaveLoadResponse;
import italo.xclin.service.ProfissionalProcedimentoVinculoService;

@RestController 
@RequestMapping("/api/conta/profissional/procedimento/vinculo")
public class ContaProfissionalProcedimentoVinculoController {

	@Autowired
	private ProfissionalProcedimentoVinculoService profissionalProcedimentoVinculoService;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/salva/{procedimentoId}")
	public ResponseEntity<Object> salva(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long procedimentoId,
			@RequestBody ProfissionalProcedimentoSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalProcedimentoVinculoService.salva( logadoUID, procedimentoId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/vincula/{procedimentoId}")
	public ResponseEntity<Object> vincula(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long procedimentoId ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalProcedimentoVinculoService.vincula( logadoUID, procedimentoId );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/{procedimentoId}")
	public ResponseEntity<Object> getVinculo(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long procedimentoId ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalProcedimentoVinculoResponse resp = profissionalProcedimentoVinculoService.getVinculo( logadoUID, procedimentoId );		
		return ResponseEntity.ok( resp );		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/lista")
	public ResponseEntity<Object> lista(
			@RequestHeader( "Authorization") String authorizationHeader ) throws ServiceException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		List<ProfissionalProcedimentoVinculoResponse> resp = profissionalProcedimentoVinculoService.lista( logadoUID );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/load/save")
	public ResponseEntity<Object> loadSave(
			@RequestHeader( "Authorization") String authorizationHeader ) throws ServiceException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalProcedimentoSaveLoadResponse resp = profissionalProcedimentoVinculoService.loadSave( logadoUID );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/deleta/{procedimentoId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long procedimentoId ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalProcedimentoVinculoService.deleta( logadoUID, procedimentoId );
		return ResponseEntity.ok().build();
	}
	
}

