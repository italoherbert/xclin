package italo.scm.model.request.save;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AcessoListaSaveRequest {

	private List<AcessoSaveRequest> acessos;
	
}
