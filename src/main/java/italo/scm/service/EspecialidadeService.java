package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.EspecialidadeLoader;
import italo.scm.model.Especialidade;
import italo.scm.model.request.filtro.EspecialidadeFiltroRequest;
import italo.scm.model.request.save.EspecialidadeSaveRequest;
import italo.scm.model.response.EspecialidadeResponse;
import italo.scm.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private EspecialidadeLoader especialidadeLoader;
	
	public void registra( EspecialidadeSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		boolean existe = especialidadeRepository.existePorNome( nome );
		if ( existe )
			throw new ServiceException( Erro.ESPECIALIDADE_JA_EXISTE );
		
		Especialidade e = especialidadeLoader.novoBean();
		especialidadeLoader.loadBean( e, request );
		especialidadeRepository.save( e );
	}
	
	public void altera( Long id, EspecialidadeSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		Optional<Especialidade> rop = especialidadeRepository.findById( id );
		if ( !rop.isPresent() )
			throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );
		
		Especialidade e = rop.get();
		
		if ( !nome.equalsIgnoreCase( e.getNome() ) ) {
			boolean existe = especialidadeRepository.existePorNome( nome );
			if ( existe )
				throw new ServiceException( Erro.ESPECIALIDADE_JA_EXISTE );
		}
		
		especialidadeLoader.loadBean( e, request );
		especialidadeRepository.save( e );
	}
	
	public List<EspecialidadeResponse> filtra( EspecialidadeFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();
		
		List<Especialidade> especialidades;
		if ( nomeIni.equals( "*" ) ) {
			especialidades = especialidadeRepository.findAll();		
		} else {
			especialidades = especialidadeRepository.filtra( nomeIni+"%" );
		}
		
		List<EspecialidadeResponse> lista = new ArrayList<>();
		for( Especialidade e : especialidades ) {
			EspecialidadeResponse resp = especialidadeLoader.novoResponse();
			especialidadeLoader.loadResponse( resp, e );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public List<EspecialidadeResponse> listaTodas() throws ServiceException {
		List<Especialidade> especialidades = especialidadeRepository.findAll();
		List<EspecialidadeResponse> lista = new ArrayList<>();
		for( Especialidade e : especialidades ) {
			EspecialidadeResponse resp = especialidadeLoader.novoResponse();
			especialidadeLoader.loadResponse( resp, e );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public EspecialidadeResponse get( Long id ) throws ServiceException {
		Optional<Especialidade> rop = especialidadeRepository.findById( id );
		if ( !rop.isPresent() )
			throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );
		
		Especialidade e = rop.get();
		
		EspecialidadeResponse resp = especialidadeLoader.novoResponse();
		especialidadeLoader.loadResponse( resp, e );
		return resp;
	}
	
	public void deleta( Long id ) throws ServiceException {
		boolean existe = especialidadeRepository.existsById( id );
		if ( !existe )
			throw new ServiceException( Erro.ESPECIALIDADE_NAO_ENCONTRADA );
		
		especialidadeRepository.deleteById( id ); 
	}
	
}
