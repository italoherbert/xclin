package italo.scm.service;

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
import italo.scm.model.Usuario;
import italo.scm.model.request.filtro.ClinicaFiltroRequest;
import italo.scm.model.request.save.ClinicaSaveRequest;
import italo.scm.model.response.ClinicaResponse;
import italo.scm.model.response.EnderecoResponse;
import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.UFResponse;
import italo.scm.model.response.UsuarioResponse;
import italo.scm.model.response.load.detalhes.ClinicaDetalhesLoadResponse;
import italo.scm.model.response.load.edit.ClinicaEditLoadResponse;
import italo.scm.model.response.load.reg.ClinicaRegLoadResponse;
import italo.scm.repository.ClinicaRepository;
import italo.scm.repository.UsuarioRepository;
import italo.scm.service.shared.LocalidadesSharedService;

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
