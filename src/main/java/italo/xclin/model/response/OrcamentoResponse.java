package italo.xclin.model.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrcamentoResponse {
		
	private boolean pago;
	
	private double valorTotalBruto;
	
	private double valorTotal;
	
	private double valorPago;

	private boolean temConsulta;

	private ConsultaResponse consulta;
	
	private List<ExameItemResponse> exames;
	
	private List<ProcedimentoItemResponse> procedimentos;
	
}
