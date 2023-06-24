package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.AnamneseLoader;
import italo.xclin.loader.AnamnesePerguntaLoader;
import italo.xclin.model.Anamnese;
import italo.xclin.model.AnamneseModelo;
import italo.xclin.model.AnamnesePergunta;
import italo.xclin.model.Paciente;
import italo.xclin.model.Profissional;
import italo.xclin.model.request.save.AnamnesePerguntaSaveRequest;
import italo.xclin.model.request.save.AnamneseSaveRequest;
import italo.xclin.model.response.AnamnesePerguntaResponse;
import italo.xclin.model.response.AnamneseResponse;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.load.edit.AnamneseEditLoadResponse;
import italo.xclin.model.response.load.reg.AnamneseRegLoadResponse;
import italo.xclin.repository.AnamneseModeloRepository;
import italo.xclin.repository.AnamnesePerguntaRepository;
import italo.xclin.repository.AnamneseRepository;
import italo.xclin.repository.PacienteRepository;
import italo.xclin.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;

@Service
public class AnamneseService {

	@Autowired
	private AnamneseRepository anamneseRepository;
	
	@Autowired
	private AnamnesePerguntaRepository anamnesePerguntaRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private AnamneseModeloRepository anamneseModeloRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private AnamneseLoader anamneseLoader;
	
	@Autowired
	private AnamnesePerguntaLoader anamnesePerguntaLoader;
		
	public void vinculaAnamnese( Long pacienteId, Long anamneseModeloId ) throws ServiceException {
		Optional<AnamneseModelo> anamneseModeloOp = anamneseModeloRepository.findById( anamneseModeloId );
		if ( !anamneseModeloOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_MODELO_NAO_ENCONTRADO );
				
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Paciente paciente = pacienteOp.get();
		AnamneseModelo modelo = anamneseModeloOp.get();
		
		Anamnese anamnese = anamneseLoader.novoBean( paciente );
		anamneseLoader.loadBean( anamnese, modelo );
		
		anamneseRepository.save( anamnese );
	}
	
	@Transactional
	public void alteraAnamnesePerguntas( Long anamneseId, AnamneseSaveRequest request ) throws ServiceException {
		Optional<Anamnese> anamneseOp = anamneseRepository.findById( anamneseId );
		if ( !anamneseOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_NAO_ENCONTRADA );
					
		Anamnese anamnese = anamneseOp.get();
		
		anamnesePerguntaRepository.deleteByAnamneseId( anamneseId );
		
		List<AnamnesePerguntaSaveRequest> perguntas = request.getPerguntas();		
		for( AnamnesePerguntaSaveRequest pergunta : perguntas ) {
			AnamnesePergunta p = anamnesePerguntaLoader.novoBean( anamnese );
			anamnesePerguntaLoader.loadBean( p, pergunta );			
			anamnesePerguntaRepository.save( p );
		}		
	}
	
	public AnamneseResponse get( Long anamneseId ) throws ServiceException {
		Optional<Anamnese> anamneseOp = anamneseRepository.findById( anamneseId );
		if ( !anamneseOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_NAO_ENCONTRADA );
					
		Anamnese anamnese = anamneseOp.get();
		List<AnamnesePergunta> perguntas = anamnese.getPerguntas();
		
		List<AnamnesePerguntaResponse> pLista = new ArrayList<>();
		
		for( AnamnesePergunta pergunta : perguntas ) {
			AnamnesePerguntaResponse presp = anamnesePerguntaLoader.novoResponse();
			anamnesePerguntaLoader.loadResponse( presp, pergunta );
			
			pLista.add( presp );
		}
		
		AnamneseResponse resp = anamneseLoader.novoResponse( pLista );
		anamneseLoader.loadResponse( resp, anamnese );
		return resp;
	}
	
	public AnamneseRegLoadResponse loadRegTela( Long logadoUID ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		
		List<Long> ids = new ArrayList<>();
		List<String> nomes = new ArrayList<>();
		
		List<AnamneseModelo> modelos = profissional.getAnamneseModelos();
		for( AnamneseModelo modelo : modelos ) {
			ids.add( modelo.getId() );
			nomes.add( modelo.getNome() );
		}
		
		return anamneseLoader.novoRegResponse( new ListaResponse( ids, nomes ) );
		
	}
		
	public AnamneseEditLoadResponse loadEditTela( Long logadoUID, Long anamneseId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Optional<Anamnese> anamneseOp = anamneseRepository.findById( anamneseId );
		if ( !anamneseOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_NAO_ENCONTRADA );

		Profissional profissional = profissionalOp.get();
		Anamnese anamnese = anamneseOp.get();
		
		List<Long> modelosIDs = new ArrayList<>();
		List<String> modelosNomes = new ArrayList<>();
		
		List<AnamneseModelo> modelos = profissional.getAnamneseModelos();
		for( AnamneseModelo modelo : modelos ) {
			modelosIDs.add( modelo.getId() );
			modelosNomes.add( modelo.getNome() );
		}
		
		List<AnamnesePerguntaResponse> anamnesePerguntaLista = new ArrayList<>();
		
		List<AnamnesePergunta> perguntas = anamnese.getPerguntas();
		for( AnamnesePergunta pergunta : perguntas ) {
			AnamnesePerguntaResponse presp = anamnesePerguntaLoader.novoResponse();
			anamnesePerguntaLoader.loadResponse( presp, pergunta );
			
			anamnesePerguntaLista.add( presp );
		}
				
		AnamneseResponse aresp = anamneseLoader.novoResponse( anamnesePerguntaLista );
		anamneseLoader.loadResponse( aresp, anamnese ); 
		
		ListaResponse anamneseModelosLista = new ListaResponse( modelosIDs, modelosNomes );
		
		return anamneseLoader.novoEditResponse( aresp, anamneseModelosLista );
	}
	
}
