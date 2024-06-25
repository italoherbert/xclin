package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ExameLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Exame;
import italo.xclin.model.request.filtro.ExameFiltroRequest;
import italo.xclin.model.request.save.ExameSaveRequest;
import italo.xclin.model.response.ExameResponse;
import italo.xclin.model.response.load.edit.ExameEditLoadResponse;
import italo.xclin.model.response.load.reg.ExameRegLoadResponse;
import italo.xclin.model.response.load.tela.ExameTelaLoadResponse;
import italo.xclin.repository.ExameRepository;
import italo.xclin.repository.ClinicaRepository;

@Service
public class ExameService {
	
	@Autowired
	private ExameRepository exameRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;

	@Autowired
	private ExameLoader exameLoader;
	
	public void registra( Long clinicaId, ExameSaveRequest request ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica clinica = clinicaOp.get();
		
		String nome = request.getNome();
		
		boolean existe = exameRepository.existePorClinicaPorNome( clinicaId, nome );
		if ( existe )
			throw new ServiceException( Erro.EXAME_JA_EXISTE );
		
		Exame exame = exameLoader.novoBean( clinica );
		exameLoader.loadBean( exame, request ); 
		
		exameRepository.save( exame );
	}
	
	public void altera( Long clinicaExameId, ExameSaveRequest request ) throws ServiceException {
		Optional<Exame> exameOp = exameRepository.findById( clinicaExameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		Exame exame = exameOp.get();
		
		String nome = request.getNome();		
		if ( !nome.equalsIgnoreCase( exame.getNome() ) )
			if ( exameRepository.existePorClinicaPorNome( clinicaExameId, nome ) )
				throw new ServiceException( Erro.EXAME_JA_EXISTE );		
		
		exameLoader.loadBean( exame, request );
		exameRepository.save( exame );
	}
	
	public List<ExameResponse> filtra( Long clinicaId, ExameFiltroRequest request ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica clinica = clinicaOp.get();
		
		String filtroNome = request.getFiltroNome();
		
		List<Exame> exames;
		if ( filtroNome.equals( "*" ) ) {
			exames = clinica.getExames();
		} else {
			exames = clinica.getExames()
					.stream()
					.filter( e -> e.getNome().matches( ".*"+filtroNome+".*" ) ) 
					.toList();
		}
		
		List<ExameResponse> lista = new ArrayList<>();
		for( Exame e : exames ) {
			ExameResponse eresp = exameLoader.novoResponse();
			exameLoader.loadResponse( eresp, e );
			
			lista.add( eresp );
		}
		return lista;
	}
	
	public ExameResponse get( Long clinicaExameId ) throws ServiceException {
		Optional<Exame> exameOp = exameRepository.findById( clinicaExameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		Exame exame = exameOp.get();
		
		ExameResponse resp = exameLoader.novoResponse();
		exameLoader.loadResponse( resp, exame );
		
		return resp;
	}
	
	public ExameTelaLoadResponse telaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();		
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		return exameLoader.novoTelaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ExameRegLoadResponse regLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();	
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		return exameLoader.novoRegResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ExameEditLoadResponse editLoad( Long clinicaExameId ) throws ServiceException {				
		Optional<Exame> exameOp = exameRepository.findById( clinicaExameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		Exame exame = exameOp.get();
		Clinica clinica = exame.getClinica();
		
		ExameResponse eresp = exameLoader.novoResponse();
		exameLoader.loadResponse( eresp, exame );
		
		return exameLoader.novoEditResponse( eresp, clinica );
	}
		
	public void deleta( Long clinicaExameId ) throws ServiceException {
		boolean existe = exameRepository.existsById( clinicaExameId );
		if ( !existe )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		exameRepository.deleteById( clinicaExameId ); 
	}
	
}
