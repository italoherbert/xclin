package italo.xclin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.loader.ClinicaLoader;
import italo.xclin.loader.EnderecoLoader;
import italo.xclin.loader.UsuarioLoader;
import italo.xclin.model.Clinica;
import italo.xclin.model.Endereco;
import italo.xclin.model.Usuario;
import italo.xclin.model.request.filtro.ClinicaFiltroRequest;
import italo.xclin.model.request.save.ClinicaSaveRequest;
import italo.xclin.model.response.ClinicaResponse;
import italo.xclin.model.response.EnderecoResponse;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.model.response.MunicipioResponse;
import italo.xclin.model.response.UFResponse;
import italo.xclin.model.response.UsuarioResponse;
import italo.xclin.model.response.load.detalhes.ClinicaDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.ClinicaEditLoadResponse;
import italo.xclin.model.response.load.reg.ClinicaRegLoadResponse;
import italo.xclin.repository.ClinicaRepository;
import italo.xclin.repository.UsuarioRepository;
import italo.xclin.service.shared.LocalidadesSharedService;

@Service
public class ClinicaService {
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClinicaLoader clinicaLoader;
	
	@Autowired
	private EnderecoLoader enderecoLoader;
	
	@Autowired
	private UsuarioLoader usuarioLoader;
	
	@Autowired
	private LocalidadesSharedService localidadesSharedService;	
			
	public void registra( Long logadoUID, ClinicaSaveRequest request ) throws ServiceException {
		String nome = request.getNome();
		
		boolean existe = clinicaRepository.existePorNome( nome );
		if ( existe )
			throw new ServiceException( Erro.CLINICA_JA_EXISTE );
		
		Optional<Usuario> uop = usuarioRepository.findById( logadoUID );
		if ( !uop.isPresent() )
			throw new ServiceException( Erro.USUARIO_LOGADO_NAO_ENCONTRADO );
		
		Usuario usuarioLogado = uop.get();
		
		Endereco ender = enderecoLoader.novoBean();
		enderecoLoader.loadBean(ender, request.getEndereco() ); 
		
		Clinica clinica = clinicaLoader.novoBean( ender, usuarioLogado );
		clinicaLoader.loadBean( clinica, request );
		
		clinicaRepository.save( clinica );
	}

	public void altera( Long id, ClinicaSaveRequest request ) throws ServiceException {
		String reqnome = request.getNome();
									
		Optional<Clinica> cop = clinicaRepository.findById( id );
		if ( !cop.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica c = cop.get();
		
		String nome = c.getNome();
		if ( !reqnome.equalsIgnoreCase( nome ) ) {
			boolean existe = clinicaRepository.existePorNome( nome );
			if ( existe )
				throw new ServiceException( Erro.CLINICA_JA_EXISTE );
		}
		
		enderecoLoader.loadBean( c.getEndereco(), request.getEndereco() ); 		
		
		clinicaRepository.save( c );		
	}
	
	public List<ClinicaResponse> filtra( ClinicaFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();
		
		List<Clinica> clinicas;
		if ( nomeIni.equals( "*" ) ) {
			clinicas = clinicaRepository.findAll();
		} else {
			clinicas = clinicaRepository.filtra( "%"+nomeIni+"%" );
		}
		
		return this.clinicasToResponse( clinicas );
	}
	
	public List<ClinicaResponse> filtraPorNome( String nomeIni ) throws ServiceException {		
		List<Clinica> clinicas;
		if ( nomeIni.equals( "*" ) ) {
			clinicas = clinicaRepository.findAll();
		} else {
			clinicas = clinicaRepository.filtra( "%"+nomeIni+"%" );
		}
		
		return this.clinicasToResponse( clinicas );
	}
	
	public ListaResponse listaPorNome( String nomeIni, int limit ) throws ServiceException {
		List<Clinica> clinicas = clinicaRepository.lista( "%"+nomeIni+"%", PageRequest.of( 0, limit ) );
		
		List<Long> ids = new ArrayList<>();
		List<String> nomes = new ArrayList<>();
		for( Clinica c : clinicas ) {
			ids.add( c.getId() );
			nomes.add( c.getNome() );
		}
		
		return new ListaResponse( ids, nomes );
	}
			
	public List<ClinicaResponse> filtraPorIDs( Long[] clinicasIDs, ClinicaFiltroRequest request ) throws ServiceException {
		String nomeIni = request.getNomeIni();

		List<Clinica> clinicas;
		if ( nomeIni.equals( "*" ) ) {
			clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		} else {
			String nomeLike = "%"+nomeIni+"%";
			clinicas = clinicaRepository.listaPorIDsPorNome( nomeLike, clinicasIDs );		
		}
		
		return this.clinicasToResponse( clinicas );
	}

	public List<ClinicaResponse> filtraPorIDsPorNome( Long[] clinicasIDs, String nomeIni ) throws ServiceException {
		List<Clinica> clinicas;
		if ( nomeIni.equals( "*" ) ) {
			clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		} else {
			String nomeLike = "%"+nomeIni+"%";
			clinicas = clinicaRepository.listaPorIDsPorNome( nomeLike, clinicasIDs );			
		}
		return this.clinicasToResponse( clinicas );
	}

	public ListaResponse listaPorIDsPorNome( Long[] clinicasIDs, String nomeIni, int limit ) throws ServiceException {
		List<Clinica> clinicas;
		if ( nomeIni.equals( "*" ) ) {
			clinicas = clinicaRepository.listaPorIDs( clinicasIDs );
		} else {
			String nomeLike = "%"+nomeIni+"%";
			clinicas = clinicaRepository.listaPorIDsPorNome( nomeLike, clinicasIDs, Pageable.ofSize( limit ) );		
		}

		List<Long> ids = new ArrayList<>();
		List<String> nomes = new ArrayList<>();

		for( Clinica c : clinicas ) {
			ids.add( c.getId() );
			nomes.add( c.getNome() );			
		}
		
		return new ListaResponse( ids, nomes );
	}

	public ClinicaResponse get( Long id ) throws ServiceException {
		Optional<Clinica> cop = clinicaRepository.findById( id );
		if ( !cop.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica c = cop.get();		
		
		EnderecoResponse eresp = enderecoLoader.novoResponse();
		enderecoLoader.loadResponse( eresp, c.getEndereco() ); 
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, c.getCriador() ); 
		
		ClinicaResponse resp = clinicaLoader.novoResponse( eresp, uresp );
		clinicaLoader.loadResponse( resp, c );
		
		return resp;
	}
	
	public ClinicaEditLoadResponse getEditLoad( Long id ) throws ServiceException {
		Optional<Clinica> cop = clinicaRepository.findById( id );
		if ( !cop.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica c = cop.get();		
		Endereco e = c.getEndereco();
				
		EnderecoResponse eresp = enderecoLoader.novoResponse();
		enderecoLoader.loadResponse( eresp, c.getEndereco() ); 
		
		UsuarioResponse uresp = usuarioLoader.novoResponse();
		usuarioLoader.loadResponse( uresp, c.getCriador() ); 
		
		ClinicaResponse cresp = clinicaLoader.novoResponse( eresp, uresp );
		clinicaLoader.loadResponse( cresp, c );
		
		int codUf = e.getCodigoUf();
		
		List<UFResponse> ufs = localidadesSharedService.listaUFs();
		List<MunicipioResponse> municipios = localidadesSharedService.listaMunicipios( codUf );
		
		ClinicaEditLoadResponse resp = clinicaLoader.novoEditResponse( cresp, ufs, municipios );		
		return resp;
	}
	
	public ClinicaDetalhesLoadResponse getDetalhesLoad( Long id ) throws ServiceException {
		Optional<Clinica> cop = clinicaRepository.findById( id );
		if ( !cop.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Clinica c = cop.get();		
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
	
	public ClinicaRegLoadResponse getRegLoad() throws ServiceException {		
		List<UFResponse> ufs = localidadesSharedService.listaUFs();
		
		ClinicaRegLoadResponse resp = clinicaLoader.novoRegResponse( ufs );		
		return resp;
	}
		
	public void deleta( Long id ) throws ServiceException {
		boolean existe = clinicaRepository.existsById( id );
		if ( !existe )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		clinicaRepository.deleteById( id ); 
	}
	
	private List<ClinicaResponse> clinicasToResponse( List<Clinica> clinicas ) throws ServiceException {
		List<ClinicaResponse> lista = new ArrayList<>();
		for( Clinica c : clinicas ) {									
			EnderecoResponse eresp = enderecoLoader.novoResponse();
			enderecoLoader.loadResponse( eresp, c.getEndereco() ); 
			
			UsuarioResponse uresp = usuarioLoader.novoResponse();
			usuarioLoader.loadResponse( uresp, c.getCriador() ); 
			
			ClinicaResponse resp = clinicaLoader.novoResponse( eresp, uresp );
			clinicaLoader.loadResponse( resp, c );
									
			lista.add( resp );
		}
		
		return lista;
	}
	
}
