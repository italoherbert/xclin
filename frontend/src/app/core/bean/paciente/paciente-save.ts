import { EnderecoSave } from "src/app/core/bean/endereco/endereco-save";

export interface PacienteSave {
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
    clinicaId : number;
    clinicaNome : string;
    endereco : EnderecoSave;
}