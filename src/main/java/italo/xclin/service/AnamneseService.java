package italo.xclin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.exception.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.AnamneseLoader;
import italo.xclin.model.Anamnese;
import italo.xclin.model.Paciente;
import italo.xclin.model.request.save.AnamneseSaveRequest;
import italo.xclin.model.response.AnamneseResponse;
import italo.xclin.model.response.load.edit.AnamneseEditLoadResponse;
import italo.xclin.repository.AnamneseRepository;
import italo.xclin.repository.PacienteRepository;
import jakarta.transaction.Transactional;

@Service
public class AnamneseService {

	@Autowired
	private AnamneseRepository anamneseRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private AnamneseLoader anamneseLoader;
	
	@Transactional
	public void salva( Long pacienteId, AnamneseSaveRequest request ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Paciente p = pacienteOp.get();
		
		if ( !p.isAnamnesePreenchida() ) {
			Anamnese a = anamneseLoader.novoBean( p );
			anamneseLoader.loadBean( a, request );		
			anamneseRepository.save( a );
			
			p.setAnamnesePreenchida( true );
			pacienteRepository.save( p );
		} else {
			Anamnese a = p.getAnamnese();
			anamneseLoader.loadBean( a, request );
			anamneseRepository.save( a );
		}				
	}
		
	public AnamneseResponse get( Long pacienteId ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.ANAMNESE_NAO_ENCONTRADA );
		
		Paciente p = pacienteOp.get();
		
		if ( !p.isAnamnesePreenchida() )
			throw new ServiceException( Erro.ANAMNESE_NAO_PREENCHIDA );
		
		Anamnese a = p.getAnamnese();
		
		AnamneseResponse resp = anamneseLoader.novoResponse( p );
		anamneseLoader.loadResponse( resp, a );
		return resp;
	}
	
	public AnamneseEditLoadResponse getEditLoad( Long pacienteId ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Paciente p = pacienteOp.get();
		String pacienteNome = p.getNome();
		
		AnamneseEditLoadResponse resp;
		if ( p.isAnamnesePreenchida() ) {		
			Anamnese a = p.getAnamnese();
			
			AnamneseResponse aresp = anamneseLoader.novoResponse( p );
			anamneseLoader.loadResponse( aresp, a );
			
			resp = anamneseLoader.novoEditResponse( aresp );
		} else {
			resp = anamneseLoader.novoEditResponse();
		}

		anamneseLoader.loadEditResponse( resp, pacienteNome ); 
		return resp;
	}
			
}
