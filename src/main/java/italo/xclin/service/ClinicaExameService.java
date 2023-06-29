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
import italo.xclin.model.ClinicaExame;
import italo.xclin.model.request.filtro.ClinicaExameFiltroRequest;
import italo.xclin.model.request.save.ClinicaExameSaveRequest;
import italo.xclin.model.response.ClinicaExameResponse;
import italo.xclin.model.response.load.edit.ClinicaExameEditLoadResponse;
import italo.xclin.model.response.load.reg.ClinicaExameRegLoadResponse;
import italo.xclin.model.response.load.tela.ClinicaExameTelaLoadResponse;
import italo.xclin.repository.ClinicaExameRepository;
import italo.xclin.repository.ClinicaRepository;

@Service
public class ClinicaExameService {
	
	@Autowired
	private ClinicaExameRepository clinicaExameRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;

	@Autowired
	private ClinicaExameLoader clinicaExameLoader;
	
	public void registra( Long clinicaId, ClinicaExameSaveRequest request ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica clinica = clinicaOp.get();
		
		ClinicaExame exame = clinicaExameLoader.novoBean( clinica );
		clinicaExameLoader.loadBean( exame, request ); 
		
		clinicaExameRepository.save( exame );
	}
	
	public void altera( Long clinicaExameId, ClinicaExameSaveRequest request ) throws ServiceException {
		Optional<ClinicaExame> exameOp = clinicaExameRepository.findById( clinicaExameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_EXAME_NAO_ENCONTRADO );
		
		ClinicaExame exame = exameOp.get();
		
		String nome = request.getNome();		
		if ( !nome.equalsIgnoreCase( exame.getNome() ) )
			if ( clinicaExameRepository.existePorClinicaPorNome( clinicaExameId, nome ) )
				throw new ServiceException( Erro.CLINICA_EXAME_JA_EXISTE );		
		
		clinicaExameLoader.loadBean( exame, request );
		clinicaExameRepository.save( exame );
	}
	
	public List<ClinicaExameResponse> filtra( Long clinicaId, ClinicaExameFiltroRequest request ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica clinica = clinicaOp.get();
		
		String filtroNome = request.getFiltroNome();
		
		List<ClinicaExame> exames;
		if ( filtroNome.equals( "*" ) ) {
			exames = clinica.getExames();
		} else {
			exames = clinica.getExames()
					.stream()
					.filter( e -> e.getNome().matches( ".*"+filtroNome+".*" ) ) 
					.toList();
		}
		
		List<ClinicaExameResponse> lista = new ArrayList<>();
		for( ClinicaExame e : exames ) {
			ClinicaExameResponse eresp = clinicaExameLoader.novoResponse();
			clinicaExameLoader.loadResponse( eresp, e );
			
			lista.add( eresp );
		}
		return lista;
	}
	
	public ClinicaExameResponse get( Long clinicaExameId ) throws ServiceException {
		Optional<ClinicaExame> exameOp = clinicaExameRepository.findById( clinicaExameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_EXAME_NAO_ENCONTRADO );
		
		ClinicaExame exame = exameOp.get();
		
		ClinicaExameResponse resp = clinicaExameLoader.novoResponse();
		clinicaExameLoader.loadResponse( resp, exame );
		
		return resp;
	}
	
	public ClinicaExameTelaLoadResponse telaLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();		
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		return clinicaExameLoader.novoTelaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ClinicaExameRegLoadResponse regLoad( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();	
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		return clinicaExameLoader.novoRegResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ClinicaExameEditLoadResponse editLoad( Long[] clinicasIDs, Long clinicaExameId ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();		
		for( Clinica clinica : clinicas ) {
			clinicasIDs2.add( clinica.getId() );
			clinicasNomes2.add( clinica.getNome() );
		}
		
		Optional<ClinicaExame> exameOp = clinicaExameRepository.findById( clinicaExameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_EXAME_NAO_ENCONTRADO );
		
		ClinicaExame exame = exameOp.get();
		Clinica clinica = exame.getClinica();
		
		ClinicaExameResponse eresp = clinicaExameLoader.novoResponse();
		clinicaExameLoader.loadResponse( eresp, exame );
		
		return clinicaExameLoader.novoEditResponse( eresp, clinicasIDs2, clinicasNomes2, clinica );
	}
		
	public void deleta( Long clinicaExameId ) throws ServiceException {
		boolean existe = clinicaExameRepository.existsById( clinicaExameId );
		if ( !existe )
			throw new ServiceException( Erro.CLINICA_EXAME_NAO_ENCONTRADO );
		
		clinicaExameRepository.deleteById( clinicaExameId ); 
	}
	
}
