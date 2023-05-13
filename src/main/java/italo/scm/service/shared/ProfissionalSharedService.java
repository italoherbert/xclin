package italo.scm.service.shared;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.model.Profissional;
import italo.scm.model.response.ListaResponse;
import italo.scm.repository.ProfissionalRepository;

@Service
public class ProfissionalSharedService {

	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	public ListaResponse listaPorClinica( Long clinicaId ) {
		List<Profissional> profissionais = profissionalRepository.listaPorClinica( clinicaId );
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Profissional p : profissionais ) {
			clinicasIDs2.add( p.getId() );
			clinicasNomes2.add( p.getNome() );
		}
		
		return new ListaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
}
