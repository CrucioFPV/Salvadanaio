package com.salvadanaio;

import com.salvadanaio.economia.Moneta;

class TestSalvadanaio {

    public static void main(String[] args) {

        Salvadanaio salvadanaio = new Salvadanaio(100, 1, 2, 2, 3, 4);
        Moneta moneta1 = new Moneta(501);
        salvadanaio.aggiungi(moneta1);
        System.out.println("--------------------");
        salvadanaio.stato();
        salvadanaio.rimuovi(100, true);
        salvadanaio.rimuovi(1, false);
        System.out.println("--------------------");
        salvadanaio.stato();
        Salvadanaio salvadanaio2 = new Salvadanaio(moneta1);
        System.out.println("--------------------");
        salvadanaio2.stato();
    }
}
