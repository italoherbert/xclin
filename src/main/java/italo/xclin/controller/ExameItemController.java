package italo.xclin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exame/item")
public class ExameItemController {

	/*
	@Autowired
	private ExameItemService exameVinculoService;
	
	@Autowired
	private ExameItemValidator exameVinculoValidator;
	
	@Autowired
	private Autorizador autorizador;
	
	@PreAuthorize("hasAuthority('exameWRITE')")
	@PostMapping("/registra/{pacienteId}")
	public ResponseEntity<Object> registra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long pacienteId, 
			@RequestBody ExameItemSaveRequest request ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		exameVinculoValidator.validaSave( request );
		exameVinculoService.registra( pacienteId, request );
		return ResponseEntity.ok().build();		
	}
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@PostMapping("/filtra/{pacienteId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long pacienteId, 
			@RequestBody ExameItemFiltroRequest request ) throws SistemaException {
		
		autorizador.autorizaSePacienteDeClinica( authorizationHeader, pacienteId );
		
		exameVinculoValidator.validaFiltro( request );
		List<ExameItemResponse> lista = exameVinculoService.filtra( pacienteId, request );
		return ResponseEntity.ok( lista );
	}	
	
	@PreAuthorize("hasAuthority('exameREAD')")
	@GetMapping("/get/{exameId}")
	public ResponseEntity<Object> filtra(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
		
		autorizador.autorizaSeExameDeClinica( authorizationHeader, exameId );
		
		ExameItemResponse resp = exameVinculoService.get( exameId );
		return ResponseEntity.ok( resp );
	}	
	
	@PreAuthorize("hasAuthority('exameDELETE')")
	@DeleteMapping("/deleta/{exameId}")
	public ResponseEntity<Object> deleta(
			@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Long exameId ) throws SistemaException {
		
		autorizador.autorizaSeExameDeClinica( authorizationHeader, exameId );
		
		exameVinculoService.deleta( exameId );
		return ResponseEntity.ok().build();
	}
	*/
	
}
