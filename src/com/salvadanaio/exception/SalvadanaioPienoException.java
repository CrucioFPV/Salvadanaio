package com.salvadanaio.exception;

public class SalvadanaioPienoException extends Exception {

    public SalvadanaioPienoException( String message ) {
        super(message);
    }

    public String toString() {
        return "Superato limite monete del salvadanaio!";
    }
}
