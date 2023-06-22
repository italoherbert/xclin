package italo.xclin.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LancamentoResponse {
	
	private Long id;
	
	private String tipo;
	
	private double valor;
	
	private String dataLancamento;
	
	private String observacoes;
	
	private Long usuarioId;
	
	private String usuarioUsername;
	
	private Long clinicaId;
	
	private String clinicaNome;
	
	private String tipoLabel;		
	
}
