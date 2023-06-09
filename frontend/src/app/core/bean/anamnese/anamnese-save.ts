
export interface AnamneseSave {
    tomaMedicamentos : boolean;
    quaisMedicamentos : string;
    temAlergias : boolean;
    quaisAlergias : string;
    pressaoArterial : string;
    temOuTeveProblemasDoCoracao : boolean;
    quaisProblemasDoCoracao : string;
    temFaltaDeAr : boolean;
    temDiabetes : boolean;
    sangramentoQuandoSeCorta : string;
    cicatrizacao : string;
    jaFezCirurgia : boolean;    
    ehGestante : boolean;
    semanasDeGestacao : number;
    problemasDeSaude : string;
    queixaPrincipal : string;
    jaTeveReacaoComAnestesiaDental : boolean;
    quaisReacoesTeveComAnestesiaDental : string;
    dataUltimoTratamentoDentario : string;
    senteDoresNosDentesOuGengiva : boolean;
    sangramentoNaGengiva : string;
    senteGostoRuimNaBoca : boolean;
    quantasVezesEscovaOsDentes : number;
    usaFioDental : boolean;
    senteDoresNoMaxilarOuOuvido : boolean;
    rangeOsDentes : boolean;
    teveFeridaNaFaceOuNosLabios : boolean;
    fuma : boolean;
    quantidadeQueFuma : string;
}