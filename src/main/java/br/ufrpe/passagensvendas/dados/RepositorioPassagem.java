package br.ufrpe.passagensvendas.dados;

import br.ufrpe.passagensvendas.negocio.beans.Passagem;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPassagem implements IRepositorioPassagem {
    private static RepositorioPassagem instance;
    private List<Passagem> passagens;

    private RepositorioPassagem() {
        this.passagens = new ArrayList<>();
    }

    public static RepositorioPassagem getInstance() {
        if (instance == null) {
            instance = new RepositorioPassagem();
        }
        return instance;
    }

    @Override
    public void cadastrar(Passagem passagem) {
        if (passagem != null) {
            this.passagens.add(passagem);
        }
    }

    @Override
    public List<Passagem> listar() {
        return new ArrayList<>(this.passagens);
    }

    @Override
    public void remover(Passagem passagem) {
        if (passagem != null) {
            this.passagens.remove(passagem);
        }
    }

    @Override
    public List<Passagem> buscarPorCpfPassageiro(String cpf) {
        List<Passagem> passagensDoCliente = new ArrayList<>();
        for (Passagem p : this.passagens) {
            if (p.getPassageiro().getCpf().equals(cpf)) {
                passagensDoCliente.add(p);
            }
        }
        return passagensDoCliente;
    }
}
