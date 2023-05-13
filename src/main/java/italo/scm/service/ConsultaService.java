package italo.scm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.loader.ConsultaLoader;
import italo.scm.model.response.ListaResponse;
import italo.scm.model.response.load.ConsultaAgendaTelaLoadResponse;
import italo.scm.service.shared.ClinicaSharedService;

@Service
public class ConsultaService {

	@Autowired
	private ClinicaSharedService clinicaSharedService;
	
	@Autowired
	private ConsultaLoader consultaLoader;
	
	public ConsultaAgendaTelaLoadResponse getAgendaTelaLoad( Long[] clinicasIDs ) {
		ListaResponse resp = clinicaSharedService.listaPorIDs( clinicasIDs );
		
		return consultaLoader.novoAgendaTelaResponse( resp.getIds(), resp.getNomes() ); 
	}
	
}
