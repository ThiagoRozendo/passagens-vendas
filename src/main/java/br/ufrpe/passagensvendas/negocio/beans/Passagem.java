package br.ufrpe.passagensvendas.negocio.beans;

public class Passagem {
    private static int proximoId = 1;
    private int idPassagem;
    private Passageiro passageiro;
    private Voo voo;
    private double valorPago;

    public Passagem(Passageiro passageiro, Voo voo) {
        this.idPassagem = proximoId++;
        this.passageiro = passageiro;
        this.voo = voo;
        this.valorPago = voo.calcularPrecoFinal();
    }

    public int getIdPassagem() { return idPassagem; }

    public Passageiro getPassageiro() { return passageiro; }

    public Voo getVoo() { return voo; }

    public double getValorPago() { return valorPago; }
}