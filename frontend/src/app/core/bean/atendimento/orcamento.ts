import { Consulta } from "./consulta";
import { ExameItem } from "./exame-item";
import { ProcedimentoItem } from "./procedimento-item";

export interface Orcamento {
    temConsulta : boolean;
    valorTotal : number;
    valorPago : number;
    pago : boolean;
    consulta : Consulta,
    exames : ExameItem[],
    procedimentos : ProcedimentoItem[]
}