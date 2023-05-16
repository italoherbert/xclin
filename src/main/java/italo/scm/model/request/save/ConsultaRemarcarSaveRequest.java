package italo.scm.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaRemarcarSaveRequest {

	private String dataAtendimento;
	
	private String turno;
	
}
