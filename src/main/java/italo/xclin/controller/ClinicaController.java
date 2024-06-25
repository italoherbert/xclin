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

import italo.xclin.enums.tipos.UsuarioPerfil;
import italo.xclin.exception.SistemaException;
import italo.xclin.logica.JWTTokenInfo;
import italo.xclin.logica.JWTTokenLogica;
import italo.xclin.model.request.filtro.ClinicaFiltroRequest;
import italo.xclin.model.request.save.ClinicaSaveRequest;
import italo.xclin.model.response.ClinicaResponse;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.load.detalhes.ClinicaDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.ClinicaEditLoadResponse;
import italo.xclin.model.response.load.reg.ClinicaRegLoadResponse;
import italo.xclin.service.ClinicaService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.ClinicaValidator;

@RestController
@RequestMapping("/api/clinica")
public class ClinicaController {

	@Autowired
	private ClinicaService clinicaService;
	
	@Autowired
	private ClinicaValidator clinicaValidator;
	
	@Autowired
	private JWTTokenLogica jwtTokenLogica;

	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('clinicaRegWRITE')")
	@PostMapping("/registra")
	public ResponseEntity<Object> registra( 
			@RequestHeader( "Authorization" ) String authHeader,
			@RequestBody ClinicaSaveRequest request ) throws SistemaException {
		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authHeader );
		Long logadoUID = tokenInfo.getUsuarioId();
		
		clinicaValidator.validaSave( request );
		clinicaService.registra( logadoUID, request );
		return ResponseEntity.ok().build(); 
	}
	
	@PreAuthorize("hasAuthority('clinicaWRITE')")
	@PutMapping("/altera/{id}")
	public ResponseEntity<Object> altera( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long id, 
			@RequestBody ClinicaSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, id ); 

		clinicaValidator.validaSave( request );
		clinicaService.altera( id, request );
		return ResponseEntity.ok().build(); 	
	}
	
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@PostMapping("/filtra")
	public ResponseEntity<Object> filtra( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@RequestBody ClinicaFiltroRequest request ) throws SistemaException {
		
		clinicaValidator.validaFiltro( request );

		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );

		List<ClinicaResponse> lista;
		if ( tokenInfo.getPerfil().equalsIgnoreCase( UsuarioPerfil.RAIZ.name() ) ) {
			lista = clinicaService.filtra( request );
		} else {
			Long[] clinicasIDs = tokenInfo.getClinicasIDs();
			lista = clinicaService.filtraPorIDs( clinicasIDs, request );			
		}		

		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/filtra/pornome/{nomeIni}")
	public ResponseEntity<Object> filtra( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable String nomeIni ) throws SistemaException {		

		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );

		List<ClinicaResponse> lista;
		if ( tokenInfo.getPerfil().equalsIgnoreCase( UsuarioPerfil.RAIZ.name() ) ) {
			lista = clinicaService.filtraPorNome( nomeIni );
		} else {
			Long[] clinicasIDs = tokenInfo.getClinicasIDs();
			lista = clinicaService.filtraPorIDsPorNome( clinicasIDs, nomeIni );
		}

		return ResponseEntity.ok( lista );
	}
	
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/lista/limite/{nomeIni}/{quant}")
	public ResponseEntity<Object> listaPorNomeEClinica(
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable String nomeIni,
			@PathVariable int quant ) throws SistemaException {

		JWTTokenInfo tokenInfo = jwtTokenLogica.authorizationHeaderTokenInfo( authorizationHeader );

		ListaResponse resp;
		if ( tokenInfo.getPerfil().equalsIgnoreCase( UsuarioPerfil.RAIZ.name() ) ) {
			resp = clinicaService.listaPorNome( nomeIni, quant );
		} else {
			Long[] clinicasIDs = tokenInfo.getClinicasIDs();
			resp = clinicaService.listaPorIDsPorNome( clinicasIDs, nomeIni, quant );
		}
						
		return ResponseEntity.ok( resp );
	}
		
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> get( 
		@RequestHeader( "Authorization" ) String authorizationHeader,
		@PathVariable Long id ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, id ); 

		ClinicaResponse resp = clinicaService.get( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/get/edit/{id}")
	public ResponseEntity<Object> getEditLoad( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long id ) throws SistemaException {

		autorizador.autorizaPorClinica( authorizationHeader, id ); 
		
		ClinicaEditLoadResponse resp = clinicaService.getEditLoad( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/get/detalhes/{id}")
	public ResponseEntity<Object> getDetalhesLoad( 
			@RequestHeader( "Authorization" ) String authorizationHeader,	
			@PathVariable Long id ) throws SistemaException {

		autorizador.autorizaPorClinica( authorizationHeader, id );
		
		ClinicaDetalhesLoadResponse resp = clinicaService.getDetalhesLoad( id );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('clinicaREAD')")
	@GetMapping("/get/reg")
	public ResponseEntity<Object> getRegLoad() throws SistemaException {
		ClinicaRegLoadResponse resp = clinicaService.getRegLoad();
		return ResponseEntity.ok( resp );
	}
		
	@PreAuthorize("hasAuthority('clinicaDELETE')")
	@DeleteMapping("/deleta/{id}")
	public ResponseEntity<Object> deleta( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long id ) throws SistemaException {
		
		autorizador.autorizaPorClinica( authorizationHeader, id );
		
		clinicaService.deleta( id );
		return ResponseEntity.ok().build();
	}
		
}
