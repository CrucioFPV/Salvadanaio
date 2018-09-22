package com.salvadanaio.exception;

public class MonetaNotFoundException extends Exception {

    public MonetaNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "Moneta non trovata!";
    }
}
