package italo.xclin.model.request.save.lista;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DiretorClinicaVinculoListaSaveRequest {

	private List<Long> clinicas;
	
}
