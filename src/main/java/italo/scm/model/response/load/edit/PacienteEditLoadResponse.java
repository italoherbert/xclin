package italo.scm.model.response.load.edit;

import java.util.List;

import italo.scm.model.response.MunicipioResponse;
import italo.scm.model.response.PacienteResponse;
import italo.scm.model.response.TipoResponse;
import italo.scm.model.response.UFResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PacienteEditLoadResponse {

	private PacienteResponse paciente;
		
	private List<TipoResponse> sexos;
	
	private List<TipoResponse> nacionalidades;
	
	private List<TipoResponse> estadosCivis;
	
	private List<UFResponse> ufs;
	
	private List<MunicipioResponse> municipios;
	
	private List<Long> clinicasIDs;
	
	private List<String> clinicasNomes;
		
}
