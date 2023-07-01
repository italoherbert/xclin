import { Consulta } from "./consulta";
import { ExameItem } from "./exame-item";

export interface Atendimento {
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
    profissionalId : number;
    profissionalNome : string;
    clinicaId : number;
    clinicaNome : string;     
    pacienteAnamneseCriada : boolean;
    temConsulta : boolean;
    statusLabel : string;
    turnoLabel : string;
    consulta : Consulta,
    exames : ExameItem[];
}