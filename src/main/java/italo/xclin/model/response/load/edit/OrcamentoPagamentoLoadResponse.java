package italo.xclin.model.response.load.edit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrcamentoPagamentoLoadResponse {

	private boolean pago;
	
	private double valorTotalBruto;
	
	private double valorTotal;
	
	private double valorPago;
	
}
