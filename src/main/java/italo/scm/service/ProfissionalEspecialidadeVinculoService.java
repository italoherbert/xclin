package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.ProfissionalEspecialidadeVinculoLoader;
import italo.scm.loader.ProfissionalLoader;
import italo.scm.model.Especialidade;
import italo.scm.model.Profissional;
import italo.scm.model.ProfissionalEspecialidadeVinculo;
import italo.scm.model.request.save.ProfissionalEspecialidadeSaveRequest;
import italo.scm.model.response.ProfissionalEspecialidadeVinculoResponse;
import italo.scm.model.response.load.outros.ProfissionalContaEspecialidadeSaveLoadResponse;
import italo.scm.model.response.load.outros.ProfissionalEspecialidadeVinculosLoadResponse;
import italo.scm.repository.EspecialidadeRepository;
import italo.scm.repository.ProfissionalEspecialidadeVinculoRepository;
import italo.scm.repository.ProfissionalRepository;

@Service
public class ProfissionalEspecialidadeVinculoService {

	@Autowired
	private ProfissionalRepository profissionalRepository;
				
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private ProfissionalEspecialidadeVinculoRepository profissionalEspecialidadeVinculoRepository;
	
	@Autowired
	private ProfissionalLoader profissionalLoader;
	
	@Autowired
	private ProfissionalEspecialidadeVinculoLoader profissionalEspecialidadeVinculoLoader;
	
	public ProfissionalEspecialidadeVinculosLoadResponse getVinculosLoad( Long logadoUID ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		
		List<ProfissionalEspecialidadeVinculoResponse> lista = new ArrayList<>();
		
		List<ProfissionalEspecialidadeVinculo> vinculos = profissional.getProfissionalEspecialidadeVinculos();
		for( ProfissionalEspecialidadeVinculo v : vinculos ) {
			Especialidade e = v.getEspecialidade();
			ProfissionalEspecialidadeVinculoResponse resp = 
					profissionalEspecialidadeVinculoLoader.novoResponse( e );
			profissionalEspecialidadeVinculoLoader.loadResponse( resp, v );
			
			lista.add( resp );
		}
				
		return profissionalEspecialidadeVinculoLoader.novoEspecialidadeVinculosLoadResponse( profissional, lista );		
	}
	
	public ProfissionalContaEspecialidadeSaveLoadResponse getEspecialidadeSaveLoad( Long logadoUID ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		
		List<ProfissionalEspecialidadeVinculo> vinculos = profissional.getProfissionalEspecialidadeVinculos();
		int size = vinculos.size();
		
		List<Especialidade> especialidades = especialidadeRepository.findAll();
		
		List<Long> especialidadesIDs = new ArrayList<>();
		List<String> especialidadesNomes = new ArrayList<>();
		List<Boolean> especialidadesVinculadas = new ArrayList<>();		
		
		for( Especialidade e : especialidades ) {
			boolean jaVinculada = false;
			for( int i = 0; !jaVinculada && i < size; i++ ) {
				Especialidade e2 = vinculos.get( i ).getEspecialidade();
				if ( e.getNome().equalsIgnoreCase( e2.getNome() ) )
					jaVinculada = true;
			}			
			especialidadesIDs.add( e.getId() );
			especialidadesNomes.add( e.getNome() );
			especialidadesVinculadas.add( jaVinculada );			
		}
		return profissionalLoader.novoContaEspecialidadeSaveResponse( 
				especialidadesIDs, especialidadesNomes, especialidadesVinculadas );
	}
		
	public ProfissionalEspecialidadeVinculoResponse getPorLogadoUID( Long logadoUID, Long especialidadeId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
		
		return this.get( profissionalId, especialidadeId );
	}
	
	public ProfissionalEspecialidadeVinculoResponse get( Long profissionalId, Long especialidadeId ) throws ServiceException {
		Optional<ProfissionalEspecialidadeVinculo> vinculoOp = 
				profissionalEspecialidadeVinculoRepository.busca( profissionalId, especialidadeId );
		
		if ( !vinculoOp.isPresent() )
			throw new ServiceException( Erro.VINCULO_PROFISSIONAL_ESPECIALIDADE_NAO_ENCONTRADO );
		
		ProfissionalEspecialidadeVinculo vinculo = vinculoOp.get();
		Especialidade esp = vinculo.getEspecialidade();		
		
		ProfissionalEspecialidadeVinculoResponse resp = profissionalEspecialidadeVinculoLoader.novoResponse( esp );
		profissionalEspecialidadeVinculoLoader.loadResponse( resp, vinculo );
		
		return resp;
	}
		
	public void salvaAdd( Long logadoUID, Long especialidadeId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Optional<Especialidade> especialidadeOp = especialidadeRepository.findById( especialidadeId );
		if ( !especialidadeOp.isPresent() )
			throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );		
		
		Profissional profissional = profissionalOp.get();
		Especialidade especialidade = especialidadeOp.get();
				
		ProfissionalEspecialidadeVinculo vinculo = new ProfissionalEspecialidadeVinculo();
		vinculo.setProfissional( profissional );
		vinculo.setEspecialidade( especialidade ); 
		vinculo.setConsultaValor( 0 );
		
		profissionalEspecialidadeVinculoRepository.save( vinculo );
	}
	
	public void salva( 
			Long logadoUID, 
			Long especialidadeId, 
			ProfissionalEspecialidadeSaveRequest request ) throws ServiceException {
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
		
		Optional<ProfissionalEspecialidadeVinculo> vinculoOp = 
				profissionalEspecialidadeVinculoRepository.busca( profissionalId, especialidadeId );
		
		if ( !vinculoOp.isPresent() )
			throw new ServiceException( Erro.VINCULO_PROFISSIONAL_ESPECIALIDADE_NAO_ENCONTRADO );
		
		ProfissionalEspecialidadeVinculo vinculo = vinculoOp.get();
		vinculo.setConsultaValor( request.getConsultaValor() ); 
		
		profissionalEspecialidadeVinculoRepository.save( vinculo );
	}
	
	public void deleta( Long logadoUID, Long especialidadeId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Long profissionalId = profissional.getId();
		
		Optional<ProfissionalEspecialidadeVinculo> vinculoOp = 
				profissionalEspecialidadeVinculoRepository.busca( profissionalId, especialidadeId );
		
		ProfissionalEspecialidadeVinculo vinculo = vinculoOp.get();
		Long vinculoId = vinculo.getId();
		
		profissionalEspecialidadeVinculoRepository.deleteById( vinculoId );
	}
	
}
