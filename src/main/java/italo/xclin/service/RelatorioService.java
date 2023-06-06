package italo.xclin.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.exception.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.model.Anamnese;
import italo.xclin.model.Paciente;
import italo.xclin.relatorio.jrdatasource.AnamneseJRDataSource;
import italo.xclin.repository.PacienteRepository;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioService {
	
	@Autowired
	private PacienteRepository pacienteRepository;		
	
	public byte[] geraRelatorio( Long pacienteId ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId ); 
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Paciente p = pacienteOp.get();
		String pacienteNome = p.getNome();
		
		if ( !p.isAnamnesePreenchida() )
			throw new ServiceException( Erro.ANAMNESE_NAO_PREENCHIDA );		
		
		Anamnese a = p.getAnamnese();		

		AnamneseJRDataSource anamneseJRDS = new AnamneseJRDataSource( a, pacienteNome );
		
		InputStream logoIS = getClass().getResourceAsStream( "/xclin-logo.png" );
		
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put( "logo", logoIS );
		paramsMap.put( "anamneseJRDS", anamneseJRDS );
					
		InputStream reportJasperFile = getClass().getResourceAsStream( "/anamnese.jasper" );
		try {			
			JasperPrint jrprint = JasperFillManager.fillReport( reportJasperFile, paramsMap, anamneseJRDS );
			return JasperExportManager.exportReportToPdf( jrprint );			
		} catch ( JRException e ) {
			e.printStackTrace();
			throw new ServiceException( Erro.FALHA_GER_ANAMNESE_PDF );
		}
		
	}
	
}
