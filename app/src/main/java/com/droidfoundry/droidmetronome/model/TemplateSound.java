package com.droidfoundry.droidmetronome.model;

import android.media.SoundPool;

/**
 * Created by pedro on 03/06/15.
 */
public interface TemplateSound {
    void prepareSoundAlto(SoundPool sound);
    void prepareSoundBaixo(SoundPool sound);
    void playSoundAlto();
    void playSoundBaixo();
    TemplateSound getSound();
    void stopSound();
}
