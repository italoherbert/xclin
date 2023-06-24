package italo.xclin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import italo.xclin.model.request.save.AnamneseSaveRequest;
import italo.xclin.model.response.AnamneseResponse;
import italo.xclin.model.response.load.edit.AnamneseEditLoadResponse;
import italo.xclin.model.response.load.reg.AnamneseRegLoadResponse;
import italo.xclin.service.AnamneseService;
import italo.xclin.service.autorizador.Autorizador;

@RestController
@RequestMapping("/api/anamnese")
public class AnamneseController {

	@Autowired
	private AnamneseService anamneseService;
	
	@Autowired
	private Autorizador autorizador;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('anamneseWRITE')")
	@PostMapping("/vincula/{pacienteId}/{anamneseModeloId}")
	public ResponseEntity<Object> vinculaAnamnese(
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long pacienteId, 
			@PathVariable Long anamneseModeloId ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		autorizador.autorizaSeAnamneseModeloDeProfissionalLogado( authorizationHeader, anamneseModeloId );
	
		anamneseService.vinculaAnamnese( pacienteId, anamneseModeloId );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('anamneseWRITE')")
	@PutMapping("/altera/{anamneseId}")	
	public ResponseEntity<Object> alteraPerguntas(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long anamneseId, 
			@RequestBody AnamneseSaveRequest request ) throws SistemaException {
				
		anamneseService.alteraAnamnesePerguntas( anamneseId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('anamneseREAD')")
	@GetMapping("/get/{anamneseId}")	
	public ResponseEntity<Object> get(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long anamneseId ) throws SistemaException {
				
		AnamneseResponse resp = anamneseService.get( anamneseId );
		return ResponseEntity.ok( resp ); 
	}
	
	@PreAuthorize("hasAuthority('anamneseREAD')")
	@GetMapping("/load/reg")	
	public ResponseEntity<Object> loadRegTela(
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		AnamneseRegLoadResponse resp = anamneseService.loadRegTela( logadoUID );
		return ResponseEntity.ok( resp ); 
	}
	
	@PreAuthorize("hasAuthority('anamneseREAD')")
	@GetMapping("/load/edit/{anamneseId}")	
	public ResponseEntity<Object> loadEditTela(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long anamneseId ) throws SistemaException {
				
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		AnamneseEditLoadResponse resp = anamneseService.loadEditTela( logadoUID, anamneseId );
		return ResponseEntity.ok( resp ); 
	}
		
}
