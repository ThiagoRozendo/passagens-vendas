package br.ufrpe.passagensvendas.negocio.excecoes;

public class VooNaoEncontradoException extends Exception {
    public VooNaoEncontradoException(String message) {
        super(message);
    }
}
