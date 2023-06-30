package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ClinicaExameLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Exame;
import italo.xclin.model.request.filtro.ClinicaExameFiltroRequest;
import italo.xclin.model.request.save.ClinicaExameSaveRequest;
import italo.xclin.model.response.ExameResponse;
import italo.xclin.model.response.load.edit.ExameEditLoadResponse;
import italo.xclin.model.response.load.reg.ExameRegLoadResponse;
import italo.xclin.model.response.load.tela.ExameTelaLoadResponse;
import italo.xclin.repository.ExameRepository;
import italo.xclin.repository.ClinicaRepository;

@Service
public class ExameService {
	
	@Autowired
	private ExameRepository clinicaExameRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;

	@Autowired
	private ClinicaExameLoader clinicaExameLoader;
	
	public void registra( Long clinicaId, ClinicaExameSaveRequest request ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica clinica = clinicaOp.get();
		
		Exame exame = clinicaExameLoader.novoBean( clinica );
		clinicaExameLoader.loadBean( exame, request ); 
		
		clinicaExameRepository.save( exame );
	}
	
	public void altera( Long clinicaExameId, ClinicaExameSaveRequest request ) throws ServiceException {
		Optional<Exame> exameOp = clinicaExameRepository.findById( clinicaExameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_EXAME_NAO_ENCONTRADO );
		
		Exame exame = exameOp.get();
		
		String nome = request.getNome();		
		if ( !nome.equalsIgnoreCase( exame.getNome() ) )
			if ( clinicaExameRepository.existePorClinicaPorNome( clinicaExameId, nome ) )
				throw new ServiceException( Erro.CLINICA_EXAME_JA_EXISTE );		
		
		clinicaExameLoader.loadBean( exame, request );
		clinicaExameRepository.save( exame );
	}
	
	public List<ExameResponse> filtra( Long clinicaId, ClinicaExameFiltroRequest request ) throws ServiceException {
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
			ExameResponse eresp = clinicaExameLoader.novoResponse();
			clinicaExameLoader.loadResponse( eresp, e );
			
			lista.add( eresp );
		}
		return lista;
	}
	
	public ExameResponse get( Long clinicaExameId ) throws ServiceException {
		Optional<Exame> exameOp = clinicaExameRepository.findById( clinicaExameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_EXAME_NAO_ENCONTRADO );
		
		Exame exame = exameOp.get();
		
		ExameResponse resp = clinicaExameLoader.novoResponse();
		clinicaExameLoader.loadResponse( resp, exame );
		
		return resp;
	}
	
	public ExameTelaLoadResponse telaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();		
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		return clinicaExameLoader.novoTelaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ExameRegLoadResponse regLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();	
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		return clinicaExameLoader.novoRegResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ExameEditLoadResponse editLoad( Long[] clinicasIDs, Long clinicaExameId ) throws ServiceException {				
		Optional<Exame> exameOp = clinicaExameRepository.findById( clinicaExameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_EXAME_NAO_ENCONTRADO );
		
		Exame exame = exameOp.get();
		Clinica clinica = exame.getClinica();
		
		ExameResponse eresp = clinicaExameLoader.novoResponse();
		clinicaExameLoader.loadResponse( eresp, exame );
		
		return clinicaExameLoader.novoEditResponse( eresp, clinica );
	}
		
	public void deleta( Long clinicaExameId ) throws ServiceException {
		boolean existe = clinicaExameRepository.existsById( clinicaExameId );
		if ( !existe )
			throw new ServiceException( Erro.CLINICA_EXAME_NAO_ENCONTRADO );
		
		clinicaExameRepository.deleteById( clinicaExameId ); 
	}
	
}
