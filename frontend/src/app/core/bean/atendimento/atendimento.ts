import { Orcamento } from "./orcamento";

export interface Atendimento {
    id : number;
    observacoes : string;
    dataAtendimento : string;    
    status : string;
    turno : string;
    pacienteId : number;
    pacienteNome : string;
    profissionalId : number;
    profissionalNome : string;
    clinicaId : number;
    clinicaNome : string;     
    pacienteAnamneseCriada : boolean;
    statusLabel : string;
    turnoLabel : string;
    orcamento : Orcamento;
}