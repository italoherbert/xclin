import { Acesso } from "../acesso/acesso";
import { UsuarioGrupo } from "./usuario-grupo";

export interface UsuarioGrupoDetalhes {
    id : any;
    grupo : UsuarioGrupo;
    acessos : Acesso[];
}