package italo.xclin.service;

import org.springframework.stereotype.Service;

@Service
public class ExameItemService {

	/*
	@Autowired
	private ExameItemRepository exameItemRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ExameItemLoader exameLoader;
	
	public void registra( Long pacienteId, ExameItemSaveRequest request ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Paciente paciente = pacienteOp.get();
		
		ExameItem exame = exameLoader.novoBean( paciente );
		exameLoader.loadBean( exame, request );
		
		exameItemRepository.save( exame );
	}
	
	public List<ExameItemResponse> filtra( Long pacienteId, ExameItemFiltroRequest request ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );

		Paciente paciente = pacienteOp.get();
		
		String filtroNome = request.getFiltroNome();
		
		List<ExameItem> exames;
		if ( filtroNome.equals( "*" ) ) {
			exames = paciente.getExames();
		} else {
			exames = paciente.getExames()
					.stream()
					.filter( e -> e.getNome().matches( ".*"+filtroNome+".*" ) )
					.toList();
		}
		
		List<ExameItemResponse> lista = new ArrayList<>();
		for( ExameItem e : exames ) {
			ExameItemResponse eresp = exameLoader.novoResponse();
			exameLoader.loadResponse( eresp, e );
			
			lista.add( eresp );
		}
		return lista;
	}
	
	public ExameItemResponse get( Long exameId ) throws ServiceException {
		Optional<ExameItem> exameOp = exameItemRepository.findById( exameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		ExameItem exame = exameOp.get();
		
		ExameItemResponse resp = exameLoader.novoResponse();
		exameLoader.loadResponse( resp, exame );
		
		return resp;
	}
	
	public void deleta( Long exameId ) throws ServiceException {
		boolean existe = exameItemRepository.existsById( exameId );
		if ( !existe )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		exameItemRepository.deleteById( exameId ); 
	}
	*/
	
}
