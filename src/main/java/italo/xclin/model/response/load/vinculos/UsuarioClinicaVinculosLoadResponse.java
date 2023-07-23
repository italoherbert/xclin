package italo.xclin.model.response.load.vinculos;

import java.util.List;

import italo.xclin.model.response.UsuarioClinicaVinculoResponse;
import italo.xclin.model.response.UsuarioResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioClinicaVinculosLoadResponse {

	private UsuarioResponse usuario;
	
	private List<UsuarioClinicaVinculoResponse> vinculos;
		
}
