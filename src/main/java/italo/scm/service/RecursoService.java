package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.RecursoLoader;
import italo.scm.model.Recurso;
import italo.scm.model.request.filtro.RecursoFiltroRequest;
import italo.scm.model.request.save.RecursoSaveRequest;
import italo.scm.model.response.RecursoResponse;
import italo.scm.repository.RecursoRepository;

@Service
public class RecursoService {

	@Autowired
	private RecursoRepository recursoRepository;
	
	@Autowired
	private RecursoLoader recursoLoader;
	
	public void registra( RecursoSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		boolean existe = recursoRepository.existePorNome( nome );
		if ( existe )
			throw new ServiceException( Erro.RECURSO_JA_EXISTE );
		
		Recurso r = recursoLoader.novoBean();
		recursoLoader.loadBean( r, request );
		recursoRepository.save( r );
	}
	
	public void altera( Long id, RecursoSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		Optional<Recurso> rop = recursoRepository.findById( id );
		if ( !rop.isPresent() )
			throw new ServiceException( Erro.RECURSO_NAO_ENCONTRADO );
		
		Recurso r = rop.get();
		
		if ( !nome.equalsIgnoreCase( r.getNome() ) ) {
			boolean existe = recursoRepository.existePorNome( nome );
			if ( existe )
				throw new ServiceException( Erro.RECURSO_JA_EXISTE );
		}
		
		recursoLoader.loadBean( r, request );
		recursoRepository.save( r );
	}
	
	public List<RecursoResponse> filtra( RecursoFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();
		
		List<Recurso> recursos;
		if ( nomeIni.equals( "*" ) ) {
			recursos = recursoRepository.findAll();		
		} else {
			recursos = recursoRepository.filtra( nomeIni+"%" );
		}
		
		List<RecursoResponse> lista = new ArrayList<>();
		for( Recurso r : recursos ) {
			RecursoResponse resp = recursoLoader.novoResponse();
			recursoLoader.loadResponse( resp, r );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public RecursoResponse get( Long id ) throws ServiceException {
		Optional<Recurso> rop = recursoRepository.findById( id );
		if ( !rop.isPresent() )
			throw new ServiceException( Erro.RECURSO_NAO_ENCONTRADO );
		
		Recurso r = rop.get();
		
		RecursoResponse resp = recursoLoader.novoResponse();
		recursoLoader.loadResponse( resp, r );
		return resp;
	}
	
	public void deleta( Long id ) throws ServiceException {
		boolean existe = recursoRepository.existsById( id );
		if ( !existe )
			throw new ServiceException( Erro.RECURSO_NAO_ENCONTRADO );
		
		recursoRepository.deleteById( id ); 
	}
	
}
