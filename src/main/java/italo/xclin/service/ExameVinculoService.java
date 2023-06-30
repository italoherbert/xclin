package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ExameLoader;
import italo.xclin.model.ExameVinculo;
import italo.xclin.model.Paciente;
import italo.xclin.model.request.filtro.ExameFiltroRequest;
import italo.xclin.model.request.save.ExameSaveRequest;
import italo.xclin.model.response.ExameVinculoResponse;
import italo.xclin.repository.ExameVinculoRepository;
import italo.xclin.repository.PacienteRepository;

@Service
public class ExameVinculoService {

	@Autowired
	private ExameVinculoRepository exameRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ExameLoader exameLoader;
	
	public void registra( Long pacienteId, ExameSaveRequest request ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Paciente paciente = pacienteOp.get();
		
		ExameVinculo exame = exameLoader.novoBean( paciente );
		exameLoader.loadBean( exame, request );
		
		exameRepository.save( exame );
	}
	
	public List<ExameVinculoResponse> filtra( Long pacienteId, ExameFiltroRequest request ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );

		Paciente paciente = pacienteOp.get();
		
		String filtroNome = request.getFiltroNome();
		
		List<ExameVinculo> exames;
		if ( filtroNome.equals( "*" ) ) {
			exames = paciente.getExames();
		} else {
			exames = paciente.getExames()
					.stream()
					.filter( e -> e.getNome().matches( ".*"+filtroNome+".*" ) )
					.toList();
		}
		
		List<ExameVinculoResponse> lista = new ArrayList<>();
		for( ExameVinculo e : exames ) {
			ExameVinculoResponse eresp = exameLoader.novoResponse();
			exameLoader.loadResponse( eresp, e );
			
			lista.add( eresp );
		}
		return lista;
	}
	
	public ExameVinculoResponse get( Long exameId ) throws ServiceException {
		Optional<ExameVinculo> exameOp = exameRepository.findById( exameId );
		if ( !exameOp.isPresent() )
			throw new ServiceException( Erro.EXAME_NAO_ENCONTRADO );
		
		ExameVinculo exame = exameOp.get();
		
		ExameVinculoResponse resp = exameLoader.novoResponse();
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
