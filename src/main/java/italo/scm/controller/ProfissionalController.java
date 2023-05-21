package italo.scm.controller;

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

import italo.scm.enums.UsuarioPerfilEnumManager;
import italo.scm.enums.tipos.UsuarioPerfil;
import italo.scm.exception.SistemaException;
import italo.scm.logica.JWTTokenInfo;
import italo.scm.logica.JWTTokenLogica;
import italo.scm.model.request.filtro.ProfissionalFiltroRequest;
import italo.scm.model.request.save.ProfissionalSaveRequest;
import italo.scm.model.response.ListaResponse;
import italo.scm.model.response.ProfissionalResponse;
import italo.scm.model.response.load.ProfissionalDetalhesLoadResponse;
import italo.scm.model.response.load.ProfissionalEditLoadResponse;
import italo.scm.model.response.load.ProfissionalRegLoadResponse;
import italo.scm.service.ProfissionalService;
import italo.scm.service.auth.Autorizador;
import italo.scm.service.shared.ProfissionalSharedService;
import italo.scm.validator.ProfissionalValidator;

@RestController
@RequestMapping("/api/profissional")
public class ProfissionalController {

	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private ProfissionalValidator profissionalValidator;
	
	@Autowired
	private ProfissionalSharedService profissionalSharedService;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;
	
	@Autowired
	private UsuarioPerfilEnumManager usuarioPerfilEnumManager;
	
	@PreAuthorize("hasAuthority('profissionalWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( 
			@RequestHeader( "Authorization" ) String authHeader,
			@RequestBody ProfissionalSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		profissionalValidator.validaSave( request );
		profissionalService.registra( logadoUID, request );
		return ResponseEntity.ok().build(); 
	}
	
	@PreAuthorize("hasAuthority('profissionalWRITE')")
	@PutMapping("/altera/{id}")
	public ResponseEntity<Object> altera( 
			@PathVariable Long id, 
			@RequestBody ProfissionalSaveRequest request ) throws SistemaException {
		
		profissionalValidator.validaSave( request );
		profissionalService.altera( id, request );
		return ResponseEntity.ok().build(); 	
	}
	
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
		
	@PreAuthorize("hasAuthority('profissionalREAD')")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( 
			@RequestBody ProfissionalFiltroRequest request ) throws SistemaException {
		
		profissionalValidator.validaFiltro( request );
		List<ProfissionalResponse> lista = profissionalService.filtra( request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/lista/porclinica/{clinicaId}")
	public ResponseEntity<Object> listaPorClinica(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long clinicaId ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId );
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		UsuarioPerfil perfil = usuarioPerfilEnumManager.getEnum( tokenInfo.getPerfil() );
		
		ListaResponse resp;
		if ( perfil == UsuarioPerfil.PROFISSIONAL ) {
			resp = profissionalSharedService.listaPorClinica( clinicaId, logadoUID );
		} else {
			resp = profissionalSharedService.listaPorClinica( clinicaId );
		}
		
		return ResponseEntity.ok( resp );		
	}
	
	@PreAuthorize("hasAuthority('profissionalREAD')")
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> get( @PathVariable Long id ) throws SistemaException {
		ProfissionalResponse resp = profissionalService.get( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('profissionalREAD')")
	@GetMapping("/get/reg")
	public ResponseEntity<Object> getRegLoad() throws SistemaException {
		ProfissionalRegLoadResponse resp = profissionalService.getRegLoad();
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('profissionalREAD')")
	@GetMapping("/get/edit/{id}")
	public ResponseEntity<Object> getEditLoad( @PathVariable Long id ) throws SistemaException {
		ProfissionalEditLoadResponse resp = profissionalService.getEditLoad( id );
		return ResponseEntity.ok( resp );
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
	
	@PreAuthorize("hasAuthority('profissionalREAD')")
	@GetMapping("/get/detalhes/{id}")
	public ResponseEntity<Object> getDetalhesLoad( @PathVariable Long id ) throws SistemaException {
		ProfissionalDetalhesLoadResponse resp = profissionalService.getDetalhesLoad( id );
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
		
	@PreAuthorize("hasAuthority('profissionalDELETE')")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta( 
			@PathVariable Long id ) throws SistemaException {
		
		profissionalService.deleta( id );
		return ResponseEntity.ok().build();
	}
	
}

