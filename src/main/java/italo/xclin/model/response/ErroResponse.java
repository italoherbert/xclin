package italo.xclin.model.response;

import italo.xclin.exception.SistemaException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErroResponse {

	private String mensagem;
	
	public ErroResponse( SistemaException e ) {
		this.mensagem = e.getErroMensagem();
	}
	
}
