package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.DiretorLoader;
import italo.scm.loader.UsuarioLoader;
import italo.scm.model.Clinica;
import italo.scm.model.Diretor;
import italo.scm.model.DiretorClinicaVinculo;
import italo.scm.model.Usuario;
import italo.scm.model.request.save.DiretorClinicaVinculoListaSaveRequest;
import italo.scm.model.response.DiretorResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.outros.DiretorClinicaVinculosLoadResponse;
import italo.scm.repository.ClinicaRepository;
import italo.scm.repository.DiretorClinicaVinculoRepository;
import italo.scm.repository.DiretorRepository;
import jakarta.transaction.Transactional;

@Service
public class DiretorClinicaVinculoService {

	@Autowired
	private DiretorClinicaVinculoRepository diretorClinicaVinculoRepository;
	
	@Autowired
	private DiretorRepository diretorRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private DiretorLoader diretorLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;
	
	public DiretorClinicaVinculosLoadResponse getVinculoLoad( Long diretorId ) throws ServiceException {
		Optional<Diretor> diretorOp = diretorRepository.findById( diretorId );
		if ( !diretorOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );
		
		Diretor diretor = diretorOp.get();
		Usuario usuario = diretor.getUsuario();
		
		List<Long> clinicasIDs = new ArrayList<>();
		List<String> clinicasNomes = new ArrayList<>();
		
		List<DiretorClinicaVinculo> vinculos = diretor.getDiretorClinicaVinculos();
		for( DiretorClinicaVinculo vinculo : vinculos ) {
			Clinica c = vinculo.getClinica();
			clinicasIDs.add( c.getId() );
			clinicasNomes.add( c.getNome() );
		}
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, usuario ); 
		
		DiretorResponse dresp = diretorLoader.novoResponse( uresp );
		diretorLoader.loadResponse( dresp, diretor );
		
		DiretorClinicaVinculosLoadResponse resp = new DiretorClinicaVinculosLoadResponse();
		resp.setDiretor( dresp );
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes );
		return resp;
	}
	
	@Transactional
	public void salva( Long diretorId, DiretorClinicaVinculoListaSaveRequest request ) throws ServiceException {
		Optional<Diretor> diretorOp = diretorRepository.findById( diretorId );
		if ( !diretorOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );
		
		Diretor diretor = diretorOp.get();
		
		diretorClinicaVinculoRepository.deleteByDiretorId( diretorId );
		
		List<Long> ids = request.getClinicas();
		for( Long cid : ids ) {
			Optional<Clinica> clinicaOp = clinicaRepository.findById( cid );
			if ( !clinicaOp.isPresent() )
				throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
			
			Clinica clinica = clinicaOp.get();
			
			DiretorClinicaVinculo vinculo = new DiretorClinicaVinculo();
			vinculo.setDiretor( diretor);
			vinculo.setClinica( clinica );
			
			diretorClinicaVinculoRepository.save( vinculo );			
		}
	}
	
}
