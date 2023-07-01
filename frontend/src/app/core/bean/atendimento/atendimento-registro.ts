import { ConsultaSave } from "./consulta-save";
import { ExameItemSave } from "./exame-item-save";

export interface AtendimentoRegistro {
    dataAtendimento: string;
    turno : string;    
    observacoes: string;
    temConsulta : boolean;
    consulta : ConsultaSave;
    exames : ExameItemSave[];
}