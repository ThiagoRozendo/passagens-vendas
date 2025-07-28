package br.ufrpe.passagensvendas.dados;

import br.ufrpe.passagensvendas.negocio.beans.Voo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioVoo implements IRepositorioVoo {
    private static RepositorioVoo instance;
    private List<Voo> voos;

    private RepositorioVoo() {
        this.voos = new ArrayList<>();
    }

    public static RepositorioVoo getInstance() {
        if (instance == null) {
            instance = new RepositorioVoo();
        }
        return instance;
    }

    @Override
    public void cadastrar(Voo voo) {
        voos.add(voo);
    }

    @Override
    public List<Voo> listar() {
        return new ArrayList<>(voos);
    }

    @Override
    public void remover(Voo voo) {
        voos.remove(voo);
    }

    @Override
    public Voo buscarPorId(int id) {
        for (Voo voo : voos) {
            if (voo.getIdVoo() == id) {
                return voo;
            }
        }
        return null;
    }
}