
package com.salvadanaio;

import com.salvadanaio.economia.Moneta;
import com.salvadanaio.exception.MonetaNotFoundException;
import com.salvadanaio.exception.SalvadanaioPienoException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che astrae il concetto di Salvadanaio permettendo l'aggiunta di oggetti Moneta o la loro rimozione a Runtime
 *
 * @author Manuel Lusenti
 */
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
class Salvadanaio {


    private final int LIMITE_MONETE = 10;
    private List<Moneta> monete = new ArrayList<>();

    public Salvadanaio() {
        System.out.println("Creato portamonete vuoto!");
    }


    /**
     * Crea un oggetto Salvadanaio contenente delle monete i cui valori sono contenuti nel varargs valori
     *
     * @param valori Un varargs di valori Moneta
     */
    public Salvadanaio(int... valori) {
        try {
            //TODO: rendere il lancio eccezione riutilizzabile anche per l'altro costruttore
            int numeroMonete = valori.length;
            for (int index = 0; index < numeroMonete; index++) {
                this.LaunchExceptionSalvadanaioPieno();
                getMonete().add(new Moneta(valori[index]));
            }
        } catch (SalvadanaioPienoException exc) {
            System.out.println(exc.getMessage());
        } catch (NullPointerException exc) {
            System.out.println("Porta monete creato vuoto!");
        }
    }


    public Salvadanaio(Moneta... monete) {


        try {
            int numeroMonete = monete.length;
            for (int index = 0; index < numeroMonete; index++) {
                LaunchExceptionSalvadanaioPieno();
                getMonete().add(monete[index]);
            }
        } catch (SalvadanaioPienoException exc) {
            System.out.println(exc.getMessage());
        } catch (NullPointerException exc) {
            System.out.println("Porta monete creato vuoto!");
        }
    }

    private static boolean isSalvadanaioPieno(@NotNull Salvadanaio salvadanaio) {
        try {
            return salvadanaio.getMonete().size() == salvadanaio.LIMITE_MONETE;

        } catch (NullPointerException exc) {
            System.out.println("Salvadanaio impostato a null! Return True");
            return true;
        }

    }


    /**
     * Metodo statico che controlla se il salvadanaio è completamente vuoto
     *
     * @param salvadanaio Salvadanaio che verrò controllato
     * @return True se il salvadanaio è completamente vuoto, False se contiene almeno una moneta
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isSalvadanaioVuoto(@NotNull Salvadanaio salvadanaio) {

        return salvadanaio.getMonete().isEmpty();
    }

    /**
     * Metodo che aggiunge al salvadanaio una moneta. Se il salvadanaio risulta pieno, stampa un messaggio d'errore
     *lanciando l'eccezione SalvadanaioPienoException
     *
     * @param moneta Moneta da aggiungere al salvadanaio
     */
    public void aggiungi(Moneta moneta) {
        try {
            if (Salvadanaio.isSalvadanaioPieno(this)) {
                throw new SalvadanaioPienoException("Salvadanaio Pieno! Moneta non aggiunta!");
            } else {
                this.getMonete().set(this.getSpazioLibero(), moneta);
                System.out.println("Moneta aggiunta!");
            }
        } catch (SalvadanaioPienoException exc) {
            System.out.println(exc.getMessage());
        } catch (NullPointerException exc) {
            System.out.println("Passata una moneta null...moneta non aggiunta");
            exc.printStackTrace();
        }
    }

    /**
     * Metodo che restituisce l'indice dell'array monete vuoto
     *
     * @return Restituisce lo spazio vuoto nel salvadanaio
     */
    private int getSpazioLibero() {
        return this.getMonete().indexOf(null);
    }

    /**
     * Metodo che stampa a video le monete contenute nel salvadanaio
     */
    public void stato() {
        int numeroMoneta = 0;

        if (!Salvadanaio.isSalvadanaioVuoto(this)) {
            for (int index = 0; index < this.getMonete().size(); index++) {
                if (this.getMonete().get(index) != null) {
                    numeroMoneta++;
                    System.out.println(numeroMoneta + " - " +
                            this.getMonete().get(index).getDettagli());
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
            try {
                if (isEuro) rimuoviEuro(valore);
                else rimuoviCentesimi(valore);
            } catch (MonetaNotFoundException exc) {
                System.out.println(exc);
            }
        } else {
            System.out.println("Impossibile rimuovere monete: Salvadanaio Vuoto!");
        }

    }

    @Contract("false -> fail")
    private void ErroreNonTrovato(boolean found) throws MonetaNotFoundException {
        if (!found) {
            throw new MonetaNotFoundException("Moneta non trovata");
        }
    }

    private void rimuoviCentesimi(int valore) throws MonetaNotFoundException {
        boolean found = false;
        for (int index = 0; index < this.getMonete().size(); index++) {
            if ((this.getMonete().get(index) != null) &&
                    (this.getMonete().get(index).getValore() == valore &&
                            !this.getMonete().get(index).isEuro())) {

                found = true;
                this.getMonete().remove(index);
                break;
            }
        }
        //Se non trovata stampa messaggio d'errore
        this.ErroreNonTrovato(found);
    }

    private void rimuoviEuro(int valore) throws MonetaNotFoundException {
        boolean found = false;
        for (int index = 0; index < this.getMonete().size(); index++) {
            if ((this.getMonete().get(index) != null) &&
                    (this.getMonete().get(index).getValore() == valore &&
                            this.getMonete().get(index).isEuro())) {

                found = true;
                this.getMonete().remove(index);
                break;
            }
        }
        //Se non trovata stampa messaggio d'errore
        this.ErroreNonTrovato(found);
    }

    private int getLimiteMonete() {
        return LIMITE_MONETE;
    }

    private List<Moneta> getMonete() {
        return monete;
    }

    private void LaunchExceptionSalvadanaioPieno() throws SalvadanaioPienoException {
        if (isSalvadanaioPieno(this)) {
            throw new SalvadanaioPienoException("Sono state inserite solo le prime " +
                    this.getLimiteMonete() + " monete");
        }
    }
}
