package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ProfissionalProcedimentoVinculoLoader;
import italo.xclin.model.Procedimento;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalProcedimentoVinculo;
import italo.xclin.model.request.save.ProfissionalProcedimentoSaveRequest;
import italo.xclin.model.response.ProfissionalProcedimentoVinculoResponse;
import italo.xclin.model.response.load.edit.ProfissionalProcedimentoSaveLoadResponse;
import italo.xclin.repository.ProcedimentoRepository;
import italo.xclin.repository.ProfissionalProcedimentoVinculoRepository;
import italo.xclin.repository.ProfissionalRepository;

@Service
public class ProfissionalProcedimentoVinculoService {

	@Autowired
	private ProfissionalProcedimentoVinculoRepository profissionalProcedimentoVinculoRepository;
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private ProfissionalProcedimentoVinculoLoader profissionalProcedimentoVinculoLoader;
	
	public ProfissionalProcedimentoVinculoResponse getVinculo( Long logadoUID, Long procedimentoId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
			
		Optional<ProfissionalProcedimentoVinculo> vinculoOp = 
				profissionalProcedimentoVinculoRepository.busca( profissionalId, procedimentoId );
		
		if ( !vinculoOp.isPresent() )
			throw new ServiceException( Erro.VINCULO_PROFISSIONAL_PROCEDIMENTO_NAO_ENCONTRADO );
		
		ProfissionalProcedimentoVinculo vinculo = vinculoOp.get();
		Procedimento esp = vinculo.getProcedimento();		
		
		ProfissionalProcedimentoVinculoResponse resp = profissionalProcedimentoVinculoLoader.novoResponse( esp );
		profissionalProcedimentoVinculoLoader.loadResponse( resp, vinculo );
		
		return resp;
	}
	
	public List<ProfissionalProcedimentoVinculoResponse> lista( Long logadoUID ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		
		List<ProfissionalProcedimentoVinculoResponse> lista = new ArrayList<>();
		
		List<ProfissionalProcedimentoVinculo> vinculos = profissional.getProfissionalProcedimentoVinculos();
		for( ProfissionalProcedimentoVinculo vinculo : vinculos ) {
			Procedimento procedimento = vinculo.getProcedimento();
			ProfissionalProcedimentoVinculoResponse resp = profissionalProcedimentoVinculoLoader.novoResponse( procedimento );
			profissionalProcedimentoVinculoLoader.loadResponse( resp, vinculo );
			
			lista.add( resp );
		}
		
		return lista;
	}
	
	public ProfissionalProcedimentoSaveLoadResponse loadSave( Long logadoUID ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();

		List<Procedimento> procedimentos = procedimentoRepository.findAll();
		List<ProfissionalProcedimentoVinculo> vinculos = profissional.getProfissionalProcedimentoVinculos();
		
		int vinculosSize = vinculos.size();
		
		List<Long> procedimentosIDs = new ArrayList<>();
		List<String> procedimentosNomes = new ArrayList<>();
		List<Boolean> procedimentosVinculados = new ArrayList<>();

		for( Procedimento procedimento : procedimentos ) {
			boolean jaAdicionado = false;
			for( int i = 0; !jaAdicionado && i < vinculosSize; i++ ) {
				ProfissionalProcedimentoVinculo vinculo = vinculos.get( i );
				Procedimento procedimento2 = vinculo.getProcedimento();
				if ( procedimento.getId() == procedimento2.getId() )
					jaAdicionado = true;
			}
			
			procedimentosIDs.add( procedimento.getId() );
			procedimentosNomes.add( procedimento.getNome() );
			procedimentosVinculados.add( jaAdicionado );
		}
			
		return profissionalProcedimentoVinculoLoader.novoSaveLoadResponse(
				procedimentosIDs, procedimentosNomes, procedimentosVinculados );
	}
	
	public void vincula( Long logadoUID, Long procedimentoId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Optional<Procedimento> procedimentoOp = procedimentoRepository.findById( procedimentoId );
		if ( !procedimentoOp.isPresent() )
			throw new ServiceException( Erro.PROCEDIMENTO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Procedimento procedimento = procedimentoOp.get();
		
		ProfissionalProcedimentoVinculo vinculo = new ProfissionalProcedimentoVinculo();
		vinculo.setProfissional( profissional );
		vinculo.setProcedimento( procedimento ); 
		vinculo.setProcedimentoValor( 0 );
		
		profissionalProcedimentoVinculoRepository.save( vinculo );
	}
	
	public void salva( 
			Long logadoUID, 
			Long procedimentoId, 
			ProfissionalProcedimentoSaveRequest request ) throws ServiceException {
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
		
		Optional<ProfissionalProcedimentoVinculo> vinculoOp = 
				profissionalProcedimentoVinculoRepository.busca( profissionalId, procedimentoId );
		
		if ( !vinculoOp.isPresent() )
			throw new ServiceException( Erro.VINCULO_PROFISSIONAL_PROCEDIMENTO_NAO_ENCONTRADO );
		
		ProfissionalProcedimentoVinculo vinculo = vinculoOp.get();
		profissionalProcedimentoVinculoLoader.loadBean( vinculo, request ); 
		
		profissionalProcedimentoVinculoRepository.save( vinculo );
	}
	
	public void deleta( Long logadoUID, Long procedimentoId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
		
		Optional<ProfissionalProcedimentoVinculo> vinculoOp = 
				profissionalProcedimentoVinculoRepository.busca( profissionalId, procedimentoId );
		
		ProfissionalProcedimentoVinculo vinculo = vinculoOp.get();
		Long vinculoId = vinculo.getId();
		
		profissionalProcedimentoVinculoRepository.deleteById( vinculoId );
	}
}

