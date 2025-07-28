package br.ufrpe.passagensvendas.dados;

import java.util.List;

public interface IRepositorio<T> {
    void cadastrar(T obj);
    List<T> listar();
    void remover(T obj);
}