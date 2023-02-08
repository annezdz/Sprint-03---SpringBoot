package br.com.uol.exceptions;

public class SQLConstraintException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SQLConstraintException(String msg) {
        super(msg);
    }
}