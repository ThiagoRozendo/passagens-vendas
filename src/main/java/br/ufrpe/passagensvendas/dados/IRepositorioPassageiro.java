package br.ufrpe.passagensvendas.dados;

import br.ufrpe.passagensvendas.negocio.beans.Passageiro;

public interface IRepositorioPassageiro extends IRepositorio<Passageiro> {
    Passageiro buscarPorCpf(String cpf);
}