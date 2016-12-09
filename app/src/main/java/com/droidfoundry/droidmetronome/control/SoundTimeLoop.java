package com.droidfoundry.droidmetronome.control;

import android.os.CountDownTimer;
import android.os.Looper;

import com.droidfoundry.droidmetronome.exceptions.SoundException;
import com.droidfoundry.droidmetronome.model.FiguraRitmica;
import com.droidfoundry.droidmetronome.model.TemplateSound;

/**
 * Created by pedro on 12/05/15.
 */
public class SoundTimeLoop extends CountDownTimer {

    private TemplateSound templateSound;
    private FiguraRitmica figuraRitmica;

    private boolean starting = false;

    private int batidasMaximo;
    private int batidasAtual = 0;

    /**
     * Construtor do timer
     *
     * @param templateSound     - som a ser tocado
     * @param millisInFuture    - tempo máximo
     * @param countDownInterval - tempo entre toques
     */
    public SoundTimeLoop(TemplateSound templateSound, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

        // Tempo de intervalo
        HardwareActions.getInstance().setDelay(countDownInterval);

        // Som a ser tocado
        this.templateSound = templateSound;

    }

    /**
     * Método para cada tick
     *
     * @param l
     */
    @Override
    public void onTick(long l) {
        try {
            // Ativando luz
            HardwareActions.getInstance().activeLighting();


            if ((figuraRitmica.getValue() > 1) && (!this.starting)) {
                batidasMaximo = batidasMaximo * this.figuraRitmica.getValue() - (this.figuraRitmica.getValue() - 1);
                this.starting = true;
            }

            if (batidasAtual < batidasMaximo) {

                // Modo vibratório
                HardwareActions.getInstance().activeVibration();
                templateSound.playSoundAlto();

                batidasAtual++;
            } else {

                // Modo vibratório
                HardwareActions.getInstance().activeVibration();
                templateSound.playSoundBaixo();

                batidasAtual = 1;
            }

            // Desativar flash
            HardwareActions.getInstance().desactiveLighting();


        } catch (RuntimeException erro) {
            throw new SoundException("Erro na execução do tick");
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
     *
     * @param figuraRitmica
     */
    public void setFiguraRitmica(FiguraRitmica figuraRitmica) {
        this.figuraRitmica = figuraRitmica;
    }

    /**
     * Método para adicionar as batidas máximo
     *
     * @param batidasMaximo
     */
    public void setBatidasMaximo(int batidasMaximo) {
        this.batidasMaximo = batidasMaximo;
    }

}
