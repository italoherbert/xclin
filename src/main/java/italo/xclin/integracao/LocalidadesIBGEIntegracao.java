package italo.xclin.integracao;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import italo.xclin.model.response.MunicipioResponse;
import italo.xclin.model.response.UFResponse;

@FeignClient(value = "localidades-api", url = "https://servicodados.ibge.gov.br/api/v1/localidades")
public interface LocalidadesIBGEIntegracao {

	@GetMapping("/estados?orderBy=nome")
	public List<UFResponse> listaUFs();
	
	@GetMapping("/estados/{ufid}/municipios?orderBy=nome")
	public List<MunicipioResponse> listaMunicipios( @PathVariable int ufid );
	
	@GetMapping("/estados/{ufid}")
	public UFResponse getUfPorId( @PathVariable int ufid );
	
	@GetMapping("/municipios/{mid}")
	public MunicipioResponse getMunicipioPorId( @PathVariable int mid );
	
}
