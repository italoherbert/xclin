package italo.scm.integracao;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.UFResponse;

@FeignClient(value = "localidades-api", url = "https://servicodados.ibge.gov.br/api/v1/localidades")
public interface LocalidadesIBGEIntegracao {

	@GetMapping("/estados")
	public List<UFResponse> listaUFs();
	
	@GetMapping("/estados/{ufid}/municipios")
	public List<MunicipioResponse> listaMunicipios( @PathVariable Long ufid );
	
}
