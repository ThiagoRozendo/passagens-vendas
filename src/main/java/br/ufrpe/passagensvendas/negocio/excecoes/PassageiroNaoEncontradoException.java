package br.ufrpe.passagensvendas.negocio.excecoes;

public class PassageiroNaoEncontradoException extends Exception {
    public PassageiroNaoEncontradoException(String message) {
        super(message);
    }
}