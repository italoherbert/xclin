import { UsuarioSave } from "../usuario/usuario-save";

export interface RecepcionistaSave {
    nome : string;
    usuario : UsuarioSave;
    clinicaId : number;
}