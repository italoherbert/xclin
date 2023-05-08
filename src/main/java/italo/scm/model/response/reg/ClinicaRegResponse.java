package italo.scm.model.response.reg;

import java.util.List;

import italo.scm.model.response.UFResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClinicaRegResponse {

	private List<UFResponse> ufs;
	
}
