package italo.xclin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ProfissionalExameVinculoLoader;
import italo.xclin.model.Profissional;
import italo.xclin.model.ProfissionalExameVinculo;
import italo.xclin.model.response.ProfissionalExameVinculoResponse;
import italo.xclin.repository.ProfissionalExameVinculoRepository;
import italo.xclin.repository.ProfissionalRepository;

@Service
public class ProfissionalClinicaExameVinculoService {

	@Autowired
	private ProfissionalExameVinculoRepository profissionalClinicaExameVinculoRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private ProfissionalExameVinculoLoader profissionalClinicaExameVinculoLoader;
	
	public List<ProfissionalExameVinculoResponse> lista( Long logadoUID ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.buscaPorUsuario( logadoUID );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROF_LOGADO_NAO_ENCONTRADO );
		
		Profissional profissional = profissionalOp.get();
		
		List<ProfissionalExameVinculo> vinculos = profissional.getProfissionalClinicaExameVinculos();
		for( ProfissionalExameVinculo vinculo : vinculos ) {
			
		}
		
		return null;
	}
	
}
