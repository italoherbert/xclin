package italo.xclin.service.relatorio;

import org.springframework.stereotype.Service;

@Service
public class AnamneseRelatorioService {
/*	
	@Autowired
	private PacienteRepository pacienteRepository;		
	
	@Autowired
	private Converter converter;
	
	public byte[] geraRelatorio( Long pacienteId ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId ); 
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Paciente p = pacienteOp.get();
		String pacienteNome = p.getNome();
				
		if ( !p.isAnamnesePreenchida() )
			throw new ServiceException( Erro.ANAMNESE_NAO_PREENCHIDA );						
		
		Anamnese2 a = p.getAnamnese();		

		AnamneseJRDataSource anamneseJRDS = new AnamneseJRDataSource( a, pacienteNome, converter );
		
		InputStream logoIS = getClass().getResourceAsStream( "/xclin-logo.png" );
		
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put( "logo", logoIS );
					
		InputStream reportJasperFile = getClass().getResourceAsStream( "/anamnese.jasper" );
		try {			
			JasperReport report = (JasperReport)JRLoader.loadObject( reportJasperFile );
			JasperPrint jrprint = JasperFillManager.fillReport( report, paramsMap, anamneseJRDS );
			return JasperExportManager.exportReportToPdf( jrprint );			
		} catch ( JRException e ) {
			e.printStackTrace();
			throw new ServiceException( Erro.FALHA_GER_PDF );
		}		
	}
*/	
}
