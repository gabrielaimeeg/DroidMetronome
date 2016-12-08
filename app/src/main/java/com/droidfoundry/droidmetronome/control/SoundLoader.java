package com.droidfoundry.droidmetronome.control;

import android.media.SoundPool;

/**
 * Created by pedro on 03/12/16.
 * Carrega os sons na memoria dinamicamente
 */
public class SoundLoader implements SoundPool.OnLoadCompleteListener{

    private boolean isReady;

    /**
     * Construtor basico do SoundLoader.
     */
    public SoundLoader(){
        this.isReady = false;
    }

    /**
     * Define o som do metrônomo com base no definido pelo usuário.
     * @param soundPool
     * @param sampleId
     * @param status
     */
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        this.isReady = true;
    }

    /**
     * Retorna o estado do som carregado.
     * @return true - caso carregado, false - caso contrario.
     */
    public boolean isReady() {
        return isReady;
    }
}
