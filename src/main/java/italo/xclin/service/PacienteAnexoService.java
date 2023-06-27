package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.PacienteAnexoLoader;
import italo.xclin.model.Paciente;
import italo.xclin.model.PacienteAnexo;
import italo.xclin.model.request.save.PacienteAnexoSaveRequest;
import italo.xclin.model.response.PacienteAnexoResponse;
import italo.xclin.model.response.PacienteAnexoArquivoResponse;
import italo.xclin.model.response.load.tela.PacienteAnexoTelaLoadResponse;
import italo.xclin.repository.PacienteAnexoRepository;
import italo.xclin.repository.PacienteRepository;

@Service
public class PacienteAnexoService {

	@Autowired
	private PacienteAnexoRepository pacienteAnexoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private PacienteAnexoLoader pacienteAnexoLoader;
	
	public void registra( Long pacienteId, PacienteAnexoSaveRequest request ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_PACIENTE_NAO_ENCONTRADO );
		
		Paciente paciente = pacienteOp.get();
		
		PacienteAnexo anexo = pacienteAnexoLoader.novoBean( paciente );
		pacienteAnexoLoader.loadBean( anexo, request );
		
		pacienteAnexoRepository.save( anexo );
	}
	
	public PacienteAnexoArquivoResponse get( Long pacienteAnexoId ) throws ServiceException {
		Optional<PacienteAnexo> pacienteAnexoOp = pacienteAnexoRepository.findById( pacienteAnexoId );
		if ( !pacienteAnexoOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_ANEXO_NAO_ENCONTRADO );
		
		PacienteAnexo anexo = pacienteAnexoOp.get();
		
		PacienteAnexoArquivoResponse resp = pacienteAnexoLoader.novoArquivoResponse();
		pacienteAnexoLoader.loadResponse( resp, anexo ); 
		return resp;
	}
	
	public List<PacienteAnexoResponse> lista( Long pacienteId ) throws ServiceException {
		List<PacienteAnexo> anexos = pacienteAnexoRepository.findByPacienteId( pacienteId );
		
		List<PacienteAnexoResponse> lista = new ArrayList<>();
		for( PacienteAnexo anexo : anexos ) {
			PacienteAnexoResponse resp = pacienteAnexoLoader.novoResponse();
			pacienteAnexoLoader.loadResponse( resp, anexo );
			
			lista.add( resp );
		}
		
		return lista;
	}
	
	public PacienteAnexoTelaLoadResponse telaLoad( Long pacienteId ) throws ServiceException {
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_ANEXO_NAO_ENCONTRADO );
		
		Paciente paciente = pacienteOp.get();
		
		List<PacienteAnexo> anexos = paciente.getAnexos();
		
		List<PacienteAnexoResponse> anexosLista = new ArrayList<>();
		for( PacienteAnexo anexo : anexos ) {
			PacienteAnexoResponse aresp = pacienteAnexoLoader.novoResponse();
			pacienteAnexoLoader.loadResponse( aresp, anexo );
			
			anexosLista.add( aresp );
		}
		
		return pacienteAnexoLoader.novoTelaLoadResponse( paciente, anexosLista );
	}
	
	public void deleta( Long anexoId ) throws ServiceException {
		boolean existe = pacienteAnexoRepository.existsById( anexoId );
		if ( !existe )
			throw new ServiceException( Erro.PACIENTE_ANEXO_NAO_ENCONTRADO );
		
		pacienteAnexoRepository.deleteById( anexoId ); 
	}
	
}
