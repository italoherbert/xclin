package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExameItemSaveRequest {

	private Long exameId;
	
	private double valor;
	
}