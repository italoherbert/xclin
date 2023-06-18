package italo.xclin.service.relatorio;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.exception.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.logica.Converter;
import italo.xclin.model.Anamnese;
import italo.xclin.model.Paciente;
import italo.xclin.repository.PacienteRepository;
import italo.xclin.service.relatorio.jrdatasource.AnamneseJRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class AnamneseRelatorioService {
	
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
		
		Anamnese a = p.getAnamnese();		

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
		
}
