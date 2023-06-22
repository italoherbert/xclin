
export interface Lancamento {
    id : number;
    valor : number;
    tipo : string;

    observacoes : string;

    dataLancamento : string;

    usuarioId : number;
    usuarioUsername : string;

    clinicaId : number;
    clinicaNome : string;

    tipoLabel : string;
}