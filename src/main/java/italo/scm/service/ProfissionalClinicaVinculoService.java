package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.ProfissionalLoader;
import italo.scm.loader.UsuarioLoader;
import italo.scm.model.Clinica;
import italo.scm.model.Profissional;
import italo.scm.model.ProfissionalClinicaVinculo;
import italo.scm.model.Usuario;
import italo.scm.model.request.save.ProfissionalClinicaVinculoListaSaveRequest;
import italo.scm.model.response.ProfissionalResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.outros.ProfissionalClinicaVinculosLoadResponse;
import italo.scm.repository.ClinicaRepository;
import italo.scm.repository.ProfissionalClinicaVinculoRepository;
import italo.scm.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;

@Service
public class ProfissionalClinicaVinculoService {

	@Autowired
	private ProfissionalClinicaVinculoRepository profissionalClinicaVinculoRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private ProfissionalLoader profissionalLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;
	
	public ProfissionalClinicaVinculosLoadResponse getVinculoLoad( Long profissionalId ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		Usuario usuario = profissional.getUsuario();
		
		List<Long> clinicasIDs = new ArrayList<>();
		List<String> clinicasNomes = new ArrayList<>();
		
		List<ProfissionalClinicaVinculo> vinculos = profissional.getProfissionalClinicaVinculos();
		for( ProfissionalClinicaVinculo vinculo : vinculos ) {
			Clinica c = vinculo.getClinica();
			clinicasIDs.add( c.getId() );
			clinicasNomes.add( c.getNome() );
		}
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, usuario ); 
		
		ProfissionalResponse presp = profissionalLoader.novoResponse( uresp );
		profissionalLoader.loadResponse( presp, profissional );
		
		ProfissionalClinicaVinculosLoadResponse resp = new ProfissionalClinicaVinculosLoadResponse();
		resp.setProfissional( presp );
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes );
		return resp;
	}
	
	@Transactional
	public void salva( Long profissionalId, ProfissionalClinicaVinculoListaSaveRequest request ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		
		profissionalClinicaVinculoRepository.deleteByProfissionalId( profissionalId );
		
		List<Long> ids = request.getClinicas();
		for( Long cid : ids ) {
			Optional<Clinica> clinicaOp = clinicaRepository.findById( cid );
			if ( !clinicaOp.isPresent() )
				throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
			
			Clinica clinica = clinicaOp.get();
			
			ProfissionalClinicaVinculo vinculo = new ProfissionalClinicaVinculo();
			vinculo.setProfissional( profissional);
			vinculo.setClinica( clinica );
			
			profissionalClinicaVinculoRepository.save( vinculo );			
		}
	}
	
}
