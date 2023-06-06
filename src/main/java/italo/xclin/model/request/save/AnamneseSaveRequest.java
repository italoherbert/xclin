package italo.xclin.model.request.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnamneseSaveRequest {

	private boolean tomaMedicamentos;
	
	private String quaisMedicamentos;
	
	private boolean temAlergias;
	
	private String quaisAlergias;
	
	private String pressaoArterial;
	
	private boolean temOuTeveProblemasDoCoracao;
	
	private String quaisProblemasDoCoracao;
	
	private boolean temDiabetes;
	
	private boolean temFaltaDeAr;
	
	private String sangramentoQuandoSeCorta;
	
	private String cicatrizacao;
	
	private boolean jaFezCirurgia;
	
	private boolean ehGestante;
	
	private int semanasDeGestacao;
	
	private String problemasDeSaude;
	
	private String queixaPrincipal;
	
	private boolean jaTeveReacaoComAnestesiaDental;
	
	private String quaisReacoesTeveComAnestesiaDental;
	
	private String dataUltimoTratamentoDentario;
	
	private boolean senteDoresNosDentesOuGengiva;
	
	private String sangramentoNaGengiva;
	
	private boolean senteGostoRuimNaBoca;
	
	private int quantasVezesEscovaOsDentes;
	
	private boolean usaFioDental;
	
	private boolean senteDoresNoMaxilarOuOuvido;
	
	private boolean rangeOsDentes;
	
	private boolean teveFeridaNaFaceOuNosLabios;
	
	private boolean fuma;
	
	private String quantidadeQueFuma;
	
}
