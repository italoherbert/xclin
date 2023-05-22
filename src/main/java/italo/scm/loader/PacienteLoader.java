package italo.scm.loader;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.scm.enums.EstadoCivilEnumManager;
import italo.scm.enums.NacionalidadeEnumManager;
import italo.scm.enums.SexoEnumManager;
import italo.scm.exception.ConverterException;
import italo.scm.exception.LoaderException;
import italo.scm.logica.Converter;
import italo.scm.model.Clinica;
import italo.scm.model.Endereco;
import italo.scm.model.Paciente;
import italo.scm.model.request.save.PacienteSaveRequest;
import italo.scm.model.response.EnderecoResponse;
import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.PacienteResponse;
import italo.scm.model.response.UFResponse;
import italo.scm.model.response.load.detalhes.PacienteDetalhesLoadResponse;
import italo.scm.model.response.load.edit.PacienteEditLoadResponse;
import italo.scm.model.response.load.reg.PacienteRegLoadResponse;
import italo.scm.model.response.load.tela.PacienteTelaLoadResponse;

@Component
public class PacienteLoader {

	@Autowired
	private Converter converter;
	
	@Autowired
	private SexoEnumManager sexoEnumManager;
	
	@Autowired
	private EstadoCivilEnumManager estadoCivilEnumManager;
	
	@Autowired
	private NacionalidadeEnumManager nacionalidadeEnumManager;
		
	public void loadBean( Paciente p, PacienteSaveRequest request ) throws LoaderException {
		p.setNome( request.getNome() );
		p.setTelefone( request.getTelefone() );
		p.setEmail( request.getEmail() );
		p.setCpf( request.getCpf() );
		p.setRg( request.getRg() );
		p.setSexo( sexoEnumManager.getEnum( request.getSexo() ) );
		p.setNacionalidade( nacionalidadeEnumManager.getEnum( request.getNacionalidade() ) );
		p.setEstadoCivil( estadoCivilEnumManager.getEnum( request.getEstadoCivil() ) );
		p.setOcupacao( request.getOcupacao() );
		p.setObservacoes( request.getObservacoes() );
		p.setDataRegistro( new Date() ); 

		try {
			p.setDataNascimento( converter.stringToData( request.getDataNascimento() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		} 
	}
	
	public void loadResponse( PacienteResponse resp, Paciente p ) {
		resp.setId( p.getId() );
		resp.setNome( p.getNome() );
		resp.setTelefone( p.getTelefone() );
		resp.setEmail( p.getEmail() );
		resp.setCpf( p.getCpf() );
		resp.setRg( p.getRg() );
		
		if ( p.getSexo() != null )
			resp.setSexo( p.getSexo().name() );
		if ( p.getEstadoCivil() != null )
			resp.setEstadoCivil( p.getEstadoCivil().name() );
		if ( p.getNacionalidade() != null )
			resp.setNacionalidade( p.getNacionalidade().name() );

		resp.setOcupacao( p.getOcupacao() ); 
		resp.setObservacoes( p.getObservacoes() );
		
		resp.setDataNascimento( converter.dataToString( p.getDataNascimento() ) );
	}
	
	public void loadRegResponse( PacienteRegLoadResponse resp ) {
		resp.setSexos( sexoEnumManager.tipoResponses() );
		resp.setEstadosCivis( estadoCivilEnumManager.tipoResponses() );
		resp.setNacionalidades( nacionalidadeEnumManager.tipoResponses() ); 
	}
	
	public void loadEditResponse( PacienteEditLoadResponse resp ) {
		resp.setSexos( sexoEnumManager.tipoResponses() );
		resp.setEstadosCivis( estadoCivilEnumManager.tipoResponses() );
		resp.setNacionalidades( nacionalidadeEnumManager.tipoResponses() ); 
	}
	
	public void loadDetalhesResponse( PacienteDetalhesLoadResponse resp, Paciente p ) {
		if ( p.getEstadoCivil() != null )
			resp.setEstadoCivil( estadoCivilEnumManager.tipoResponse( p.getEstadoCivil() ) );
		if ( p.getSexo() != null )
			resp.setSexo( sexoEnumManager.tipoResponse( p.getSexo() ) );
		if ( p.getNacionalidade() != null )
			resp.setNacionalidade( nacionalidadeEnumManager.tipoResponse( p.getNacionalidade() ) );
	}
							
	public Paciente novoBean( Endereco e, Clinica c ) {
		Paciente paciente = new Paciente();
		paciente.setEndereco( e );
		paciente.setClinica( c );
		return paciente;
	}
	
	public PacienteResponse novoResponse( 
			EnderecoResponse eresp, Clinica clinica ) {
		
		PacienteResponse resp = new PacienteResponse();
		resp.setEndereco( eresp );
		resp.setClinicaId( clinica.getId() );
		resp.setClinicaNome( clinica.getNome() ); 
		return resp;
	}
	
	public PacienteRegLoadResponse novoRegLoadResponse( 
			List<UFResponse> ufs, 
			List<Long> clinicasIDs, 
			List<String> clinicasNomes ) {
		PacienteRegLoadResponse resp = new PacienteRegLoadResponse();
		resp.setUfs( ufs );
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes );
		return resp;
	}
	
	public PacienteEditLoadResponse novoEditLoadResponse( 
			PacienteResponse presp,
			List<UFResponse> ufs, 
			List<MunicipioResponse> municipios, 
			List<Long> clinicasIDs, 
			List<String> clinicasNomes ) {
		PacienteEditLoadResponse resp = new PacienteEditLoadResponse();
		resp.setPaciente( presp ); 
		resp.setUfs( ufs );
		resp.setMunicipios( municipios );
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes );
		return resp;
	}
	
	public PacienteDetalhesLoadResponse novoDetalhesLoadResponse( 
			PacienteResponse presp,
			UFResponse uf, 
			MunicipioResponse municipio ) {
		PacienteDetalhesLoadResponse resp = new PacienteDetalhesLoadResponse();
		resp.setPaciente( presp ); 
		resp.setUf( uf );
		resp.setMunicipio( municipio );
		return resp;
	}
	
	public PacienteTelaLoadResponse novoTelaLoadResponse( 
			List<Long> clinicasIDs, 
			List<String> clinicasNomes ) {
		PacienteTelaLoadResponse resp = new PacienteTelaLoadResponse();
		resp.setClinicasIDs( clinicasIDs );
		resp.setClinicasNomes( clinicasNomes ); 
		return resp;
	}
	
}
