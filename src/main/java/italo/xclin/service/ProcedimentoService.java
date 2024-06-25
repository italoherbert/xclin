package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ProcedimentoLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Procedimento;
import italo.xclin.model.request.filtro.ProcedimentoFiltroRequest;
import italo.xclin.model.request.save.ProcedimentoSaveRequest;
import italo.xclin.model.response.ProcedimentoResponse;
import italo.xclin.model.response.load.edit.ProcedimentoEditLoadResponse;
import italo.xclin.model.response.load.reg.ProcedimentoRegLoadResponse;
import italo.xclin.model.response.load.tela.ProcedimentoTelaLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.ProcedimentoRepository;

@Service
public class ProcedimentoService {

	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private ProcedimentoLoader procedimentoLoader;
	
	public void registra( Long clinicaId, ProcedimentoSaveRequest request ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		String nome = request.getNome();
		
		boolean existe = procedimentoRepository.existePorClinicaPorNome( clinicaId, nome );
		if ( existe )
			throw new ServiceException( Erro.PROCEDIMENTO_JA_EXISTE );
		
		Clinica clinica = clinicaOp.get();
		
		Procedimento procedimento = procedimentoLoader.novoBean( clinica );
		procedimentoLoader.loadBean( procedimento, request );
		
		procedimentoRepository.save( procedimento );
	}
	
	public void altera( Long procedimentoId, ProcedimentoSaveRequest request ) throws ServiceException {
		Optional<Procedimento> procedimentoOp = procedimentoRepository.findById( procedimentoId );
		if ( !procedimentoOp.isPresent() )
			throw new ServiceException( Erro.PROCEDIMENTO_NAO_ENCONTRADO );
		
		Procedimento procedimento = procedimentoOp.get();
		
		String nome = request.getNome();
		
		if ( !nome.equalsIgnoreCase( procedimento.getNome() ) ) {
			boolean existe = procedimentoRepository.existePorClinicaPorNome( procedimentoId , nome );
			if ( existe )
				throw new ServiceException( Erro.PROCEDIMENTO_JA_EXISTE );
		}
			
		procedimentoLoader.loadBean( procedimento, request );
		
		procedimentoRepository.save( procedimento );				
	}
	
	public List<ProcedimentoResponse> filtra( Long clinicaId, ProcedimentoFiltroRequest request ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica clinica = clinicaOp.get();
		
		List<Procedimento> procedimentos;
	
		String filtroNome = request.getFiltroNome();
		if ( filtroNome.equals( "*" ) ) {
			procedimentos = clinica.getProcedimentos();
		} else {
			procedimentos = clinica.getProcedimentos()
					.stream()
					.filter( (p) -> p.getNome().matches( ".*"+filtroNome+".*" ) )
					.toList();			
		}
		
		List<ProcedimentoResponse> lista = new ArrayList<>();
		for( Procedimento p : procedimentos ) {
			ProcedimentoResponse resp = procedimentoLoader.novoResponse();
			procedimentoLoader.loadResponse( resp, p );
			
			lista.add( resp );
		} 
		
		return lista;
	}
	
	public ProcedimentoResponse get( Long procedimentoId ) throws ServiceException {
		Optional<Procedimento> procedimentoOp = procedimentoRepository.findById( procedimentoId );
		if ( !procedimentoOp.isPresent() )
			throw new ServiceException( Erro.PROCEDIMENTO_NAO_ENCONTRADO );
		
		Procedimento procedimento = procedimentoOp.get();
		
		ProcedimentoResponse resp = procedimentoLoader.novoResponse();
		procedimentoLoader.loadResponse( resp, procedimento );
		
		return resp;
	}
	
	public ProcedimentoTelaLoadResponse telaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();		
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		return procedimentoLoader.novoTelaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ProcedimentoRegLoadResponse regLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();		
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		return procedimentoLoader.novoRegResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ProcedimentoEditLoadResponse editLoad( Long[] clinicasIDs, Long procedimentoId ) throws ServiceException {
		Optional<Procedimento> procedimentoOp = procedimentoRepository.findById( procedimentoId );
		if ( !procedimentoOp.isPresent() )
			throw new ServiceException( Erro.PROCEDIMENTO_NAO_ENCONTRADO );
		
		Procedimento procedimento = procedimentoOp.get();
		
		ProcedimentoResponse presp = procedimentoLoader.novoResponse();
		procedimentoLoader.loadResponse( presp, procedimento );
				
		Clinica clinica = procedimento.getClinica();
		
		return procedimentoLoader.novoEditResponse( presp, clinica );
	}
	
	public void deleta( Long procedimentoId ) throws ServiceException {
		boolean existe = procedimentoRepository.existsById( procedimentoId );
		if ( !existe )
			throw new ServiceException( Erro.PROCEDIMENTO_NAO_ENCONTRADO );
		
		procedimentoRepository.deleteById( procedimentoId );
	}
	
}
