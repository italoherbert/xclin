package italo.xclin.service.shared;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.model.Clinica;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.repository.ClinicaRepository;

@Service
public class ClinicaSharedService {

	@Autowired
	private ClinicaRepository clinicaRepository;
		
	public ListaResponse listaPorIDs( Long[] clinicasIDs ) {
		List<Clinica> clinicas = clinicaRepository.buscaPorIDs( clinicasIDs );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Clinica c : clinicas ) {
			clinicasIDs2.add( c.getId() );
			clinicasNomes2.add( c.getNome() );
		}
		
		return new ListaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
}