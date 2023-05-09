package italo.scm.model.response.load;

import java.util.List;

import italo.scm.model.response.UFResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClinicaRegLoadResponse {

	private List<UFResponse> ufs;
	
}
