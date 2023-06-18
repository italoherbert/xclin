package italo.xclin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import italo.xclin.model.request.filtro.UsuarioFiltroRequest;
import italo.xclin.model.request.save.UsuarioSaveRequest;
import italo.xclin.model.request.save.UsuarioSenhaSaveRequest;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.edit.UsuarioEditLoadResponse;
import italo.xclin.model.response.load.reg.UsuarioRegLoadResponse;
import italo.xclin.service.UsuarioService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.UsuarioValidator;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
		
	@Autowired
	private UsuarioValidator usuarioValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('usuarioWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra(  
			@RequestHeader( "Authorization" ) String authHeader,
			@RequestBody UsuarioSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		usuarioValidator.validaRegistro( request );
		usuarioService.registra( logadoUID, request );
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Object> alteraSenha(
			@RequestHeader( "Authorization" ) String authHeader,
			@RequestBody UsuarioSenhaSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		usuarioValidator.validaAlteraSenha( request );
		usuarioService.alteraSenhaPorLogadoUID( logadoUID, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('usuarioWRITE')")
	@PutMapping("/altera/{id}")
	public ResponseEntity<Object> altera( @PathVariable Long id, @RequestBody UsuarioSaveRequest request ) throws SistemaException {
		usuarioValidator.validaSave( request );
		usuarioService.altera( id, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@PatchMapping("/altera/senha/logado")
	public ResponseEntity<Object> alteraSenhaPorLogadoUID(
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@RequestBody UsuarioSenhaSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		usuarioValidator.validaAlteraSenha( request );
		usuarioService.alteraSenhaPorLogadoUID( logadoUID, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@GetMapping("/busca/{id}")
	public ResponseEntity<Object> get( @PathVariable Long id ) throws SistemaException {
		UsuarioResponse resp = usuarioService.get( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( @RequestBody UsuarioFiltroRequest request ) throws SistemaException {
		usuarioValidator.validaFiltro( request );
		List<UsuarioResponse> lista = usuarioService.filtra( request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/lista/porclinica/{clinicaId}")
	public ResponseEntity<Object> listaPorClinica(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long clinicaId ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, clinicaId ); 
		
		ListaResponse lista = usuarioService.listaPorClinica( clinicaId );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@GetMapping("/get/reg")
	public ResponseEntity<Object> getRegLoad() throws SistemaException {
		UsuarioRegLoadResponse resp = usuarioService.getRegLoad();
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('usuarioREAD')")
	@GetMapping("/get/edit/{id}")
	public ResponseEntity<Object> getEditLoad( @PathVariable Long id ) throws SistemaException {
		UsuarioEditLoadResponse resp = usuarioService.getEditLoad( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('usuarioDELETE')")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta( @PathVariable Long id ) throws SistemaException {
		usuarioService.delete( id );
		return ResponseEntity.ok().build();
	}
	
}
