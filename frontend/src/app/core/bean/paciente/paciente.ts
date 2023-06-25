import { Endereco } from "src/app/core/bean/endereco/endereco";

export interface Paciente {
    id : number;
    nome : string;
    telefone : string;
    email : string;
    cpf : string;
    rg : string;
    sexo : any;
    nacionalidade : any;
    estadoCivil : any;
    dataNascimento : string;
    ocupacao : string;
    observacao : string;
    endereco : Endereco;
    anamneseCriada : boolean;
    clinicaId: number;
    clinicaNome: string;
}