package italo.xclin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.AnamneseModeloPerguntaLoader;
import italo.xclin.model.AnamneseModelo;
import italo.xclin.model.AnamneseModeloPergunta;
import italo.xclin.model.request.save.AnamneseModeloPerguntaSaveRequest;
import italo.xclin.model.response.AnamneseModeloPerguntaResponse;
import italo.xclin.model.response.load.edit.AnamneseModeloPerguntaEditLoadResponse;
import italo.xclin.model.response.load.reg.AnamneseModeloPerguntaRegLoadResponse;
import italo.xclin.repository.AnamneseModeloPerguntaRepository;
import italo.xclin.repository.AnamneseModeloRepository;

@Service
public class AnamneseModeloPerguntaService {

	@Autowired
	private AnamneseModeloPerguntaRepository anamneseModeloPerguntaRepository;
	
	@Autowired
	private AnamneseModeloRepository anamneseModeloRepository;
	
	@Autowired
	private AnamneseModeloPerguntaLoader anamneseModeloPerguntaLoader;
		
	public void registra( Long anamneseModeloId, AnamneseModeloPerguntaSaveRequest request ) throws ServiceException {
		Optional<AnamneseModelo> modeloOp = anamneseModeloRepository.findById( anamneseModeloId );
		if ( !modeloOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_MODELO_NAO_ENCONTRADO );
		
		AnamneseModelo modelo = modeloOp.get();
		
		AnamneseModeloPergunta pergunta = anamneseModeloPerguntaLoader.novoBean( modelo );
		anamneseModeloPerguntaLoader.loadBean( pergunta, request );
		
		anamneseModeloPerguntaRepository.save( pergunta );
	}
	
	public void altera( Long perguntaId, AnamneseModeloPerguntaSaveRequest request ) throws ServiceException {
		Optional<AnamneseModeloPergunta> perguntaOp = anamneseModeloPerguntaRepository.findById( perguntaId );
		if ( !perguntaOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_MODELO_PERGUNTA_NAO_ENCONTRADA );
		
		AnamneseModeloPergunta pergunta = perguntaOp.get();		
		anamneseModeloPerguntaLoader.loadBean( pergunta, request );
				
		anamneseModeloPerguntaRepository.save( pergunta );
	}
	
	public AnamneseModeloPerguntaResponse get( Long perguntaId ) throws ServiceException {
		Optional<AnamneseModeloPergunta> perguntaOp = anamneseModeloPerguntaRepository.findById( perguntaId );
		if ( !perguntaOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_MODELO_PERGUNTA_NAO_ENCONTRADA );
		
		AnamneseModeloPergunta pergunta = perguntaOp.get();
		
		AnamneseModeloPerguntaResponse resp = anamneseModeloPerguntaLoader.novoResponse();
		anamneseModeloPerguntaLoader.loadResponse( resp, pergunta );
		
		return resp;
	}
	
	public AnamneseModeloPerguntaEditLoadResponse novoEditLoad( Long anamneseModeloPerguntaId ) throws ServiceException {
		Optional<AnamneseModeloPergunta> perguntaOp = anamneseModeloPerguntaRepository.findById( anamneseModeloPerguntaId );
		if ( !perguntaOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_MODELO_PERGUNTA_NAO_ENCONTRADA );
		
		AnamneseModeloPergunta pergunta = perguntaOp.get();
		
		AnamneseModeloPerguntaResponse presp = anamneseModeloPerguntaLoader.novoResponse();
		anamneseModeloPerguntaLoader.loadResponse( presp, pergunta ); 
				
		AnamneseModeloPerguntaEditLoadResponse resp = anamneseModeloPerguntaLoader.novoEditResponse( presp );
		anamneseModeloPerguntaLoader.loadEditResponse( resp );
		
		return resp;
	}
	
	public AnamneseModeloPerguntaRegLoadResponse novoRegLoad() {
		AnamneseModeloPerguntaRegLoadResponse resp = anamneseModeloPerguntaLoader.novoRegResponse();
		anamneseModeloPerguntaLoader.loadRegResponse( resp );
		return resp;
	}
	
	public void deleta( Long perguntaId ) throws ServiceException {
		boolean existe = anamneseModeloPerguntaRepository.existsById( perguntaId );
		if ( !existe )
			throw new ServiceException( Erro.ANAMNESE_MODELO_PERGUNTA_NAO_ENCONTRADA );
		
		anamneseModeloPerguntaRepository.deleteById( perguntaId ); 
	}
	
}
