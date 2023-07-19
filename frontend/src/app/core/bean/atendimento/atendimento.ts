import { Consulta } from "./consulta";
import { ExameItem } from "./exame-item";
import { ProcedimentoItem } from "./procedimento-item";

export interface Atendimento {
    id : number;
    observacoes : string;
    dataAtendimento : string;
    valorTotal : number;
    valorPago : number;
    pago : boolean;
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
    exames : ExameItem[],
    procedimentos : ProcedimentoItem[]
}