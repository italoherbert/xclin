package italo.xclin.model.response.load.detalhes;

import italo.xclin.model.response.MunicipioResponse;
import italo.xclin.model.response.PacienteResponse;
import italo.xclin.model.response.TipoResponse;
import italo.xclin.model.response.UFResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PacienteDetalhesLoadResponse {

	private PacienteResponse paciente;
	
	private UFResponse uf;
	
	private MunicipioResponse municipio;
	
	private TipoResponse sexo;
	
	private TipoResponse estadoCivil;
	
	private TipoResponse nacionalidade;
	
}
