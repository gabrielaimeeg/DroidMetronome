package com.droidfoundry.droidmetronome.control;

/**
 * Created by pedro on 16/05/15.
 */

import android.content.Context;

import com.droidfoundry.droidmetronome.model.FiguraRitmica;
import com.droidfoundry.droidmetronome.model.Sound_8Bits;
import com.droidfoundry.droidmetronome.model.Sound_beep;
import com.droidfoundry.droidmetronome.model.Sound_hiHats;
import com.droidfoundry.droidmetronome.model.Sound_kickClap;
import com.droidfoundry.droidmetronome.model.Sound_rimShot;
import com.droidfoundry.droidmetronome.model.TemplateSound;


/**
 * Classe responsável por ligar o front-end ao back-end
 */
public class FrontConversor {

    private static FrontConversor instance;
    private TemplateSound sound;

    private long frequenciaBPM;
    private int quantidadeBatidas;
    private int tempoMinutos;

    private boolean vibracao;
    private boolean flash;

    private FiguraRitmica figuraRitmica;

    /**
     * Retorna a instancia interna do objeto
     * @return
     */
    public synchronized static FrontConversor getInstance(){
        if(instance == null){
            instance = new FrontConversor();
        }

        return(instance);
    }

    /**
     * Construtor privado do padrão singleton
     */
    private FrontConversor(){}

    /**
     * Define o som do metrônomo com base no definido pelo usuário.
     * @param idTipoSom - Id para escolha do som (caso não exista é atribuido um valor padrão).
     * @param context - Contexto aonde o som será exibido.
     */
    public void createSomById(int idTipoSom,Context context) {

        switch(idTipoSom){
            //Som 1:
            case 1:
                sound = new Sound_8Bits(context);
                break;

            //Som 2:
            case 2:
                sound = new Sound_hiHats(context);
                break;

            //Som 3:
            case 3:
                sound = new Sound_kickClap(context);
                break;

            //Som 4:
            case 4:
                sound = new Sound_rimShot(context);
                break;

            //Som 5:
            case 5:
                sound = new Sound_beep(context);
                break;

            //Som padrão
            default:
                sound = new Sound_8Bits(context);

        }
    }

    /**
     * Define a figura rítmica definido pelo usuário.
     * @param idFiguraRitmica - Id para escolha da figura ritmica (caso não exista é atribuido um valor padrão).
     */
    public void createFiguraRitmicaById(int idFiguraRitmica) {

        switch(idFiguraRitmica){

            case 1:
                figuraRitmica = FiguraRitmica.SemiBreve;
                break;

            case 2:
                figuraRitmica = FiguraRitmica.Minima;
                break;

            case 3:
                figuraRitmica = FiguraRitmica.SemiMinima;
                break;

            case 4:
                figuraRitmica = FiguraRitmica.Colcheia;
                break;

            case 5:
                figuraRitmica = FiguraRitmica.SemiColcheia;
                break;

            case 6:
                figuraRitmica = FiguraRitmica.Fusa;
                break;

            case 7:
                figuraRitmica = FiguraRitmica.SemiFusa;
                break;

            //Som padrão
            default:
                figuraRitmica = FiguraRitmica.SemiBreve;

        }
    }

    /**
     * Permição de vibrar durante a execução
     * @param vibracao - Ativado se verdadeiro ,desativado se falso
     */
    public void setVibracao(boolean vibracao) {
        this.vibracao = vibracao;
    }

    /**
     * Permição de ativar o flash durante a execução
     * @param flash - Ativado se verdadeiro ,desativado se falso
     */
    public void setFlash(boolean flash) {
        this.flash = flash;
    }

    /**
     * Define o tempo de duração em minutos. Caso esteja fora do intervalo é definido um valor padrão.
     * @param tempoMinutos - Tempo em minutos definido pelo usuário.
     */
    public void setTempoMinutos(int tempoMinutos) {
        if((tempoMinutos < 1)||(tempoMinutos > 15)){
            //Valor padrão caso esteja fora dos limites.
            this.tempoMinutos = 1;
        }else {
            this.tempoMinutos = tempoMinutos;
        }
    }

    /**
     * Define a frequencia em BPM's. Caso esteja fora do intervalo é definido um valor padrão.
     * @param frequenciaBPM - Frequencia em BPM definido pelo usuário.
     */
    public void setFrequenciaBPM(long frequenciaBPM) {
        if((frequenciaBPM < 10)||(frequenciaBPM > 300)){
            //Valor padrão caso esteja fora dos limites.
            this.frequenciaBPM = 120;
        }else {
            this.frequenciaBPM = frequenciaBPM;
        }
    }

    /**
     * Define a quantidade máxima de batidas. Caso esteja fora do intervalo é definido um valor padrão.
     * @param quantidadeBatidas - Quantidade máxima de batidas definido pelo usuário.
     */
    public void setQuantidadeBatidas(int quantidadeBatidas) {
        if((quantidadeBatidas < 1)||(quantidadeBatidas > 16)){
            //Valor padrão caso esteja fora dos limites.
            this.quantidadeBatidas = 4;
        }else {
            this.quantidadeBatidas = quantidadeBatidas;
        }
    }

    /**
     * Retorna o som selecionado
     * @return
     */
    public TemplateSound getSound() {
        return sound;
    }

    /**
     * Retorna a frequencia selecionada
     * @return
     */
    public long getFrequenciaBPM() {
        return frequenciaBPM;
    }

    /**
     * Retorna a quantidade de batidas selecionada
     * @return
     */
    public int getQuantidadeBatidas() {
        return quantidadeBatidas;
    }

    /**
     * retorna o tempo em minutos selecionado
     * @return
     */
    public int getTempoMinutos() {
        return tempoMinutos;
    }

    /**
     * Modo vibratorio ativo?
     * @return
     */
    public boolean isVibracao() {
        return vibracao;
    }

    /**
     * Modo luminoso ativo?
     * @return
     */
    public boolean isFlash() {
        return flash;
    }

    /**
     * Retorna a figura rítmica selecionada
     * @return
     */
    public FiguraRitmica getFiguraRitmica() {
        return figuraRitmica;
    }
}
