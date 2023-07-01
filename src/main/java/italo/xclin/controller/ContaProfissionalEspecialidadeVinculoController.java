package italo.xclin.controller;

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

import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.save.ProfissionalEspecialidadeSaveRequest;
import italo.xclin.model.response.ProfissionalEspecialidadeVinculoResponse;
import italo.xclin.model.response.load.edit.ProfissionalEspecialidadeSaveLoadResponse;
import italo.xclin.model.response.load.vinculos.ProfissionalEspecialidadeVinculosLoadResponse;
import italo.xclin.service.ProfissionalEspecialidadeVinculoService;

@RestController 
@RequestMapping("/api/conta/profissional/especialidade/vinculo")
public class ContaProfissionalEspecialidadeVinculoController {
	
	@Autowired
	private ProfissionalEspecialidadeVinculoService profissionalEspecialidadeVinculoService;
				
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/salva/{especialidadeId}")
	public ResponseEntity<Object> salvaContaEspecialidade(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId,
			@RequestBody ProfissionalEspecialidadeSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalEspecialidadeVinculoService.salva( logadoUID, especialidadeId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/vincula/{especialidadeId}")
	public ResponseEntity<Object> salvaAddContaEspecialidade(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalEspecialidadeVinculoService.vinculaPorLogadoUID( logadoUID, especialidadeId );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/lista")
	public ResponseEntity<Object> lista(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalEspecialidadeVinculosLoadResponse resp = profissionalEspecialidadeVinculoService.getVinculosLoad( logadoUID );
		
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/load/save")
	public ResponseEntity<Object> getContaEspecialidadeSaveLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalEspecialidadeSaveLoadResponse resp = profissionalEspecialidadeVinculoService.getEspecialidadeSaveLoad( logadoUID );
		
		return ResponseEntity.ok( resp );		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/{especialidadeId}")
	public ResponseEntity<Object> getEspecialidadeVinculo(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalEspecialidadeVinculoResponse resp = profissionalEspecialidadeVinculoService.getPorLogadoUID( logadoUID, especialidadeId );		
		return ResponseEntity.ok( resp );		
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/deleta/{especialidadeId}")
	public ResponseEntity<Object> deletaContaEspecialidade(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalEspecialidadeVinculoService.deleta( logadoUID, especialidadeId );
		return ResponseEntity.ok().build();
	}
	
}
