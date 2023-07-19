import { ConsultaSave } from "./consulta-save";
import { ExameItemSave } from "./exame-item-save";
import { ProcedimentoItemSave } from "./procedimento-item-save";

export interface OrcamentoRegistro {
    temConsulta : boolean;
    valorTotal : number;
    valorPago : number;
    pago : boolean;
    consulta : ConsultaSave,
    exames : ExameItemSave[],
    procedimentos : ProcedimentoItemSave[]
}