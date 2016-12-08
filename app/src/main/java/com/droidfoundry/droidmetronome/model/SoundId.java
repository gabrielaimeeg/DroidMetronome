package com.droidfoundry.droidmetronome.model;

import com.droidfoundry.droidmetronome.R;

/**
 * Created by pedro on 03/12/16.
 */

public enum SoundId {

    BITS_ALTO(R.raw.bit8_02),
    BITS_BAIXO(R.raw.bit8_01),
    BEEP_ALTO(R.raw.beep_2),
    BEEP_BAIXO(R.raw.beep_1),
    HIHATS_ALTO(R.raw.hihats_02),
    HIHATS_BAIXO(R.raw.hihats_01),
    KICKCLAP_ALTO(R.raw.kick_clap_02),
    KICKCLAP_BAIXO(R.raw.kick_clap_01),
    RIMSHOT_ALTO(R.raw.rim_shot_01),
    RIMSHOT_BAIXO(R.raw.rim_shot_02);


    private final int valor;

    /**
     * Construtor de SoundId
     * @param valor o identificador do som
     */
    SoundId(int valor){
        this.valor = valor;
    }

    /**
     * Retorna o identificador do som
     * @return o identificador do som
     */
    public int getValue(){
        return valor;
    }
}
