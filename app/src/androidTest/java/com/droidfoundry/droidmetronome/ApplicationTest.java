package com.droidfoundry.droidmetronome;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.droidfoundry.droidmetronome.model.Compasso;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

        /*testCase1bpm();
        testCase2bpm();
        testCase3bpm();
        testCase4bpm();
        testCase5bpm();

        testCase1beat();
        testCase2beat();
        testCase3beat();
        testCase4beat();
        testCase5beat();
        testCase6beat();*/
    }
    public Compasso compasso = new Compasso();

   /* public void testCase1bpm(){
        int bpm = 9;
       compasso.setFrequenciaBPM(bpm);

    }
    public void testCase2bpm(){
        int bpm = 10;
        compasso.setFrequenciaBPM(bpm);

    }
    public void testCase3bpm(){
        int bpm = 120;
        compasso.setFrequenciaBPM(bpm);

    }
    public void testCase4bpm(){
        int bpm = 300;
        compasso.setFrequenciaBPM(bpm);

    }
    public void testCase5bpm(){
        int bpm = 301;
        compasso.setFrequenciaBPM(bpm);

    }


    public void testCase1beat(){
        int beat = -5;
        compasso.setQuantidadeBatidas(beat);
    }
    public void testCase2beat(){
        int beat = 0;
        compasso.setQuantidadeBatidas(beat);
    }
    public void testCase3beat(){
        int beat = 1;
        compasso.setQuantidadeBatidas(beat);
    }
    public void testCase4beat(){
        int beat = 4;
        compasso.setQuantidadeBatidas(beat);
    }
    public void testCase5beat(){
        int beat = 16;
        compasso.setQuantidadeBatidas(beat);
    }
    public void testCase6beat(){
        int beat = 17;
        compasso.setQuantidadeBatidas(beat);
    }*/
}