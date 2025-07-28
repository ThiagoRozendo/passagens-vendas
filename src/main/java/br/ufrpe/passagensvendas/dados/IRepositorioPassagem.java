package br.ufrpe.passagensvendas.dados;

import br.ufrpe.passagensvendas.negocio.beans.Passagem;
import java.util.List;

public interface IRepositorioPassagem extends IRepositorio<Passagem> {
    List<Passagem> buscarPorCpfPassageiro(String cpf);
}