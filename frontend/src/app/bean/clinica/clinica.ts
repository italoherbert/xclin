import { Endereco } from "../endereco/endereco";

export interface Clinica {
    id : number;
    nome: string;
    telefone : string;
    email : string;
    endereco : Endereco;
}