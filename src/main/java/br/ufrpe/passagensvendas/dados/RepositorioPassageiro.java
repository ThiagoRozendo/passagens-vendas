package br.ufrpe.passagensvendas.dados;

import br.ufrpe.passagensvendas.negocio.beans.Passageiro;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPassageiro implements IRepositorioPassageiro {
    private static RepositorioPassageiro instance;
    private List<Passageiro> passageiros;

    private RepositorioPassageiro() {
        this.passageiros = new ArrayList<>();
    }

    public static RepositorioPassageiro getInstance() {
        if (instance == null) {
            instance = new RepositorioPassageiro();
        }
        return instance;
    }

    @Override
    public void cadastrar(Passageiro passageiro) {
        if (passageiro != null && buscarPorCpf(passageiro.getCpf()) == null) {
            this.passageiros.add(passageiro);
        }
    }

    @Override
    public List<Passageiro> listar() {
        return new ArrayList<>(this.passageiros);
    }

    @Override
    public void remover(Passageiro passageiro) {
        if (passageiro != null) {
            this.passageiros.remove(passageiro);
        }
    }

    @Override
    public Passageiro buscarPorCpf(String cpf) {
        for (Passageiro p : this.passageiros) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }
}