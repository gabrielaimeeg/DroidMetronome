package com.droidfoundry.droidmetronome.control;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.os.Looper;
import android.os.Vibrator;

import com.droidfoundry.droidmetronome.model.FiguraRitmica;
import com.droidfoundry.droidmetronome.model.TemplateSound;

/**
 * Created by pedro on 12/05/15.
 */
public class SoundTimeLoop extends CountDownTimer{

    private TemplateSound som;
    private FiguraRitmica figuraRitmica;

    private boolean starting = false;

    private int batidasMaximo;
    private int batidasAtual = 0;

    /**
     * Construtor do timer
     * @param millisInFuture - tempo máximo
     * @param countDownInterval - tempo entre toques
     */
    public SoundTimeLoop(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

        // Tempo de intervalo
        HardwareActions.getInstance().setDelay(countDownInterval);

        // Som a ser tocado
        TemplateSound som = FrontConversor.getInstance().getSound();
        this.som = som;

    }

    /**
     * Método para cada tick
     * @param l
     */
    @Override
    public void onTick(long l){
        try {
            // Ativando luz
            HardwareActions.getInstance().activeLighting();


            if((figuraRitmica.getValue() >1 ) && (!this.starting)){
                batidasMaximo = batidasMaximo * this.figuraRitmica.getValue() - (this.figuraRitmica.getValue() - 1);
                this.starting = true;
            }

            if(batidasAtual < batidasMaximo) {

                // Modo vibratório
                HardwareActions.getInstance().activeVibration();
                som.playSoundAlto();

                batidasAtual++;
            }else{

                // Modo vibratório
                HardwareActions.getInstance().activeVibration();
                som.playSoundBaixo();

                batidasAtual = 1;
            }

            // Desativar flash
            HardwareActions.getInstance().desactiveLighting();


        }catch(Exception erro) {
            erro.printStackTrace();
        } finally {
            // Desativar flash
            HardwareActions.getInstance().desactiveLighting();
        }

    }

    /**
     * Método executado ao encerrar o timer
     */
    @Override
    public void onFinish() {
        Looper.myLooper().quit();
    }

    /**
     * Método que define uma figura ritmica para o contador
     * @param figuraRitmica
     */
    public void setFiguraRitmica(FiguraRitmica figuraRitmica){
        this.figuraRitmica = figuraRitmica;
    }

    /**
     * Método para adicionar as batidas máximo
     * @param batidasMaximo
     */
    public void setBatidasMaximo(int batidasMaximo){
        this.batidasMaximo = batidasMaximo;
    }

}