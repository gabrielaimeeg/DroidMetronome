package com.droidfoundry.droidmetronome.control;

import android.app.IntentService;
import android.content.Intent;
import android.os.Looper;

import com.droidfoundry.droidmetronome.model.FiguraRitmica;
import com.droidfoundry.droidmetronome.model.TemplateSound;
import com.droidfoundry.droidmetronome.model.UserInterface;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by pedro on 12/05/15.
 * Executa o som durante determinando tempo
 */
public class Compasso extends IntentService {

    private long frequenciaBPM;
    private int tempoMinutos;
    private int batidasMaximo;

    private FiguraRitmica figuraRitmica;
    private TemplateSound templateSound;
    private SoundTimeLoop timer;

    /**
     * Construtor da classe compasso
     */
    public Compasso() {
        super("Compasso");
    }

    /**
     * Método executado ao iniciar o service
     */
    @Override
    public void onCreate() {
        super.onCreate();

        UserInterface userInterface = EventBus.getDefault().removeStickyEvent(UserInterface.class);

        // Configurações de som no geral
        FiguraRitmica figRit = userInterface.getFiguraRitmica();
        int min = userInterface.getTempoMinutos();
        long freq = userInterface.getFrequenciaBPM();
        int bat = userInterface.getQuantidadeBatidas();

        this.setSoundConfiguration(figRit, min, freq, bat);
        this.templateSound = userInterface.getSound();

        // Configuraçoes luz e vibração
        boolean vibrating = userInterface.isVibracao();
        boolean lighting = userInterface.isFlash();

        // Definindo configurações de hardware
        HardwareActions.getInstance().setHardwareConfiguration(vibrating, lighting, getApplicationContext());
        HardwareActions.getInstance().startConfiguration();
    }

    /**
     * Método que será executado ao startar o service
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        double frequenciaSegundos = this.conversorBPM(this.frequenciaBPM); // 2bps
        long delay = 1000 / (long)frequenciaSegundos; // 0.5s

        int tempoMiliSegundos = this.tempoMinutos * 60 * 1000; // 60000 milisegundos

        // Iniciando ciclo de batidas
        timer = new SoundTimeLoop(this.templateSound, tempoMiliSegundos, delay / this.figuraRitmica.getValue());
        timer.setBatidasMaximo(this.batidasMaximo);

        timer.setFiguraRitmica(this.figuraRitmica);

        timer.start();
        Looper.loop();

    }

    /**
     * Executado ao encerrar o service
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        this.stopMetronomo();
    }

    /**
     * Para o metronomo e encerra a Thread
     */
    public void stopMetronomo() {

        timer.cancel();
        stopSelf();
    }

    /**
     * Converte de BPM para BPS
     *
     * @param bpm - Frequencia definida pelo usuário.
     * @return Frequencia convertida para batidas por segundo.
     */
    private double conversorBPM(long bpm) {
        return bpm / (double) 60;
    }

    /**
     * Define a figura rítmica. Caso esteja fora do intervalo é definido um valor padrão.
     * @param figuraRitmica - Figura rítmica definido pelo usuário.
     * @param tempoMinutos - Tempo de duração do metronomo
     * @param frequenciaBPM - frequencia em bpm
     * @param batidas - quantidade de batidas máximo
     */
    public void setSoundConfiguration(FiguraRitmica figuraRitmica ,int tempoMinutos ,long frequenciaBPM ,int batidas){
        // Figura rítmica
        this.figuraRitmica = figuraRitmica;

        // Tempo em minutos
        if((tempoMinutos < 1)||(tempoMinutos > 15)){
            //Valor padrão caso esteja fora dos limites.
            this.tempoMinutos = 1;
        }else {
            this.tempoMinutos = tempoMinutos;
        }

        // Frequencia em BPM
        if((frequenciaBPM < 10)||(frequenciaBPM > 300)){
            //Valor padrão caso esteja fora dos limites.
            this.frequenciaBPM = 120;
        }else {
            this.frequenciaBPM = frequenciaBPM;
        }

        // Batidas
        if((batidas < 1)||(batidas > 16)){
            //Valor padrão caso esteja fora dos limites.
            this.batidasMaximo = 4;
        }else {
            this.batidasMaximo = batidas;
        }
    }

}
