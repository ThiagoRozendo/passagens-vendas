package br.ufrpe.passagensvendas.negocio.beans;

public class Passageiro {
    private String nome;
    private String cpf;
    private String passaporte;

    public Passageiro(String nome, String cpf, String passaporte) {
        if (nome == null || nome.trim().isEmpty() || cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("Nome e CPF (11 dígitos) são obrigatórios.");
        }
        this.nome = nome;
        this.cpf = cpf;
        this.passaporte = passaporte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}