
export interface Consulta {
    id : number;
    descricao : string;
    dataConsulta : string;
    valor : string;
    paga : boolean;
    retorno : boolean;
    pacienteId : number;
    pacienteNome : string; 
}