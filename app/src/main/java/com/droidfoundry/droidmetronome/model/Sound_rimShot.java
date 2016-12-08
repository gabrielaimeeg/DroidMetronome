package com.droidfoundry.droidmetronome.model;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.droidfoundry.droidmetronome.R;

/**
 * Created by pedro on 03/06/15.
 */
public class Sound_rimShot implements  TemplateSound{

    private int somAlto;
    private int somBaixo;

    private SoundPool sound;
    private int idSom;

    private boolean readyAlto = false;
    private boolean readyBaixo = false;

    public Sound_rimShot(Context context){

        this.sound = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

        this.somAlto = sound.load(context, R.raw.rim_shot_01,1);
        this.prepareSoundAlto(sound);

        this.somBaixo = sound.load(context,R.raw.rim_shot_02,1);
        //this.prepareSoundBaixo(sound);
    }

    @Override
    public void prepareSoundAlto(SoundPool sound){
        sound.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){

            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                readyAlto = true;
            }

        });
    }

    @Override
    public void prepareSoundBaixo(SoundPool sound){
        sound.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                readyBaixo = true;
            }

        });
    }

    @Override
    public void playSoundAlto() {
        if(readyAlto)
            idSom = this.sound.play(somAlto, 1, 1, 1, 0, 1);
    }

    @Override
    public void playSoundBaixo(){
        //if(readyBaixo)
            idSom = this.sound.play(somBaixo, 1, 1, 1, 0, 1);
    }

    @Override
    public TemplateSound getSound() {
        return this;
    }

    @Override
    public void stopSound() {
        this.sound.stop(this.idSom);
        this.sound.release();
    }
}

