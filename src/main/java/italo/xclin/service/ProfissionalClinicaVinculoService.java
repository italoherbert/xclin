package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.exception.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ProfissionalLoader;
import italo.xclin.loader.UsuarioLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalClinicaVinculo;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.save.ProfissionalClinicaVinculoListaSaveRequest;
import italo.xclin.model.response.ProfissionalResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.outros.ProfissionalClinicaVinculosLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.ProfissionalClinicaVinculoRepository;
import italo.xclin.repository.ProfissionalRepository;
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
