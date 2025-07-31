package br.ufrpe.passagensvendas.negocio;


import br.ufrpe.passagensvendas.negocio.beans.*;
import br.ufrpe.passagensvendas.negocio.controllers.ControladorPassageiros;
import br.ufrpe.passagensvendas.negocio.controllers.ControladorPassagens;
import br.ufrpe.passagensvendas.negocio.controllers.ControladorVoos;
import br.ufrpe.passagensvendas.negocio.excecoes.AssentosInsuficientesException;
import br.ufrpe.passagensvendas.negocio.excecoes.PassageiroJaCadastradoException;
import br.ufrpe.passagensvendas.negocio.excecoes.PassageiroNaoEncontradoException;
import br.ufrpe.passagensvendas.negocio.excecoes.VooNaoEncontradoException;


import java.util.List;

public class Fachada {
    private static Fachada instance;

    private ControladorPassageiros controladorPassageiros;
    private ControladorVoos controladorVoos;
    private ControladorPassagens controladorPassagens;

    private Fachada() {
        this.controladorPassageiros = ControladorPassageiros.getInstance();
        this.controladorVoos = ControladorVoos.getInstance();
        this.controladorPassagens = ControladorPassagens.getInstance();
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

    public void cadastrarVoo(Voo voo) {
        controladorVoos.cadastrarVoo(voo);
    }

    public List<Voo> listarVoos() {
        return controladorVoos.listarVoos();
    }

    public Voo[] listarVoosComoArray() {
        List<Voo> listaDeVoos = controladorVoos.listarVoos();
        return listaDeVoos.toArray(new Voo[0]);
    }

    public Voo buscarVooPorId(int id) throws VooNaoEncontradoException {
        return controladorVoos.buscarVooPorId(id);
    }

    public void comprarPassagem(String cpfPassageiro, int idVoo) throws VooNaoEncontradoException, PassageiroNaoEncontradoException, AssentosInsuficientesException {
        Passageiro p = buscarPassageiroPorCpf(cpfPassageiro);
        Voo v = buscarVooPorId(idVoo);
        controladorPassagens.comprarPassagem(p, v);
    }

    public List<Passagem> listarReservas() {
        return controladorPassagens.listar();
    }

    public List<Passagem> listarReservasPorPassageiro(String cpf) {
        return controladorPassagens.listarPorPassageiro(cpf);
    }
}