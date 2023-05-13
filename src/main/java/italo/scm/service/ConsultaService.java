package italo.scm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.exception.ServiceException;
import italo.scm.loader.ConsultaLoader;
import italo.scm.model.Consulta;
import italo.scm.model.Paciente;
import italo.scm.model.response.ConsultaResponse;
import italo.scm.model.response.ListaResponse;
import italo.scm.model.response.load.ConsultaAgendaTelaLoadResponse;
import italo.scm.repository.ConsultaRepository;
import italo.scm.service.shared.ClinicaSharedService;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private ClinicaSharedService clinicaSharedService;
	
	@Autowired
	private ConsultaLoader consultaLoader;
	
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
			Paciente p = c.getPaciente();
			
			ConsultaResponse resp = consultaLoader.novoResponse( p );
			consultaLoader.loadResponse( resp, c );
			
			lista.add( resp );
		}
		
		return lista;
	}
	
}
