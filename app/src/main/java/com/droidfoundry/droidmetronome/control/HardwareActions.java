package com.droidfoundry.droidmetronome.control;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Vibrator;

/**
 * Created by pedro on 27/06/15.
 */
public class HardwareActions {

    private static HardwareActions ourInstance = new HardwareActions();

    private Vibrator vibrate;
    private Camera camera;
    private Context context;

    private boolean isVibrating;
    private boolean isLighting;
    private boolean hardwareSupported;

    private long delay;

    private HardwareActions() {}

    public static HardwareActions getInstance() {
        return ourInstance;
    }

    /**
     * Iniciando configuração de sistema do metronomo
     */
    public void startConfiguration(){
        // Configurações gerais
        this.vibrate = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        this.hardwareSupported = hardwareSupported();
    }

    /**
     * Define as configurações de hardware
     * @param vibrating - Define vibrações
     * @param lighting - Define luz
     * @param context - Contexto da aplicacao
     */
    public void setHardwareConfiguration(boolean vibrating ,boolean lighting ,Context context){
        this.isVibrating = vibrating;
        this.isLighting = lighting;
        this.context = context;

        this.hardwareSupported = this.hardwareSupported();
    }

    /**
     * Define o tempo da vibração
     * @param delay
     */
    public void setDelay(long delay) {
        this.delay = delay;
    }

    /**
     * Verifica se o hardware é compativel
     * @return
     */
    private boolean hardwareSupported(){
        PackageManager pm = context.getPackageManager();

        boolean switchFlash = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean switchCamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
        return switchFlash && switchCamera;
    }

    /**
     * Ativando vibração
     */
    public void activeVibration(){
        if(isVibrating)
            vibrate.vibrate(delay/10);
    }

    /**
     * Ativa a luz
     */
    public void activeLighting(){
        if((isLighting) && (hardwareSupported) && (camera == null)){

                new Thread(){

                    @Override
                    public void run() {
                        camera = Camera.open();
                        Camera.Parameters parameters = camera.getParameters();

                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        camera.startPreview();
                    }
                }.start();
            }
    }

    /**
     * Desativa a luz
     */
    public void desactiveLighting(){
        if((isLighting) && (hardwareSupported) && (camera != null)){

                new Thread(){

                    @Override
                    public void run() {
                        camera.release();
                        camera = null;
                    }
                }.start();  
        }
    }
}
