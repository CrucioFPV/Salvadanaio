package com.salvadanaio.economia;

import static com.salvadanaio.grammatica.GrammaticaMonete.getPluraleCentesimi;

/**
 * Astrae il concetto di moneta in euro
 *
 * @author Manuel Lusenti
 */
public class Moneta {
    private int valore;
    private boolean isEuro = false;


    public Moneta(int valore){
        this.setValore(valore);
        trasformaInEuro();
        System.out.println("Creata una " + this.getDettagli());
    }


    /*
    ***********************
    *                     *
    *       METODI        *
    *                     *
    * *********************
    */

    /*
    ***************************
    *                         *
    * METODI INTERNI CLASSE   *
    *                         *
    ***************************
    */

    /** Metodo richiamato all'interno del costruttore che in caso rilevi la variabile valore
       superiore o uguale al valore di 100, divide il valore per 100 ricavando il valore in euro e setta
       la variabile isEuro a true
     */
    private void trasformaInEuro(){
        if (this.getValore() >= 100) {
            this.setValore((this.getValore()) / 100);
            this.setIsEuro();
            //  System.out.println("Trasformazione in euro: " + this.getValore());
        }
    }

    /*
    ****************************
    *                          *
    *      METODI PUBBLICI     *
    *                          *
    ****************************
    */

    public String getDettagli(){
        String dettagli = "moneta da " + this.getValore() + " ";

        if(isEuro)
        {
            dettagli += "Euro";
        }
        else {
            dettagli += getPluraleCentesimi(this);
        }

        return dettagli;
    }

    /*
     *****************************
     * ACCESSOR & MUTATOR METHOD *
     *****************************
     */

    public int getValore(){
        if(this.valore > 0) {
            return this.valore;
        }
        else{
            System.out.println("Impossibile recuperare valore moneta");
            return 0;
        }
    }

    private void setValore(int valore){
        if(valore>0){
            this.valore = valore;

            //Linea sucessiva per DEBUG
            //System.out.println("Impostato valore moneta a  " + this.getValore());
        }
        else
        {
            System.out.println("Inserire un valore maggiore di 0 per creare la moneta!");
        }
    }

    /**
     * Metodo Accessor
     * @return
     * Ritorna true se la moneta è in euro, False se è in centesimi
     */
    public boolean isEuro() {
        return isEuro;
    }

    private void setIsEuro() {
        isEuro = true;
    }
}
