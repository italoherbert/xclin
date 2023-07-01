package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ProfissionalExameVinculoLoader;
import italo.xclin.model.Exame;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalExameVinculo;
import italo.xclin.model.request.save.ProfissionalExameSaveRequest;
import italo.xclin.model.response.ProfissionalExameVinculoResponse;
import italo.xclin.model.response.load.edit.ProfissionalExameSaveLoadResponse;
import italo.xclin.repository.ExameRepository;
import italo.xclin.repository.ProfissionalExameVinculoRepository;
import italo.xclin.repository.ProfissionalRepository;

@Service
public class ProfissionalExameVinculoService {

	@Autowired
	private ProfissionalExameVinculoRepository profissionalExameVinculoRepository;
	
	@Autowired
	private ExameRepository exameRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private ProfissionalExameVinculoLoader profissionalExameVinculoLoader;
	
	public ProfissionalExameVinculoResponse getVinculo( Long logadoUID, Long exameId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
			
		Optional<ProfissionalExameVinculo> vinculoOp = 
				profissionalExameVinculoRepository.busca( profissionalId, exameId );
		
		if ( !vinculoOp.isPresent() )
			throw new ServiceException( Erro.VINCULO_PROFISSIONAL_EXAME_NAO_ENCONTRADO );
		
		ProfissionalExameVinculo vinculo = vinculoOp.get();
		Exame esp = vinculo.getExame();		
		
		ProfissionalExameVinculoResponse resp = profissionalExameVinculoLoader.novoResponse( esp );
		profissionalExameVinculoLoader.loadResponse( resp, vinculo );
		
		return resp;
	}
	
	public List<ProfissionalExameVinculoResponse> lista( Long logadoUID ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		
		List<ProfissionalExameVinculoResponse> lista = new ArrayList<>();
		
		List<ProfissionalExameVinculo> vinculos = profissional.getProfissionalExameVinculos();
		for( ProfissionalExameVinculo vinculo : vinculos ) {
			Exame exame = vinculo.getExame();
			ProfissionalExameVinculoResponse resp = profissionalExameVinculoLoader.novoResponse( exame );
			profissionalExameVinculoLoader.loadResponse( resp, vinculo );
			
			lista.add( resp );
		}
		
		return lista;
	}
	
	public ProfissionalExameSaveLoadResponse loadSave( Long logadoUID ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();

		List<Exame> exames = exameRepository.findAll();
		List<ProfissionalExameVinculo> vinculos = profissional.getProfissionalExameVinculos();
		
		int vinculosSize = vinculos.size();
		
		List<Long> examesIDs = new ArrayList<>();
		List<String> examesNomes = new ArrayList<>();
		List<Boolean> examesVinculados = new ArrayList<>();

		for( Exame exame : exames ) {
			boolean jaAdicionado = false;
			for( int i = 0; !jaAdicionado && i < vinculosSize; i++ ) {
				ProfissionalExameVinculo vinculo = vinculos.get( i );
				Exame exame2 = vinculo.getExame();
				if ( exame.getId() == exame2.getId() )
					jaAdicionado = true;
			}
			
			examesIDs.add( exame.getId() );
			examesNomes.add( exame.getNome() );
			examesVinculados.add( jaAdicionado );
		}
			
		return profissionalExameVinculoLoader.novoSaveLoadResponse(
				examesIDs, examesNomes, examesVinculados );
	}
	
	public void vincula( Long logadoUID, Long exameId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Optional<Exame> exameOp = exameRepository.findById( exameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Exame exame = exameOp.get();
		
		ProfissionalExameVinculo vinculo = new ProfissionalExameVinculo();
		vinculo.setProfissional( profissional );
		vinculo.setExame( exame ); 
		vinculo.setExameValor( 0 );
		
		profissionalExameVinculoRepository.save( vinculo );
	}
	
	public void salva( 
			Long logadoUID, 
			Long exameId, 
			ProfissionalExameSaveRequest request ) throws ServiceException {
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
		
		Optional<ProfissionalExameVinculo> vinculoOp = 
				profissionalExameVinculoRepository.busca( profissionalId, exameId );
		
		if ( !vinculoOp.isPresent() )
			throw new ServiceException( Erro.VINCULO_PROFISSIONAL_EXAME_NAO_ENCONTRADO );
		
		ProfissionalExameVinculo vinculo = vinculoOp.get();
		profissionalExameVinculoLoader.loadBean( vinculo, request ); 
		
		profissionalExameVinculoRepository.save( vinculo );
	}
	
	public void deleta( Long logadoUID, Long exameId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
		
		Optional<ProfissionalExameVinculo> vinculoOp = 
				profissionalExameVinculoRepository.busca( profissionalId, exameId );
		
		ProfissionalExameVinculo vinculo = vinculoOp.get();
		Long vinculoId = vinculo.getId();
		
		profissionalExameVinculoRepository.deleteById( vinculoId );
	}
}
