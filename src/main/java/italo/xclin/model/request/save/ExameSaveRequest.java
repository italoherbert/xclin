package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExameSaveRequest {

	private String nome;
	
	private String descricao;
	
	private double valor;
	
	private String dataExame;
	
}
