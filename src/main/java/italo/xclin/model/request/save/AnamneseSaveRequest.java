package italo.xclin.model.request.save;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamneseSaveRequest {

	public List<AnamnesePerguntaSaveRequest> perguntas;
	
}
