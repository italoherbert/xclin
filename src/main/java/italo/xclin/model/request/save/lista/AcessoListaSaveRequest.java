package italo.xclin.model.request.save.lista;

import java.util.List;

import italo.xclin.model.request.save.AcessoSaveRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AcessoListaSaveRequest {

	private List<AcessoSaveRequest> acessos;
	
}
