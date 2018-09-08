package com.salvadanaio.grammatica;

import com.salvadanaio.economia.Moneta;
import org.jetbrains.annotations.NotNull;

/**
 *Classe dedicata a fornire dei metodi statici per l'elaborazione della grammatica per la classe e gli oggetti Moneta
 *
 * @author Manuel Lusenti
 */
public class GrammaticaMonete {

    private static boolean checkPluraleCentesimi(@NotNull Moneta moneta){
        return moneta.getValore() > 1;
    }

    @NotNull
    public static String getPluraleCentesimi(@NotNull Moneta moneta){
        if(GrammaticaMonete.checkPluraleCentesimi(moneta)){
            return "centesimi";
        }
        else{
            return "centesimo";
        }
    }
}
