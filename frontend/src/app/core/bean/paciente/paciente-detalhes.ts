import { Paciente } from "./paciente";

import { Uf } from "src/app/core/bean/endereco/uf";
import { Municipio } from "src/app/core/bean/endereco/municipio";

export interface PacienteDetalhes {
    paciente : Paciente;
    uf : Uf;
    municipio: Municipio;
    sexo: any;
    estadoCivil: any;
    nacionalidade: any;
}