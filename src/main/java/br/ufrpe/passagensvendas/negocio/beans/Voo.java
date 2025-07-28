package br.ufrpe.passagensvendas.negocio.beans;

import java.time.LocalDateTime;

public abstract class Voo {
    private static int proximoId = 1;
    private int idVoo;
    private String origem;
    private String destino;
    private LocalDateTime dataHoraPartida;
    private int assentosDisponiveis;
    private double precoBase;

    public Voo(String origem, String destino, LocalDateTime dataHoraPartida, int assentosDisponiveis, double precoBase) {
        this.idVoo = proximoId++;
        this.origem = origem;
        this.destino = destino;
        this.dataHoraPartida = dataHoraPartida;
        this.assentosDisponiveis = assentosDisponiveis;
        this.precoBase = precoBase;
    }

    public abstract double calcularPrecoFinal();

    public void decrementarAssentos() {
        if (this.assentosDisponiveis > 0) {
            this.assentosDisponiveis--;
        }
    }

    public int getIdVoo() {
        return idVoo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public int getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public double getPrecoBase() {
        return precoBase;
    }
}