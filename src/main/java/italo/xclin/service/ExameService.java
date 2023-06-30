package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ExameLoader;
import italo.xclin.model.Exame;
import italo.xclin.model.Paciente;
import italo.xclin.model.request.filtro.ExameFiltroRequest;
import italo.xclin.model.request.save.ExameSaveRequest;
import italo.xclin.model.response.ExameResponse;
import italo.xclin.repository.ExameRepository;
import italo.xclin.repository.PacienteRepository;

@Service
public class ExameService {

	@Autowired
	private ExameRepository exameRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ExameLoader exameLoader;
	
	public void registra( Long pacienteId, ExameSaveRequest request ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Paciente paciente = pacienteOp.get();
		
		Exame exame = exameLoader.novoBean( paciente );
		exameLoader.loadBean( exame, request );
		
		exameRepository.save( exame );
	}
	
	public List<ExameResponse> filtra( Long pacienteId, ExameFiltroRequest request ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );

		Paciente paciente = pacienteOp.get();
		
		String filtroNome = request.getFiltroNome();
		
		List<Exame> exames;
		if ( filtroNome.equals( "*" ) ) {
			exames = paciente.getExames();
		} else {
			exames = paciente.getExames()
					.stream()
					.filter( e -> e.getNome().matches( ".*"+filtroNome+".*" ) )
					.toList();
		}
		
		List<ExameResponse> lista = new ArrayList<>();
		for( Exame e : exames ) {
			ExameResponse eresp = exameLoader.novoResponse();
			exameLoader.loadResponse( eresp, e );
			
			lista.add( eresp );
		}
		return lista;
	}
	
	public ExameResponse get( Long exameId ) throws ServiceException {
		Optional<Exame> exameOp = exameRepository.findById( exameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		Exame exame = exameOp.get();
		
		ExameResponse resp = exameLoader.novoResponse();
		exameLoader.loadResponse( resp, exame );
		
		return resp;
	}
	
	public void deleta( Long exameId ) throws ServiceException {
		boolean existe = exameRepository.existsById( exameId );
		if ( !existe )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		exameRepository.deleteById( exameId ); 
	}
	
}
