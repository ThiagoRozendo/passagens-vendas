package br.ufrpe.passagensvendas.negocio.controllers;

import br.ufrpe.passagensvendas.dados.IRepositorioVoo;
import br.ufrpe.passagensvendas.dados.RepositorioVoo;
import br.ufrpe.passagensvendas.negocio.beans.Voo;
import br.ufrpe.passagensvendas.negocio.excecoes.VooNaoEncontradoException;

import java.util.List;

public class ControladorVoos {
    private static ControladorVoos instance;
    private IRepositorioVoo repositorioVoo;

    private ControladorVoos() {
        this.repositorioVoo = RepositorioVoo.getInstance();
    }

    public static ControladorVoos getInstance() {
        if (instance == null) {
            instance = new ControladorVoos();
        }
        return instance;
    }

    public void cadastrarVoo(Voo voo) {
        if (voo != null && !voo.getOrigem().equalsIgnoreCase(voo.getDestino())) {
            this.repositorioVoo.cadastrar(voo);
        } else {
            throw new IllegalArgumentException("Dados do voo são inválidos.");
        }
    }

    public List<Voo> listarVoos() {
        return this.repositorioVoo.listar();
    }

    public Voo buscarVooPorId(int id) throws VooNaoEncontradoException {
        Voo voo = this.repositorioVoo.buscarPorId(id);
        if (voo == null) {
            throw new VooNaoEncontradoException("Voo com ID " + id + " não encontrado.");
        }
        return voo;
    }
}