import { ConsultaSave } from "./consulta-save";
import { ExameItemSave } from "./exame-item-save";
import { ProcedimentoItemSave } from "./procedimento-item-save";

export interface AtendimentoRegistro {
    dataAtendimento: string;
    turno : string;    
    observacoes: string;
    temConsulta : boolean;
    pago : boolean;
    valorTotal : number;
    valorPago : number;
    consulta : ConsultaSave;
    exames : ExameItemSave[];
    procedimentos : ProcedimentoItemSave[];
}