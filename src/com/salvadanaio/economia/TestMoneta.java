package com.salvadanaio.economia;

/**
 * Classe contente il metodo main per il Testing della Classe Moneta
 *
 * @author Manuel Lusenti
 */
class TestMoneta {

    public static void main(String[] args){
        Moneta m1 = new Moneta(1);
        Moneta m2 = new Moneta(20);
        Moneta m3 = new Moneta(100);

        System.out.println(m1.getDettagli());
        System.out.println(m2.getDettagli());
        System.out.println(m3.getDettagli());


    }
}
