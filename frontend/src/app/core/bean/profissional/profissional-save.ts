import { UsuarioSave } from "../usuario/usuario-save";

export interface ProfissionalSave {
    nome : string;
    funcao : string;
    usuario : UsuarioSave;
}