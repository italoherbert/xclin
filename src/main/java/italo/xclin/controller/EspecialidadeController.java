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

import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.filtro.EspecialidadeFiltroRequest;
import italo.xclin.model.request.save.EspecialidadeSaveRequest;
import italo.xclin.model.response.EspecialidadeResponse;
import italo.xclin.model.response.load.edit.EspecialidadeEditLoadResponse;
import italo.xclin.model.response.load.reg.EspecialidadeRegLoadResponse;
import italo.xclin.model.response.load.tela.EspecialidadeTelaLoadResponse;
import italo.xclin.service.EspecialidadeService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.EspecialidadeValidator;

@RestController
@RequestMapping("/api/especialidade")
public class EspecialidadeController {
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Autowired
	private EspecialidadeValidator especialidadeValidator;
	
	@Autowired
	private Autorizador autorizador;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@PreAuthorize("hasAuthority('especialidadeWRITE')")
	@PostMapping("/registra/{clinicaId}")
	public ResponseEntity<Object> registra(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody EspecialidadeSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		especialidadeValidator.validaSave( request );
		especialidadeService.registra( clinicaId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('especialidadeWRITE')")
	@PutMapping("/altera/{especialidadeId}")
	public ResponseEntity<Object> altera( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId, 
			@RequestBody EspecialidadeSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSeEspecialidadeDeClinica( authorizationHeader, especialidadeId );
		
		especialidadeValidator.validaSave( request );
		especialidadeService.altera( especialidadeId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('especialidadeREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId,
			@RequestBody EspecialidadeFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		especialidadeValidator.validaFiltro( request );
		List<EspecialidadeResponse> lista = especialidadeService.filtra( clinicaId, request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('especialidadeREAD')")
	@GetMapping("/get/{especialidadeId}")
	public ResponseEntity<Object> get( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId ) throws SistemaException {
		
		autorizador.autorizaSeEspecialidadeDeClinica( authorizationHeader, especialidadeId );
		
		EspecialidadeResponse resp = especialidadeService.get( especialidadeId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('especialidadeREAD')")
	@GetMapping("/load/tela")
	public ResponseEntity<Object> loadTela( 
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		EspecialidadeTelaLoadResponse resp = especialidadeService.loadTela( clinicasIDs );
		return ResponseEntity.ok( resp );		
	}
	
	@PreAuthorize("hasAuthority('especialidadeREAD')")
	@GetMapping("/load/reg")
	public ResponseEntity<Object> loadRegTela( 
			@RequestHeader( "Authorization" ) String authorizationHeader ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long[] clinicasIDs = tokenInfo.getClinicasIDs();
		
		EspecialidadeRegLoadResponse resp = especialidadeService.loadRegTela( clinicasIDs );
		return ResponseEntity.ok( resp );		
	}
	
	@PreAuthorize("hasAuthority('especialidadeREAD')")
	@GetMapping("/load/edit/{especialidadeId}")
	public ResponseEntity<Object> loadEditTela( 
			@PathVariable Long especialidadeId ) throws SistemaException {
		
		EspecialidadeEditLoadResponse resp = especialidadeService.loadEditTela( especialidadeId );
		return ResponseEntity.ok( resp );		
	}
		
	@PreAuthorize("hasAuthority('especialidadeDELETE')")
	@DeleteMapping("/deleta/{especialidadeId}")
	public ResponseEntity<Object> deleta( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long especialidadeId ) throws SistemaException {

		autorizador.autorizaSeEspecialidadeDeClinica( authorizationHeader, especialidadeId );
		
		especialidadeService.deleta( especialidadeId );
		return ResponseEntity.ok().build();
	}

}

