package italo.scm.service.naoadmin;

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
import italo.scm.model.request.filtro.NaoAdminProfissionalFiltroRequest;
import italo.scm.model.response.ProfissionalResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.NaoAdminProfissionalTelaLoadResponse;
import italo.scm.model.response.load.ProfissionalDetalhesLoadResponse;
import italo.scm.repository.ClinicaRepository;
import italo.scm.repository.ProfissionalRepository;

@Service
public class NaoAdminProfissionalService {

	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private ProfissionalLoader profissionalLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;

	public List<ProfissionalResponse> filtra( Long clinicaId, NaoAdminProfissionalFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();
		
		List<Profissional> profissionais;
		if ( nomeIni.equals( "*" ) ) {
			profissionais = profissionalRepository.filtra( clinicaId );
		} else {		
			profissionais = profissionalRepository.filtra( clinicaId, nomeIni+"%" );
		}
		
		List<ProfissionalResponse> lista = new ArrayList<>();
		for( Profissional d : profissionais ) {
			Usuario u = d.getUsuario();
			
			UsuarioResponse uresp = usuarioLoader.novoResponse();
			usuarioLoader.loadResponse( uresp, u );
			
			ProfissionalResponse resp = profissionalLoader.novoResponse( uresp );
			profissionalLoader.loadResponse( resp, d );
			
			lista.add( resp );
		}
		return lista;
	}
	
	public NaoAdminProfissionalTelaLoadResponse getTelaLoad( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		return profissionalLoader.novoNaoAdminTelaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ProfissionalDetalhesLoadResponse getDetalhesLoad( Long profissionalId, Long[] clinicasIDs ) throws ServiceException {
		Optional<Profissional> profissionalOp = profissionalRepository.busca( profissionalId, clinicasIDs );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.DIRETOR_NAO_ENCONTRADO );
		
		Profissional d = profissionalOp.get();
		Usuario u = d.getUsuario();
		
		List<String> clinicas = new ArrayList<>();
		
		List<ProfissionalClinicaVinculo> vinculos = d.getProfissionalClinicaVinculos();
		for( ProfissionalClinicaVinculo v : vinculos )
			clinicas.add( v.getClinica().getNome() );
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, u );
		
		ProfissionalResponse resp = profissionalLoader.novoResponse( uresp );
		profissionalLoader.loadResponse( resp, d );
		
		return profissionalLoader.novoDetalhesResponse( resp, clinicas ); 
	}
	
}
