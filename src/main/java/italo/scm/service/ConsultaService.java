package italo.scm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.Erro;
import italo.scm.exception.ServiceException;
import italo.scm.loader.ConsultaLoader;
import italo.scm.model.Clinica;
import italo.scm.model.Consulta;
import italo.scm.model.Paciente;
import italo.scm.model.Profissional;
import italo.scm.model.request.save.ConsultaSaveRequest;
import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.ListaResponse;
import italo.scm.model.response.load.ConsultaAgendaTelaLoadResponse;
import italo.scm.model.response.load.ConsultaRegLoadResponse;
import italo.scm.repository.ClinicaRepository;
import italo.scm.repository.ConsultaRepository;
import italo.scm.repository.PacienteRepository;
import italo.scm.repository.ProfissionalRepository;
import italo.scm.service.shared.ClinicaSharedService;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private ClinicaSharedService clinicaSharedService;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ConsultaLoader consultaLoader;
	
	public void registra( Long clinicaId, Long profissionalId, Long pacienteId, ConsultaSaveRequest request ) throws ServiceException {
		Optional<Clinica> clinicaOp = clinicaRepository.findById( clinicaId );
		if ( !clinicaOp.isPresent() )
			throw new ServiceException( Erro.CLINICA_NAO_ENCONTRADA );
		
		Optional<Profissional> profissionalOp = profissionalRepository.findById( profissionalId );
		if ( !profissionalOp.isPresent() )
			throw new ServiceException( Erro.PROFISSIONAL_NAO_ENCONTRADO );
			
		Optional<Paciente> pacienteOp = pacienteRepository.findById( pacienteId );
		if ( !pacienteOp.isPresent() )
			throw new ServiceException( Erro.PACIENTE_NAO_ENCONTRADO );
		
		Clinica clinica = clinicaOp.get();
		Profissional profissional = profissionalOp.get();
		Paciente paciente = pacienteOp.get();
		
		Consulta consulta = consultaLoader.novoBean( profissional, paciente, clinica );
		consultaLoader.loadBean( consulta, request );
		
		consultaRepository.save( consulta );
	}
	
	public ConsultaResponse get( Long id, Long[] clinicasIDs ) throws ServiceException {
		Optional<Consulta> consultaOp = consultaRepository.findById( id );
		if ( !consultaOp.isPresent() )
			throw new ServiceException( Erro.CONSULTA_NAO_ENCONTRADA );
		
		Consulta consulta = consultaOp.get();
		Clinica clinica = consulta.getClinica();
		Paciente paciente = consulta.getPaciente();
		
		boolean achou = false;
		for( int i = 0; !achou && i < clinicasIDs.length; i++ )
			if ( clinicasIDs[ i ] == clinica.getId() )
				achou = true;
		
		if ( !achou )
			throw new ServiceException( Erro.CLINICA_ACESSO_NAO_AUTORIZADO );
					
		ConsultaResponse resp = consultaLoader.novoResponse( paciente, clinica );
		consultaLoader.loadResponse( resp, consulta );
		
		return resp;
	}
	
	public ConsultaRegLoadResponse getRegLoad() {
		ConsultaRegLoadResponse resp = consultaLoader.novoRegResponse();
		consultaLoader.loadRegResponse( resp );
		return resp;
	}
	
	public ConsultaAgendaTelaLoadResponse getAgendaTelaLoad( Long[] clinicasIDs ) {
		ListaResponse resp = clinicaSharedService.listaPorIDs( clinicasIDs );
		
		return consultaLoader.novoAgendaTelaResponse( resp.getIds(), resp.getNomes() ); 
	}
	
	public List<Object[]> agrupaPorDiaDeMes( 
			Long clinicaId, Long profissionalId, int mes, int ano ) throws ServiceException {

		return consultaRepository.agrupaPorDiaDeMes( clinicaId, profissionalId, mes, ano );		
	}
	
	public List<ConsultaResponse> listaPorDia( 
			Long clinicaId, Long profissionalId, int dia, int mes, int ano ) {
		
		List<ConsultaResponse> lista = new ArrayList<>();
		
		List<Consulta> consultas = consultaRepository.listaPorDia( clinicaId, profissionalId, dia, mes, ano );
		for( Consulta c : consultas ) {
			Paciente paciente = c.getPaciente();
			Clinica clinica = c.getClinica();
			
			ConsultaResponse resp = consultaLoader.novoResponse( paciente, clinica );
			consultaLoader.loadResponse( resp, c );
			
			lista.add( resp );
		}
		
		return lista;
	}
	
}
