package italo.xclin.service.shared;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.model.Profissional;
import italo.xclin.model.response.ListaResponse;
import italo.xclin.repository.ProfissionalRepository;

@Service
public class ProfissionalSharedService {

	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	public ListaResponse listaPorClinica( Long clinicaId ) {
		List<Profissional> profissionais = profissionalRepository.listaPorClinica( clinicaId );
		return this.toListaResponse( profissionais );
	}
	
	public ListaResponse listaPorClinica( Long clinicaId, Long logadoUID ) {
		List<Profissional> profissionais = profissionalRepository.listaPorClinicaEUsuario( clinicaId, logadoUID );
		return this.toListaResponse( profissionais );
	}
	
	private ListaResponse toListaResponse( List<Profissional> profissionais ) {
		List<Long> clinicasIDs2 = new ArrayList<>();
		List<String> clinicasNomes2 = new ArrayList<>();
		
		for( Profissional p : profissionais ) {
			clinicasIDs2.add( p.getId() );
			clinicasNomes2.add( p.getNome() );
		}
		
		return new ListaResponse( clinicasIDs2, clinicasNomes2 );
	}
	
}
