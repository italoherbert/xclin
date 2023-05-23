
export interface Consulta {
    id : number;
    observacoes : string;
    dataAtendimento : string;
    valor : number;
    paga : boolean;
    retorno : boolean;
    status : string;
    turno : string;
    pacienteId : number;
    pacienteNome : string;
    clinicaId : number;
    clinicaNome : string; 
    especialidadeId : number;
    especialidadeNome : string;
    statusLabel : string;
    turnoLabel : string;
}