package br.ufrpe.passagensvendas.negocio.controllers;

import br.ufrpe.passagensvendas.dados.IRepositorioPassageiro;
import br.ufrpe.passagensvendas.dados.RepositorioPassageiro;
import br.ufrpe.passagensvendas.negocio.beans.Passageiro;
import br.ufrpe.passagensvendas.negocio.excecoes.PassageiroJaCadastradoException;
import br.ufrpe.passagensvendas.negocio.excecoes.PassageiroNaoEncontradoException;

import java.util.List;

public class ControladorPassageiros {
    private static ControladorPassageiros instance;
    private IRepositorioPassageiro repositorioPassageiro;

    private ControladorPassageiros() {
        this.repositorioPassageiro = RepositorioPassageiro.getInstance();
    }

    public static ControladorPassageiros getInstance() {
        if (instance == null) {
            instance = new ControladorPassageiros();
        }
        return instance;
    }

    public void cadastrar(Passageiro passageiro) throws PassageiroJaCadastradoException {
        if (passageiro == null || passageiro.getCpf() == null) {
            throw new IllegalArgumentException("Dados do passageiro são inválidos.");
        }
        if (this.repositorioPassageiro.buscarPorCpf(passageiro.getCpf()) != null) {
            throw new PassageiroJaCadastradoException("Passageiro com CPF " + passageiro.getCpf() + " já existe.");
        }
        this.repositorioPassageiro.cadastrar(passageiro);
    }

    public List<Passageiro> listar() {
        return this.repositorioPassageiro.listar();
    }

    public Passageiro buscarPorCpf(String cpf) throws PassageiroNaoEncontradoException {
        Passageiro passageiro = this.repositorioPassageiro.buscarPorCpf(cpf);
        if (passageiro == null) {
            throw new PassageiroNaoEncontradoException("Passageiro com CPF " + cpf + " não encontrado.");
        }
        return passageiro;
    }
}