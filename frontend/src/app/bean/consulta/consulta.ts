
export interface Consulta {
    id : number;
    observacoes : string;
    dataAtendimento : string;
    valor : number;
    paga : boolean;
    retorno : boolean;
    status : string;
    turno : string;
    tempoEstimado : number;
    pacienteId : number;
    pacienteNome : string;
    clinicaId : number;
    clinicaNome : string; 

    statusLabel : string;
    turnoLabel : string;
}