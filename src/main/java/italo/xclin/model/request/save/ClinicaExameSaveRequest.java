package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClinicaExameSaveRequest {

	private String nome;
	
	private String descricao;
	
	private double valor;
	
}
