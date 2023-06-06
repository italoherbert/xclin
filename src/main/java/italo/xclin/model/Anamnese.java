package italo.xclin.model;

import java.util.Date;

import italo.xclin.enums.tipos.Cicatrizacao;
import italo.xclin.enums.tipos.PressaoArterial;
import italo.xclin.enums.tipos.Sangramento;
import italo.xclin.enums.tipos.SangramentoNaGengiva;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="anamnese")
public class Anamnese {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private boolean tomaMedicamentos;
	
	private String quaisMedicamentos;
	
	private boolean temAlergias;
	
	private String quaisAlergias;
	
	@Enumerated(EnumType.STRING)
	private PressaoArterial pressaoArterial;
	
	private boolean temOuTeveProblemasDoCoracao;
	
	private String quaisProblemasDoCoracao;
	
	private boolean temDiabetes;
	
	private boolean temFaltaDeAr;
	
	@Enumerated(EnumType.STRING)
	private Sangramento sangramentoQuandoSeCorta;
	
	@Enumerated(EnumType.STRING)
	private Cicatrizacao cicatrizacao;
	
	private boolean jaFezCirurgia;
	
	private boolean ehGestante;
	
	private int semanasDeGestacao;
	
	private String problemasDeSaude;
	
	private String queixaPrincipal;
	
	private boolean jaTeveReacaoComAnestesiaDental;
	
	private String quaisReacoesTeveComAnestesiaDental;
	
	@Temporal(TemporalType.DATE)
	private Date dataUltimoTratamentoDentario;
	
	private boolean senteDoresNosDentesOuGengiva;
	
	@Enumerated(EnumType.STRING)
	private SangramentoNaGengiva sangramentoNaGengiva;
	
	private boolean senteGostoRuimNaBoca;
	
	private int quantasVezesEscovaOsDentes;
	
	private boolean usaFioDental;
	
	private boolean senteDoresNoMaxilarOuOuvido;
	
	private boolean rangeOsDentes;
	
	private boolean teveFeridaNaFaceOuNosLabios;
	
	private boolean fuma;
	
	private String quantidadeQueFuma;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="paciente_id")
	private Paciente paciente;
	
}
