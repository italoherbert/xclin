import { UsuarioSave } from "../usuario/usuario-save";

export interface ProfissionalSave {
    nome : string;
    tempoConsulta: number;
    tempoConsultaRetorno: number;
    valorConsulta: number;
    funcao : string;
    usuario : UsuarioSave;
}