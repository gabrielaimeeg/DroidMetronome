package com.droidfoundry.droidmetronome.model;

/**
 * Created by pedro on 16/06/15.
 */
public enum FiguraRitmica {

    SemiBreve(1),
    Minima(2),
    SemiMinima(3),
    Colcheia(4),
    SemiColcheia(5),
    Fusa(6),
    SemiFusa(7);


    private final int valor;

    /**
     * Construtor de figura ritmica
     * @param valor
     */
    FiguraRitmica(int valor){
        this.valor = valor;
    }

    /**
     * retorna o valor da figura ritmica
     * @return
     */
    public int getValue(){
        return(valor);
    }
}
