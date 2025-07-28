package br.ufrpe.passagensvendas.negocio.excecoes;

public class AssentosInsuficientesException extends Exception {
    public AssentosInsuficientesException(String message) {
        super(message);
    }
}