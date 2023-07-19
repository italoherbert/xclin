package italo.xclin.model.request.save;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AtendimentoSaveRequest {
	
	private String dataAtendimento;
	
	private String turno;
	
	private String observacoes;
	
	private boolean pago;
	
	private double valorTotal;
	
	private double valorPago;
	
	private boolean temConsulta;
	
	private ConsultaSaveRequest consulta;
	
	private List<ExameItemSaveRequest> exames;
	
	private List<ProcedimentoItemSaveRequest> procedimentos;

}
