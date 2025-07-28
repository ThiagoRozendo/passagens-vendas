package br.ufrpe.passagensvendas.dados;

import br.ufrpe.passagensvendas.negocio.beans.Voo;

public interface IRepositorioVoo extends IRepositorio<Voo> {
    Voo buscarPorId(int id);
}
