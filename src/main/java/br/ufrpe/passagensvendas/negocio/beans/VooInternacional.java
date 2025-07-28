package br.ufrpe.passagensvendas.negocio.beans;

import java.time.LocalDateTime;

public class VooInternacional extends Voo {
    private static final double TAXA_EMBARQUE_INTERNACIONAL = 110.80;

    public VooInternacional(String origem, String destino, LocalDateTime dataHoraPartida, int assentosDisponiveis, double precoBase) {
        super(origem, destino, dataHoraPartida, assentosDisponiveis, precoBase);
    }

    @Override
    public double calcularPrecoFinal() {
        return getPrecoBase() + TAXA_EMBARQUE_INTERNACIONAL;
    }
}