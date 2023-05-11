import { Endereco } from "../endereco/endereco";

export interface Paciente {
    id : number;
    nome : string;
    telefone : string;
    email : string;
    cpf : string;
    rg : string;
    sexo : string;
    nacionalidade : string;
    estadoCivil : string;
    dataNascimento : string;
    ocupacao : string;
    observacao : string;
    endereco : Endereco;

}