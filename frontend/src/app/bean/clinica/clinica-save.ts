import { EnderecoSave } from "../endereco/endereco-save";

export interface ClinicaSave {
    nome: string;
    telefone : string;
    email : string;
    endereco : EnderecoSave;
}