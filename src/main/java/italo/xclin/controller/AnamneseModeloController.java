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
import italo.xclin.model.request.filtro.AnamneseModeloFiltroRequest;
import italo.xclin.model.request.save.AnamneseModeloSaveRequest;
import italo.xclin.model.response.AnamneseModeloResponse;
import italo.xclin.model.response.load.detalhes.AnamneseModeloDetalhesLoadResponse;
import italo.xclin.service.AnamneseModeloService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.AnamneseModeloValidator;

@RestController
@RequestMapping("/api/anamnese/modelo")
public class AnamneseModeloController {
	
	@Autowired
	private AnamneseModeloService anamneseModeloService;
	
	@Autowired
	private AnamneseModeloValidator anamneseModeloValidator;		
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('anamneseModeloWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( 
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody AnamneseModeloSaveRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		anamneseModeloValidator.validaSave( request );
		
		anamneseModeloService.registra( logadoUID, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('anamneseModeloWRITE')")
	@PutMapping("/altera/{anamneseModeloId}")
	public ResponseEntity<Object> altera(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long anamneseModeloId,
			@RequestBody AnamneseModeloSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSeAnamneseModeloDeProfissionalLogado( authorizationHeader, anamneseModeloId );
								
		anamneseModeloValidator.validaSave( request );
		anamneseModeloService.altera( anamneseModeloId, request);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('anamneseModeloREAD')")
	@GetMapping("/get/{anamneseModeloId}")
	public ResponseEntity<Object> get(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long anamneseModeloId ) throws SistemaException {
		
		autorizador.autorizaSeAnamneseModeloDeProfissionalLogado( authorizationHeader, anamneseModeloId );
		
		AnamneseModeloResponse resp = anamneseModeloService.get( anamneseModeloId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('anamneseModeloREAD')")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@RequestBody AnamneseModeloFiltroRequest request ) throws SistemaException {
		
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		anamneseModeloValidator.validaFiltro( request );
		List<AnamneseModeloResponse> lista = anamneseModeloService.filtra( logadoUID, request );
		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('anamneseModeloREAD')")
	@GetMapping("/load/detalhes/{anamneseModeloId}")
	public ResponseEntity<Object> detalhesLoad( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long anamneseModeloId ) throws SistemaException {
		
		autorizador.autorizaSeAnamneseModeloDeProfissionalLogado( authorizationHeader, anamneseModeloId );
		
		AnamneseModeloDetalhesLoadResponse resp = anamneseModeloService.novoDetalhesLoad( anamneseModeloId );
		return ResponseEntity.ok( resp );
	}
		
	@PreAuthorize("hasAuthority('anamneseModeloDELETE')")
	@DeleteMapping("/deleta/{anamneseModeloId}")
	public ResponseEntity<Object> deleta( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long anamneseModeloId ) throws SistemaException {
		
		autorizador.autorizaSeAnamneseModeloDeProfissionalLogado( authorizationHeader, anamneseModeloId );
		
		anamneseModeloService.deleta( anamneseModeloId );
		return ResponseEntity.ok().build();
	}

}
