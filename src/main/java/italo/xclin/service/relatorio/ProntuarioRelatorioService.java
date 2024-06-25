package italo.xclin.service.relatorio;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.model.AnamnesePergunta;
import italo.xclin.model.Clinica;
import italo.xclin.model.Paciente;
import italo.xclin.model.response.load.relatorio.ProntuarioLoadTelaResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.PacienteRepository;
import italo.xclin.service.relatorio.jrdatasource.AnamnesePerguntasJRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ProntuarioRelatorioService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	public byte[] geraRelatorio( Long pacienteId ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() ) 
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Paciente p = pacienteOp.get();
		String pacienteNome = p.getNome();
		
		List<AnamnesePergunta> perguntas;
		if ( p.isAnamneseCriada() ) {
			perguntas = p.getAnamnese().getPerguntas();
		} else {
			perguntas = new ArrayList<>();
		}
		
		try {						
			AnamnesePerguntasJRDataSource anamnesePerguntasJRDS = new AnamnesePerguntasJRDataSource( perguntas );
			
			InputStream anamneseSubreportJasperFile = getClass().getResourceAsStream( "/anamnese.jasper" );
			JasperReport anamenseSubreport = (JasperReport)JRLoader.loadObject( anamneseSubreportJasperFile );
			
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put( "pacienteNome", pacienteNome );
			paramsMap.put( "anamnesePerguntasSubreport", anamenseSubreport );
			paramsMap.put( "anamnesePerguntasJRDataSource", anamnesePerguntasJRDS );
						
			InputStream reportJasperFile = getClass().getResourceAsStream( "/prontuario.jasper" );
		
			JasperReport report = (JasperReport)JRLoader.loadObject( reportJasperFile );
			JasperPrint jrprint = JasperFillManager.fillReport( report, paramsMap, new JREmptyDataSource() );			
			return JasperExportManager.exportReportToPdf( jrprint );			
		} catch ( JRException e ) {
			e.printStackTrace();
			throw new ServiceException( Erro.FALHA_GER_PDF );
		}		
	}
		
	public ProntuarioLoadTelaResponse loadTelaProntuario( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		ProntuarioLoadTelaResponse resp = new ProntuarioLoadTelaResponse();
		resp.setClinicasIDs( clinicasIDs2 ); 
		resp.setClinicasNomes( clinicasNomes2 );
		return resp;
	}

}
