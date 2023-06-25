package italo.xclin.loader;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.enums.EstadoCivilEnumManager;
import italo.xclin.enums.NacionalidadeEnumManager;
import italo.xclin.enums.SexoEnumManager;
import italo.xclin.exception.ConverterException;
import italo.xclin.exception.LoaderException;
import italo.xclin.logica.Converter;
import italo.xclin.model.Clinica;
import italo.xclin.model.Endereco;
import italo.xclin.model.Paciente;
import italo.xclin.model.request.save.PacienteSaveRequest;
import italo.xclin.model.response.EnderecoResponse;
import italo.xclin.model.response.MunicipioResponse;
import italo.xclin.model.response.PacienteResponse;
import italo.xclin.model.response.UFResponse;
import italo.xclin.model.response.load.detalhes.PacienteDetalhesLoadResponse;
import italo.xclin.model.response.load.edit.PacienteEditLoadResponse;
import italo.xclin.model.response.load.reg.PacienteRegLoadResponse;
import italo.xclin.model.response.load.tela.PacienteTelaLoadResponse;

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
		p.setAnamneseCriada( false ); 

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
		resp.setAnamneseCriada( p.isAnamneseCriada() ); 
		
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
