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
import italo.xclin.model.request.save.AnamneseModeloPerguntaSaveRequest;
import italo.xclin.model.response.AnamneseModeloPerguntaResponse;
import italo.xclin.model.response.load.edit.AnamneseModeloPerguntaEditLoadResponse;
import italo.xclin.model.response.load.reg.AnamneseModeloPerguntaRegLoadResponse;
import italo.xclin.service.AnamneseModeloPerguntaService;
import italo.xclin.service.autorizador.Autorizador;
import italo.xclin.validator.AnamneseModeloPerguntaValidator;

@RestController
@RequestMapping("/api/anamnese/modelo/pergunta")
public class AnamneseModeloPerguntaController {

	@Autowired
	private AnamneseModeloPerguntaService anamneseModeloPerguntaService;
	
	@Autowired
	private AnamneseModeloPerguntaValidator anamneseModeloPerguntaValidator;
	
	@Autowired
	private Autorizador autorizador;
		
	@PreAuthorize("hasAuthority('anamneseModeloWRITE')")
	@PostMapping("/registra/{anamneseModeloId}")
	public ResponseEntity<Object> registra(
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long anamneseModeloId,
			@RequestBody AnamneseModeloPerguntaSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSeAnamneseModeloDeProfissionalLogado( authorizationHeader, anamneseModeloId );
	
		anamneseModeloPerguntaValidator.validaSave( request );
		anamneseModeloPerguntaService.registra( anamneseModeloId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('anamneseModeloWRITE')")
	@PutMapping("/altera/{anamneseModeloPerguntaId}")
	public ResponseEntity<Object> altera(
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long anamneseModeloPerguntaId,
			@RequestBody AnamneseModeloPerguntaSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSeAnamneseModeloPerguntaDeProfissionalLogado( authorizationHeader, anamneseModeloPerguntaId );
		
		anamneseModeloPerguntaValidator.validaSave( request );
		anamneseModeloPerguntaService.altera( anamneseModeloPerguntaId, request );
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('anamneseModeloREAD')")
	@GetMapping("/get/{anamneseModeloPerguntaId}")
	public ResponseEntity<Object> get(
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long anamneseModeloPerguntaId ) throws SistemaException {
		
		autorizador.autorizaSeAnamneseModeloPerguntaDeProfissionalLogado( authorizationHeader, anamneseModeloPerguntaId );
		
		AnamneseModeloPerguntaResponse resp = anamneseModeloPerguntaService.get( anamneseModeloPerguntaId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('anamneseModeloREAD')")
	@GetMapping("/load/edit/{anamneseModeloPerguntaId}")
	public ResponseEntity<Object> editLoad( 
			@RequestHeader( "Authorization" ) String authorizationHeader,
			@PathVariable Long anamneseModeloPerguntaId ) throws SistemaException {
		
		autorizador.autorizaSeAnamneseModeloPerguntaDeProfissionalLogado( authorizationHeader, anamneseModeloPerguntaId );
		
		AnamneseModeloPerguntaEditLoadResponse resp = anamneseModeloPerguntaService.novoEditLoad( anamneseModeloPerguntaId );
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('anamneseModeloREAD')")
	@GetMapping("/load/reg")
	public ResponseEntity<Object> regLoad() throws SistemaException {	
		AnamneseModeloPerguntaRegLoadResponse resp = anamneseModeloPerguntaService.novoRegLoad();
		return ResponseEntity.ok( resp );
	}
	
	@PreAuthorize("hasAuthority('anamneseModeloDELETE')")
	@GetMapping("/delete/{anamneseModeloPerguntaId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader( "Authorization" ) String authorizationHeader, 
			@PathVariable Long anamneseModeloPerguntaId ) throws SistemaException {
		
		autorizador.autorizaSeAnamneseModeloPerguntaDeProfissionalLogado( authorizationHeader, anamneseModeloPerguntaId );
		
		anamneseModeloPerguntaService.deleta( anamneseModeloPerguntaId );
		return ResponseEntity.ok().build();
	}
	
}
