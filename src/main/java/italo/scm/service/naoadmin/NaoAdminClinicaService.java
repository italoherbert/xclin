package italo.scm.service.naoadmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.ClinicaLoader;
import italo.scm.loader.EnderecoLoader;
import italo.scm.loader.UsuarioLoader;
import italo.scm.model.Clinica;
import italo.scm.model.Endereco;
import italo.scm.model.response.ClinicaResponse;
import italo.scm.model.response.EnderecoResponse;
import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.UFResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.detalhes.ClinicaDetalhesLoadResponse;
import italo.scm.model.response.load.tela.NaoAdminClinicaTelaLoadResponse;
import italo.scm.repository.ClinicaRepository;
import italo.scm.service.shared.LocalidadesSharedService;

@Service
public class NaoAdminClinicaService {

	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private ClinicaLoader clinicaLoader;
	
	@Autowired
	private EnderecoLoader enderecoLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;
	
	@Autowired
	private LocalidadesSharedService localidadesSharedService;
	
	public NaoAdminClinicaTelaLoadResponse getTelaLoad( Long[] clinicasIDs ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		return clinicaLoader.novoNaoAdminTelaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
	public ClinicaDetalhesLoadResponse getDetalhesLoad( Long clinicaId, Long[] clinicasIDs ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.busca( clinicaId, clinicasIDs );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica c = clinicaOp.get();		
		Endereco e = c.getEndereco();
				
		EnderecoResponse eresp = enderecoLoader.novoResponse();
		enderecoLoader.loadResponse( eresp, c.getEndereco() ); 
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, c.getCriador() ); 
		
		ClinicaResponse cresp = clinicaLoader.novoResponse( eresp, uresp );
		clinicaLoader.loadResponse( cresp, c );
		
		int codUf = e.getCodigoUf();
		int codMunicipio = e.getCodigoMunicipio();
		
		UFResponse uf = localidadesSharedService.getUfPorId( codUf );
		MunicipioResponse municipio = localidadesSharedService.getMunicipioPorId( codMunicipio ); 
		
		ClinicaDetalhesLoadResponse resp = clinicaLoader.novoDetalhesResponse( cresp, uf, municipio );		
		return resp;
	}
	
}
