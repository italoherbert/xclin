package italo.scm.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EnderecoSaveRequest {

	private String logradouro;
	
	private String numero;
	
	private String bairro;
	
	private int codigoMunicipio;
	
	private int codigoUf;
	
}
