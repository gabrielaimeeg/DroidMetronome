package com.droidfoundry.droidmetronome.model;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.droidfoundry.droidmetronome.control.SoundLoader;

/**
 * Created by pedro on 03/06/15.
 * Abstrai e prepara os sons a serem executados pelo metronomo.
 */
public class TemplateSound{

    private int somAlto;
    private int somBaixo;

    private SoundPool sound;
    private int idSom;

    private boolean readySound = false;

    /**
     * Inicia o carregamento dos sons na memoria
     * @param context contexto da aplicacao
     * @param somAlto identificador do som a ser executado
     * @param somBaixo identificador do som a ser executado
     */
    public TemplateSound(Context context, SoundId somAlto, SoundId somBaixo){

        this.sound = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

        this.somAlto = sound.load(context, somAlto.getValue(),1);
        this.somBaixo = sound.load(context,somBaixo.getValue(),1);

        this.readySound = this.prepareSound(sound);
    }

    /**
     * Inicia o carregamento do som na memoria
     * @param sound som a ser carregado
     * @return o estado do som
     */
    private boolean prepareSound(SoundPool sound){

        SoundLoader soundLoader = new SoundLoader();
        sound.setOnLoadCompleteListener(soundLoader);
        return soundLoader.isReady();
    }

    /**
     * Executa o som alto
     */
    public void playSoundAlto() {
        if(readySound)
            idSom = this.sound.play(somAlto, 1, 1, 1, 0, 1);
    }

    /**
     * Executa o som baixo
     */
    public void playSoundBaixo(){
        if(readySound)
            idSom = this.sound.play(somBaixo, 1, 1, 1, 0, 1);
    }

    /**
     * Para os sons
     */
    public void stopSound() {
        this.sound.stop(this.idSom);
        this.sound.release();
    }
}

