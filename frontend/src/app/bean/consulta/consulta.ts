
export interface Consulta {
    id : number;
    descricao : string;
    dataConsulta : string;
    valor : string;
    paga : boolean;
    retorno : boolean;
    tempoEstimado : number;
    pacienteId : number;
    pacienteNome : string; 
}