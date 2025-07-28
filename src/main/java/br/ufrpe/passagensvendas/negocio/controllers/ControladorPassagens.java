package br.ufrpe.passagensvendas.negocio.controllers;

import br.ufrpe.passagensvendas.dados.IRepositorioPassagem;
import br.ufrpe.passagensvendas.dados.RepositorioPassagem;
import br.ufrpe.passagensvendas.negocio.beans.Passageiro;
import br.ufrpe.passagensvendas.negocio.beans.Passagem;
import br.ufrpe.passagensvendas.negocio.beans.Voo;
import br.ufrpe.passagensvendas.negocio.excecoes.AssentosInsuficientesException;

import java.util.List;

public class ControladorPassagens {
    private static ControladorPassagens instance;
    private IRepositorioPassagem repositorioPassagem;

    private ControladorPassagens() {
        this.repositorioPassagem = RepositorioPassagem.getInstance();
    }

    public static ControladorPassagens getInstance() {
        if (instance == null) {
            instance = new ControladorPassagens();
        }
        return instance;
    }

    public void comprarPassagem(Passageiro passageiro, Voo voo) throws AssentosInsuficientesException {
        if (passageiro == null || voo == null) {
            throw new IllegalArgumentException("Passageiro e Voo não podem ser nulos.");
        }

        if (voo.getAssentosDisponiveis() <= 0) {
            throw new AssentosInsuficientesException("Não há assentos disponíveis para o voo " + voo.getIdVoo() + ".");
        }

        Passagem novaPassagem = new Passagem(passageiro, voo);
        this.repositorioPassagem.cadastrar(novaPassagem);

        voo.decrementarAssentos();
    }

    public List<Passagem> listar() {
        return this.repositorioPassagem.listar();
    }

    public List<Passagem> listarPorPassageiro(String cpf) {
        return this.repositorioPassagem.buscarPorCpfPassageiro(cpf);
    }
}