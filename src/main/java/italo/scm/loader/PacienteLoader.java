package italo.scm.loader;

import java.util.Date;

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
import italo.scm.model.response.PacienteResponse;
import italo.scm.model.response.load.PacienteEditLoadResponse;
import italo.scm.model.response.load.PacienteRegLoadResponse;

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
	
	public void loadResponse( PacienteResponse resp, Paciente p ) throws LoaderException {
		resp.setId( p.getId() );
		resp.setNome( p.getNome() );
		resp.setTelefone( p.getTelefone() );
		resp.setEmail( p.getEmail() );
		resp.setCpf( p.getCpf() );
		resp.setRg( p.getRg() );
		resp.setSexo( sexoEnumManager.tipoResponse( p.getSexo() ) );
		resp.setEstadoCivil( estadoCivilEnumManager.tipoResponse( p.getEstadoCivil() ) );
		resp.setNacionalidade( nacionalidadeEnumManager.tipoResponse( p.getNacionalidade() ) );

		resp.setOcupacao( p.getOcupacao() ); 
		resp.setObservacoes( p.getObservacoes() );
		
		resp.setDataNascimento( converter.dataToString( p.getDataNascimento() ) );
	}
	
	public void loadRegResponse( PacienteRegLoadResponse resp ) throws LoaderException {
		resp.setSexos( sexoEnumManager.tipoResponses() );
		resp.setEstadosCivis( estadoCivilEnumManager.tipoResponses() );
		resp.setNacionalidades( nacionalidadeEnumManager.tipoResponses() ); 
	}
	
	public void loadEditResponse( PacienteEditLoadResponse resp ) throws LoaderException {
		resp.setSexos( sexoEnumManager.tipoResponses() );
		resp.setEstadosCivis( estadoCivilEnumManager.tipoResponses() );
		resp.setNacionalidades( nacionalidadeEnumManager.tipoResponses() ); 
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
	
	public PacienteRegLoadResponse novoRegLoadResponse() {
		return new PacienteRegLoadResponse();
	}
	
	public PacienteEditLoadResponse novoEditLoadResponse( PacienteResponse presp ) {
		PacienteEditLoadResponse resp = new PacienteEditLoadResponse();
		resp.setPaciente( presp ); 
		return resp;
	}
	
}
