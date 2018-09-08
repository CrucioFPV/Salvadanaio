
package com.salvadanaio;

import com.salvadanaio.economia.Moneta;
import org.jetbrains.annotations.NotNull;

/**
 * Classe che astrae il concetto di Salvadanaio permettendo l'aggiunta di oggetti Moneta o la loro rimozione a Runtime
 *
 * @author Manuel Lusenti
 */
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
class Salvadanaio {

    @SuppressWarnings("FieldCanBeLocal")
    private final int LIMITE_MONETE = 10;
    private final Moneta[] monete = new Moneta[this.getLimiteMonete()];

    /**
     * Crea un oggetto Salvadanaio contenente delle monete i cui valori sono contenuti nel varargs valori
     *
     * @param valori Un varargs di valori Moneta
     */
    public Salvadanaio(int... valori) {
        int numeroMonete = valori.length;

        if (numeroMonete > 0) {
            for (int index = 0; index < numeroMonete; index++) {
                if (index >= this.getLimiteMonete()) {
                    System.out.println("Sono state inserite sole le prime " +
                            this.getLimiteMonete() + " monete");
                    break;
                }
                getMonete()[index] = new Moneta(valori[index]);
            }
        }
    }

    public Salvadanaio(Moneta... monete) {
        int numeroMonete = monete.length;

        if (numeroMonete > 0) {
            for (int index = 0; index < numeroMonete; index++) {
                if (index >= this.getLimiteMonete()) {
                    System.out.println("Sono state inserite sole le prime " +
                            this.getLimiteMonete() + " monete");
                    break;
                }
                getMonete()[index] = monete[index];
            }
        }
    }

    private static boolean isSalvadanaioPieno(@NotNull Salvadanaio salvadanaio) {
        for (int index = 0; index < salvadanaio.getMonete().length; index++) {
            if (salvadanaio.getMonete()[index] == null) {
                return false;
            }
        }
        return true;
    }


    /**
     * Metodo statico che controlla se il salvadanaio è completamente vuoto
     *
     * @param salvadanaio Salvadanaio che verrò controllato
     * @return True se il salvadanaio è completamente vuoto, False se contiene almeno una moneta
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isSalvadanaioVuoto(@NotNull Salvadanaio salvadanaio) {
        for (int index = 0; index < salvadanaio.getMonete().length; index++) {
            if (salvadanaio.getMonete()[index] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo che aggiunge al salvadanaio una moneta. Se il salvadanaio risulta pieno, stampa un messaggio d'errore
     *
     * @param moneta Moneta da aggiungere al salvadanaio
     */
    public void aggiungi(Moneta moneta) {
        if (Salvadanaio.isSalvadanaioPieno(this)) {
            System.out.println("Salvadanaio Pieno! Moneta non aggiunta!");
        } else {
            this.getMonete()[this.getSpazioLibero()] = moneta;
            System.out.println("Moneta aggiunta!");
        }
    }

    /**
     * Metodo che restituisce l'indice dell'array monete vuoto
     *
     * @return Restituisce lo spazio vuoto nel salvadanaio
     */
    private int getSpazioLibero() {
        int index = -1;

        if (!(Salvadanaio.isSalvadanaioPieno(this))) {

            for (index = 0; index < this.getMonete().length; index++) {
                if (this.getMonete()[index] == null) {
                    break;
                }
            }
        }
        return index;
    }

    /**
     * Metodo che stampa a video le monete contenute nel salvadanaio
     */
    public void stato() {
        int numeroMoneta = 0;

        if (!Salvadanaio.isSalvadanaioVuoto(this)) {
            for (int index = 0; index < this.getMonete().length; index++) {
                if (this.getMonete()[index] != null) {
                    numeroMoneta++;
                    System.out.println(numeroMoneta + " - " +
                            this.getMonete()[index].getDettagli());
                }
            }
        } else {
            System.out.println("Salvadanaio Vuoto!");
        }
    }

    /**
     * Metodo che cerca e rimuove dal salvadanaio una moneta
     *
     * @param valore valore della moneta da rimuovere
     * @param isEuro Inserire True se la moneta da cercare è in euro, False se la moneta è in centesimi di euro
     */
    public void rimuovi(int valore, boolean isEuro) {

        if (!Salvadanaio.isSalvadanaioVuoto(this)) {
            if (isEuro) {
                rimuoviEuro(valore);
            } else {
                rimuoviCentesimi(valore);
            }
        } else {
            System.out.println("Impossibile rimuovere monete: Salvadanaio Vuoto!");
        }

    }

    private void ErroreNonTrovato(boolean found) {
        if (!found) {
            System.out.println("Moneta non trovata");
        }
    }

    private void rimuoviCentesimi(int valore) {
        boolean found = false;
        for (int index = 0; index < this.getMonete().length; index++) {
            if ((this.getMonete()[index] != null) &&
                    (this.getMonete()[index].getValore() == valore &&
                            !this.getMonete()[index].isEuro())) {

                found = true;
                this.getMonete()[index] = null;
                break;
            }
        }
        //Se non trovata stampa messaggio d'errore
        this.ErroreNonTrovato(found);
    }

    private void rimuoviEuro(int valore) {
        boolean found = false;
        for (int index = 0; index < this.getMonete().length; index++) {
            if ((this.getMonete()[index] != null) &&
                    (this.getMonete()[index].getValore() == valore &&
                            this.getMonete()[index].isEuro())) {

                found = true;
                this.getMonete()[index] = null;
                break;
            }
        }
        //Se non trovata stampa messaggio d'errore
        this.ErroreNonTrovato(found);
    }

    private int getLimiteMonete() {
        return LIMITE_MONETE;
    }

    private Moneta[] getMonete() {
        return monete;
    }
}
