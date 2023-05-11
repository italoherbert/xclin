package italo.scm.service.shared;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.scm.integracao.LocalidadesIBGEIntegracao;
import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.UFResponse;

@Service
public class LocalidadesSharedService {

	@Autowired
	private LocalidadesIBGEIntegracao localidadesIBGEIntegracao;	
		
	public List<UFResponse> listaUFs() {
		return localidadesIBGEIntegracao.listaUFs();
	}
	
	public List<MunicipioResponse> listaMunicipios( int ufid ) {
		return localidadesIBGEIntegracao.listaMunicipios( ufid );
	}
	
	public UFResponse getUfPorId( int ufid ) {
		try {
			return localidadesIBGEIntegracao.getUfPorId( ufid );
		} catch ( Exception ex ) {
			return new UFResponse();			
		}
	}
	
	public MunicipioResponse getMunicipioPorId( int municipioId ) {
		try {
			return localidadesIBGEIntegracao.getMunicipioPorId( municipioId );
		} catch ( Exception ex ) {
			return new MunicipioResponse();			 
		}
	}	
	
}
