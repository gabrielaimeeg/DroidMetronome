package com.droidfoundry.droidmetronome.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.ToggleButton;

import com.droidfoundry.droidmetronome.R;
import com.droidfoundry.droidmetronome.control.Compasso;
import com.droidfoundry.droidmetronome.model.UserInterface;

import org.greenrobot.eventbus.EventBus;


public class MainActivity extends ActionBarActivity {

    private boolean inExecution;

    private int idSom = 1;
    private int idBit8 = 1;
    private int idHihats = 2;
    private int idKickClap = 3;
    private int idRimshot = 4;
    private int idBeep = 5;

    private NumberPicker npBPM;
    private NumberPicker npQntBatidas;
    private NumberPicker npValorBase;
    private NumberPicker npTimer;
    private ToggleButton buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mountInterface();

        inExecution = false;
    }

    /**
     * Retorna o id do som escolhido pelo usuário
     */
    public int getIdSom() {
        return idSom;
    }

    /**
     * Seta o id do som escolhido pelo usuário como Bit8.
     * @param view
     */
    public void setIdBit8(View view) {
        this.idSom = idBit8;
    }

    /**
     * Seta o id do som escolhido pelo usuário como idHihats.
     * @param view
     */
    public void setIdHihats(View view) {
        this.idSom = idHihats;
    }

    /**
     * Seta o id do som escolhido pelo usuário como kickclap.
     * @param view
     */
    public void setIdKickClap(View view) {
        this.idSom = idKickClap;
    }

    /**
     * Seta o id do som escolhido pelo usuário como Rimshot.
     * @param view
     */
    public void setIdRimshot(View view) {
        this.idSom = idRimshot;
    }

    /**
     * Seta o id do som escolhido pelo usuário como Beep.
     * @param view
     */
    public void setIdBeep(View view) {
        this.idSom = idBeep;
    }


    private void mountInterface(){
        npBPM = (NumberPicker) findViewById(R.id.bpm);
        npBPM.setMinValue(10);
        npBPM.setMaxValue(300);
        npBPM.setValue(120); //Padrão

        npTimer = (NumberPicker) findViewById(R.id.timer);
        npTimer.setMinValue(1);
        npTimer.setMaxValue(15);
        npTimer.setValue(1); //Padrão

        npQntBatidas = (NumberPicker) findViewById(R.id.qntBatidas);
        npQntBatidas.setMinValue(1);
        npQntBatidas.setMaxValue(16);
        npQntBatidas.setValue(4); // Padrão

        npValorBase = (NumberPicker) findViewById(R.id.valorBase);
        npValorBase.setMinValue(1);

        npValorBase.setMaxValue(6);
        npValorBase.setValue(1);

        buttonPlay = (ToggleButton)findViewById(R.id.floatingButtonPlay);

    }

    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            stopService(new Intent(this, Compasso.class)); // encerrar service executar
        } else {
            executar();
        }
    }

    /**
     * Prepara e executa o metronomo.
     * @param
     */
    public void executar(){

        UserInterface userInterface = new UserInterface();

        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Boolean vibracao = sharedPrefs.getBoolean(
                getString(R.string.pref_vibracao_key), Boolean.parseBoolean(getString(R.string.pref_vibracao_default)));
        Boolean flash = sharedPrefs.getBoolean(
                getString(R.string.pref_flash_key), Boolean.parseBoolean(getString(R.string.pref_flash_default)));

        userInterface.setVibracao(vibracao);
        userInterface.setFlash(flash);

        userInterface.setTempoMinutos(npTimer.getValue());
        userInterface.setFrequenciaBPM(npBPM.getValue());
        userInterface.setQuantidadeBatidas(npQntBatidas.getValue());

        userInterface.createSomById(getIdSom(), this);
        userInterface.createFiguraRitmicaById(npValorBase.getValue());

        EventBus.getDefault().postSticky(userInterface);
        startService(new Intent(this,Compasso.class)); // executar
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, ConfiguracoesActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if(inExecution){
            stopService(new Intent(this, Compasso.class)); // encerrar service executar
            inExecution = false;
        }

        super.onDestroy();
    }
}
