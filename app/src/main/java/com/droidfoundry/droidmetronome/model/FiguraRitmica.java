package com.droidfoundry.droidmetronome.model;

/**
 * Created by pedro on 16/06/15.
 */
public enum FiguraRitmica {

    SEMIBREVE(1),
    MINIMA(2),
    SEMIMINIMA(3),
    COLCHEIA(4),
    SEMICOLCHEIA(5),
    FUSA(6),
    SEMIFUSA(7);


    private final int valor;

    /**
     * Construtor de figura ritmica
     * @param valor o valor da figura ritmica
     */
    FiguraRitmica(int valor){
        this.valor = valor;
    }

    /**
     * retorna o valor da figura ritmica
     * @return figura ritmica
     */
    public int getValue(){
        return valor;
    }
}
