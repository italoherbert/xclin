package italo.scm.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EnderecoResponse {

	private Long id;
	
	private String logradouro;
	
	private String numero;
	
	private String bairro;

	private int codigoMunicipio;
	
	private int codigoUf;
		
}
