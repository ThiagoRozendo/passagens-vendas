package br.ufrpe.passagensvendas.negocio;


import br.ufrpe.passagensvendas.negocio.beans.*;
import br.ufrpe.passagensvendas.negocio.controllers.ControladorPassageiros;
import br.ufrpe.passagensvendas.negocio.excecoes.PassageiroJaCadastradoException;
import br.ufrpe.passagensvendas.negocio.excecoes.PassageiroNaoEncontradoException;

import java.util.List;

public class Fachada {
    private static Fachada instance;

    private ControladorPassageiros controladorPassageiros;

    private Fachada() {
        this.controladorPassageiros = ControladorPassageiros.getInstance();
    }

    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
    }

    public void cadastrarPassageiro(Passageiro passageiro) throws PassageiroJaCadastradoException {
        controladorPassageiros.cadastrar(passageiro);
    }

    public Passageiro buscarPassageiroPorCpf(String cpf) throws PassageiroNaoEncontradoException {
        return controladorPassageiros.buscarPorCpf(cpf);
    }

    public List<Passageiro> listarPassageiros() {
        return controladorPassageiros.listar();
    }
}