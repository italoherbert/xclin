package italo.scm.controller;

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

import italo.scm.exception.SistemaException;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.request.save.ProfissionalEspecialidadeSaveRequest;
import italo.scm.model.request.save.ProfissionalSaveRequest;
import italo.scm.model.response.ProfissionalEspecialidadeVinculoResponse;
import italo.scm.model.response.load.ProfissionalContaEspecialidadeSaveLoadResponse;
import italo.scm.model.response.load.ProfissionalEspecialidadeVinculosLoadResponse;
import italo.scm.model.response.load.detalhes.ProfissionalDetalhesLoadResponse;
import italo.scm.model.response.load.edit.ProfissionalEditLoadResponse;
import italo.scm.service.ProfissionalEspecialidadeVinculoService;
import italo.scm.service.ProfissionalService;
import italo.scm.validator.ProfissionalValidator;

@RestController
@RequestMapping("/api/conta/profissional")
public class ProfissionalContaController {

	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private ProfissionalEspecialidadeVinculoService profissionalEspecialidadeVinculosService;
		
	@Autowired
	private ProfissionalValidator profissionalValidator;
		
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
		
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/altera/logado")
	public ResponseEntity<Object> alteraPorLogadoUID( 
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@RequestBody ProfissionalSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalValidator.validaSave( request );
		profissionalService.alteraPorLogadoUID( logadoUID, request );
		return ResponseEntity.ok().build(); 	
	}
		
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/salva/especialidade/vinculo/logado/{especialidadeId}")
	public ResponseEntity<Object> salvaContaEspecialidade(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId,
			@RequestBody ProfissionalEspecialidadeSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalEspecialidadeVinculosService.salva( logadoUID, especialidadeId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/salva/add/especialidade/vinculo/logado/{especialidadeId}")
	public ResponseEntity<Object> salvaAddContaEspecialidade(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalEspecialidadeVinculosService.salvaAdd( logadoUID, especialidadeId );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/edit/logado")
	public ResponseEntity<Object> getEditLoadPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalEditLoadResponse resp = profissionalService.getEditLoadPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/detalhes/logado")
	public ResponseEntity<Object> getDetalhesLoadPorLogadoUID(
			@RequestHeader("Authorization") String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalDetalhesLoadResponse resp = profissionalService.getDetalhesLoadPorLogadoUID( logadoUID );
		return ResponseEntity.ok( resp );
	}	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/lista/especialidades/vinculos/logado")
	public ResponseEntity<Object> listaVinculos(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalEspecialidadeVinculosLoadResponse resp = profissionalEspecialidadeVinculosService.getVinculosLoad( logadoUID );
		
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/especialidades/save/logado")
	public ResponseEntity<Object> getContaEspecialidadeSaveLoad(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalContaEspecialidadeSaveLoadResponse resp = profissionalEspecialidadeVinculosService.getEspecialidadeSaveLoad( logadoUID );
		
		return ResponseEntity.ok( resp );		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/get/especialidade/vinculo/logado/{especialidadeId}")
	public ResponseEntity<Object> getEspecialidadeVinculo(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId ) throws SistemaException {
			
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		ProfissionalEspecialidadeVinculoResponse resp = profissionalEspecialidadeVinculosService.get( logadoUID, especialidadeId );		
		return ResponseEntity.ok( resp );		
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/deleta/especialidade/vinculo/logado/{especialidadeId}")
	public ResponseEntity<Object> deletaContaEspecialidade(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalEspecialidadeVinculosService.deleta( logadoUID, especialidadeId );
		return ResponseEntity.ok().build();
	}
	
}
