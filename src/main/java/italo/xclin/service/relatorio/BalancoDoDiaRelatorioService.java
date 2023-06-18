package italo.xclin.service.relatorio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.exception.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.logica.Converter;
import italo.xclin.model.Clinica;
import italo.xclin.model.Lancamento;
import italo.xclin.model.request.relatorio.BalancoDoDiaRelatorioRequest;
import italo.xclin.model.response.load.relatorio.BalancoDoDiaLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.LancamentoRepository;
import italo.xclin.service.relatorio.jrdatasource.LancamentosDoDiaJRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class BalancoDoDiaRelatorioService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private Converter converter;
	
	public byte[] geraRelatorio( Long clinicaId, BalancoDoDiaRelatorioRequest request ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica clinica = clinicaOp.get();
		
		Date dataDia = converter.stringToDataNEx( request.getDataDia() ); 
		
		List<Lancamento> lancamentos;
		if ( request.isIncluirTodosOsUsuarios() ) {
			lancamentos = lancamentoRepository.clinicaLancamentosDoDia( clinicaId, dataDia );
		} else {
			Long usuarioId = request.getUsuarioId();
			lancamentos = lancamentoRepository.clinicaUsuarioLancamentosDoDia( clinicaId, usuarioId, dataDia );
		}
				
		LancamentosDoDiaJRDataSource lancamentosJRDS = new LancamentosDoDiaJRDataSource( lancamentos );
				
		InputStream logoIS = getClass().getResourceAsStream( "/xclin-logo.png" );
		
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put( "logo", logoIS );
		paramsMap.put( "clinica", clinica.getNome() );
		paramsMap.put( "dataDia", converter.formataDataBR( dataDia ) );
					
		InputStream reportJasperFile = getClass().getResourceAsStream( "/balanco-do-dia.jasper" );
		
		try {						
			JasperReport report = (JasperReport)JRLoader.loadObject( reportJasperFile );
			JasperPrint jrprint = JasperFillManager.fillReport( report, paramsMap, lancamentosJRDS );			
			return JasperExportManager.exportReportToPdf( jrprint );			
		} catch ( JRException e ) {
			e.printStackTrace();
			throw new ServiceException( Erro.FALHA_GER_PDF );
		}		
	}
	
	public BalancoDoDiaLoadResponse loadTelaBalancoDoDia( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		BalancoDoDiaLoadResponse resp = new BalancoDoDiaLoadResponse();
		resp.setClinicasIDs( clinicasIDs2 ); 
		resp.setClinicasNomes( clinicasNomes2 );
		return resp;
	}
	
}
