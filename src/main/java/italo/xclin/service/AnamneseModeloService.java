package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.exception.ServiceException;
import italo.xclin.loader.AnamneseModeloLoader;
import italo.xclin.loader.AnamneseModeloPerguntaLoader;
import italo.xclin.model.AnamneseModelo;
import italo.xclin.model.AnamneseModeloPergunta;
import italo.xclin.model.Profissional;
import italo.xclin.model.request.filtro.AnamneseModeloFiltroRequest;
import italo.xclin.model.request.save.AnamneseModeloSaveRequest;
import italo.xclin.model.response.AnamneseModeloPerguntaResponse;
import italo.xclin.model.response.AnamneseModeloResponse;
import italo.xclin.model.response.load.detalhes.AnamneseModeloDetalhesLoadResponse;
import italo.xclin.model.response.load.outros.AnamneseModeloPerguntasLoadResponse;
import italo.xclin.msg.Erro;
import italo.xclin.repository.AnamneseModeloRepository;
import italo.xclin.repository.ProfissionalRepository;

@Service
public class AnamneseModeloService {

	@Autowired
	private AnamneseModeloRepository anamneseModeloRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private AnamneseModeloLoader anamneseModeloLoader;
	
	@Autowired
	private AnamneseModeloPerguntaLoader anamneseModeloPerguntaLoader;
	
	public void registra( Long logadoUID, AnamneseModeloSaveRequest request ) throws ServiceException {
		boolean existe = anamneseModeloRepository.existePorNome( request.getNome() );
		if ( existe )
			throw new ServiceException( Erro.ANAMNESE_MODELO_NOME_JA_EXISTE );
		
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional p = profissionalOp.get();
		
		AnamneseModelo a = anamneseModeloLoader.novoBean( p );
		anamneseModeloLoader.loadBean( a, request );		
		anamneseModeloRepository.save( a );
	}
	
	public void altera( Long id, AnamneseModeloSaveRequest request ) throws ServiceException {
		Optional<AnamneseModelo> aop = anamneseModeloRepository.findById( id );
		if ( !aop.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_MODELO_NAO_ENCONTRADO );
		
		AnamneseModelo a = aop.get();
		String anome = a.getNome();
		
		String nome = request.getNome();
		if ( !anome.equalsIgnoreCase( nome ) ) {
			boolean existe = anamneseModeloRepository.existePorNome( nome );
			if ( existe )
				throw new ServiceException( Erro.ANAMNESE_MODELO_NOME_JA_EXISTE );
		}
		
		anamneseModeloLoader.loadBean( a, request );
		
		anamneseModeloRepository.save( a );
	}
	
	public AnamneseModeloResponse get( Long anamneseModeloId ) throws ServiceException {
		Optional<AnamneseModelo> amOp = anamneseModeloRepository.findById( anamneseModeloId );
		if ( !amOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_MODELO_NAO_ENCONTRADO );
		
		AnamneseModelo am = amOp.get();
		
		AnamneseModeloResponse resp = anamneseModeloLoader.novoResponse();
		anamneseModeloLoader.loadResponse( resp, am );
		return resp;
	}
	
	public List<AnamneseModeloResponse> filtra( Long logadoUID, AnamneseModeloFiltroRequest request ) throws ServiceException {
		Optional<Profissional> prOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !prOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = prOp.get();
		
		String filtroNome = request.getFiltroNome();
		
		List<AnamneseModelo> modelos;
		if ( filtroNome.equals( "*" ) ) {
			modelos = profissional.getAnamneseModelos();
		} else {
			modelos = profissional.getAnamneseModelos().stream()
				.filter( (p) -> p.getNome().matches( ".*"+filtroNome+".*" )	)
				.toList();
		}

		List<AnamneseModeloResponse> lista = new ArrayList<>();
		for( AnamneseModelo m : modelos ) {
			AnamneseModeloResponse resp = anamneseModeloLoader.novoResponse();
			anamneseModeloLoader.loadResponse( resp, m );
			
			lista.add( resp );
		}
		return lista;
	}		
	
	public AnamneseModeloPerguntasLoadResponse novoPerguntasLoad( Long anamneseModeloId ) throws ServiceException {
		Optional<AnamneseModelo> amOp = anamneseModeloRepository.findById( anamneseModeloId );
		if ( !amOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_MODELO_NAO_ENCONTRADO );
		
		AnamneseModelo am = amOp.get();
		List<AnamneseModeloPergunta> perguntas = am.getPerguntas();
		
		List<AnamneseModeloPerguntaResponse> lista = new ArrayList<>();
		for( AnamneseModeloPergunta p : perguntas ) {
			AnamneseModeloPerguntaResponse presp = anamneseModeloPerguntaLoader.novoResponse();
			anamneseModeloPerguntaLoader.loadResponse( presp, p );
			
			lista.add( presp );
		}
		
		AnamneseModeloResponse amResp = anamneseModeloLoader.novoResponse();
		anamneseModeloLoader.loadResponse( amResp, am );
		
		AnamneseModeloPerguntasLoadResponse resp = anamneseModeloLoader.novoPerguntasResponse( amResp, lista );
		anamneseModeloLoader.loadPerguntasResponse( resp );
		
		return resp;
	}
	
	public AnamneseModeloDetalhesLoadResponse novoDetalhesLoad( Long anamneseModeloId ) throws ServiceException {
		Optional<AnamneseModelo> amOp = anamneseModeloRepository.findById( anamneseModeloId );
		if ( !amOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_MODELO_NAO_ENCONTRADO );
		
		AnamneseModelo am = amOp.get();
		List<AnamneseModeloPergunta> perguntas = am.getPerguntas();
		
		List<AnamneseModeloPerguntaResponse> lista = new ArrayList<>();
		for( AnamneseModeloPergunta p : perguntas ) {
			AnamneseModeloPerguntaResponse presp = anamneseModeloPerguntaLoader.novoResponse();
			anamneseModeloPerguntaLoader.loadResponse( presp, p );
			
			lista.add( presp );
		}
		
		AnamneseModeloResponse amResp = anamneseModeloLoader.novoResponse();
		anamneseModeloLoader.loadResponse( amResp, am );
		
		AnamneseModeloDetalhesLoadResponse resp = anamneseModeloLoader.novoDetalhesResponse( amResp, lista );
		anamneseModeloLoader.loadDetalhesResponse( resp );
		
		return resp;
	}
	
	public void deleta( Long amId ) throws ServiceException {
		boolean existe = anamneseModeloRepository.existsById( amId );
		if ( !existe )
			throw new ServiceException( Erro.ANAMNESE_MODELO_NAO_ENCONTRADO );
		
		anamneseModeloRepository.deleteById( amId );
	}
	
}
