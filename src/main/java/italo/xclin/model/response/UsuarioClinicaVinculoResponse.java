package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioClinicaVinculoResponse {

	private Long id;
	
	private Long usuarioId;
	
	private String usuarioUsername;
	
	private Long clinicaId;
	
	private String clinicaNome;
	
}
