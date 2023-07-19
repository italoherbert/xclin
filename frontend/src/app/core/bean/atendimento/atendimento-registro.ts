import { OrcamentoRegistro } from "./orcamento-registro";

export interface AtendimentoRegistro {
    dataAtendimento: string;
    turno : string;    
    observacoes: string;    
    orcamento: OrcamentoRegistro;
}