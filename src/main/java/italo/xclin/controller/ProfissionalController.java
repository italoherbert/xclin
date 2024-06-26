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

import italo.xclin.enums.UsuarioPerfilEnumManager;
import italo.xclin.enums.tipos.UsuarioPerfil;
import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.filtro.ProfissionalFiltroRequest;
import italo.xclin.model.request.save.ProfissionalSaveRequest;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.ProfissionalEspecialidadeVinculoResponse;
import italo.xclin.model.response.ProfissionalResponse;
import italo.xclin.model.response.load.detalhes.ProfissionalDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.ProfissionalEditLoadResponse;
import italo.xclin.model.response.load.reg.ProfissionalRegLoadResponse;
import italo.xclin.service.ProfissionalEspecialidadeVinculoService;
import italo.xclin.service.ProfissionalService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.service.shared.ProfissionalSharedService;
import italo.xclin.validator.ProfissionalValidator;

@RestController
@RequestMapping("/api/profissional")
public class ProfissionalController {

	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private ProfissionalEspecialidadeVinculoService profissionalEspecialidadeVinculoService;
	
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
	
	@PreAuthorize("hasAuthority('profissionalTodosREAD')")
	@PostMapping("/filtra-todos")
	public ResponseEntity<Object> filtraTodos( 
			@RequestBody ProfissionalFiltroRequest request ) throws SistemaException {
		
		profissionalValidator.validaFiltro( request );
		List<ProfissionalResponse> lista = profissionalService.filtraTodos( request );
		return ResponseEntity.ok( lista );
	}

	@PreAuthorize("hasAuthority('profissionalREAD')")
	@PostMapping("/filtra/{clinicaId}")
	public ResponseEntity<Object> filtra( 
			@PathVariable Long clinicaId,
			@RequestBody ProfissionalFiltroRequest request ) throws SistemaException {
		
		profissionalValidator.validaFiltro( request );
		List<ProfissionalResponse> lista = profissionalService.filtra( clinicaId, request );
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
	@GetMapping("/get/vinculo/{profissionalId}/{especialidadeId}")
	public ResponseEntity<Object> getVinculo( 
			@PathVariable Long profissionalId,
			@PathVariable Long especialidadeId ) throws SistemaException {
				
		ProfissionalEspecialidadeVinculoResponse resp =
				profissionalEspecialidadeVinculoService.get( profissionalId, especialidadeId );
		
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
		
	@PreAuthorize("hasAuthority('profissionalREAD')")
	@GetMapping("/get/detalhes/{id}")
	public ResponseEntity<Object> getDetalhesLoad( @PathVariable Long id ) throws SistemaException {
		ProfissionalDetalhesLoadResponse resp = profissionalService.getDetalhesLoad( id );
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

