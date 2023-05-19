import { UsuarioSave } from "../usuario/usuario-save";

export interface ProfissionalSave {
    nome : string;
    valorConsulta: number;
    funcao : string;
    usuario : UsuarioSave;
}