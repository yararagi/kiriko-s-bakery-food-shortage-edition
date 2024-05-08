package gioco.model;

import java.lang.Thread.State;
import java.util.ArrayList;

/**
 * modello interagiscie con i modelli: Kiriko, Bancone, Quest, MyGate e vari Consumer.
 * e serve per farli interagire in maniera correta tra di loro
 */

public class ModelGioco {
    private Kiriko kiriko;
    private Bancone bancone;
    private Quest quest;
    private short nLivello;
    private ArrayList<Consumer> consumers;
    private MyGate lock; 
    private int punteggio;
    private byte nConsumers;

    public ModelGioco(MyGate lock){
        punteggio=0;
        nLivello=1;
        bancone= new Bancone();
        quest= new Quest(nLivello);
        kiriko= new Kiriko(quest, bancone);
        consumers= new ArrayList<>();
        this.lock= lock;
        nConsumers=3;
        for(byte i=0; i<nConsumers;i++){
            consumers.add(new Consumer(bancone, lock));
        }
    }

    public void preparaProssimoLivello(){
        nLivello+=1;
        quest= new Quest((short)(nLivello+1));
        kiriko= new Kiriko(quest, bancone);
    }

    public void startPartita() {
        if(kiriko.getState()==State.NEW){
            kiriko.start();
        }
        if(nLivello%3==0){
            consumers.add(new Consumer(bancone, lock));
            nConsumers++;
        }
        for(byte i=0; i<nConsumers;i++){
            if(consumers.get(i).getState()==State.NEW){
                consumers.get(i).start();
            }        
        }
    }
    /**
     * in base al tipo di pane che viene inserito il metodo restituisci se è riuscito a prenderlo
     * @param tipo
     * @return se è riuscito a prenderlo o no
     */
    public boolean prendiPane(TipoPane tipo) {
        return bancone.prendiPane(tipo);
    }
    /**
     * ritorna true se il giocatore ha finito e folse se non ah finito la quest
     * @return ritorna il risultato
     */
    public boolean isQuestCompleted(){
        return quest.isQuestCompleted();
    }
    /**
     * serve al programma per ricevere quante baguette sono disponibili
     * @return ritorna il numero di baguette disponibili
     */
    public int getNumBaguetteDisponinbili() {
        return bancone.getNumBaguetteDisponinbili();
    }
    /**
     * serve al programma per ricevere quante dunat sono disponibili
     * @return ritorna il numero di dunat disponibili
     */
    public int getNumDonutDisponinbili() {
        return bancone.getNumDonutDisponinbili();
    }
    /**
     * serve al programma per ricevere quante brioche sono disponibili
     * @return ritorna il numero di brioche disponibili
     */
    public int getNumBriocheDisponinbili() {
        return bancone.getNumBriocheDisponinbili();
    }
    /**
     * serve al programma per ricevere lo stato. se positivo il consumer non si attiva, se negativo il consumer rimane attivo
     * @return ritorna lo stato del programma
     */
    public MyGate getIntermezzoLock() {
        return lock;
    }
    /**
     * serve per ricavare lo stage in cui siamo
     * @return il numero stage
     */
    public byte getStagePartita(){
        return kiriko.getStagePartita();
    }
    /**
     * serve per ricevere il numero di brioche necessari per completare la quest
     * @return  numero brioche
     */
    public int getBriocheRichieste() {
        return quest.getBriocheRichieste();
    }
    /**
     * serve per ricevere il numero di baguette necessari per completare la quest
     * @return  numero baguette
     */
    public int getBaguetteRichieste() {
        return quest.getBaguetteRichieste();
    }
    /**
     * serve per ricevere il numero di dunat necessari per completare la quest
     * @return  numero dunat
     */
    public int getDonutRichieste() {
        return quest.getDonutRichieste();
    }
    /**
     * 
     * @param tipoPane
     */
    public void presoPane(TipoPane tipoPane) {
        quest.presoPane(tipoPane);
    }
    /**
     * serve per ricevere il punteggio ceh il giocatore ha aquisito
     * @return lo "score" in numero
     */
    public int getPunteggio() {
        return punteggio;
    }
    /**
     * serve ad ogni interazione per sommare i punti ceh il giocatore ha fatto
     * @param punti
     */
    public void addPunti(int punti) {
        this.punteggio+=punti;
    }
    /**
     * serve per ricevere quante baguette kiriko ha sfornato
     * @return il numero di baguette ceh ha sfornato
     */
    public int getnBaguetteFornite() {
        return kiriko.getnBaguetteFornite();
    }
    /**
     * serve per ricevere quante brioche kiriko ha sfornato
     * @return il numero di brioche ceh ha sfornato
     */
    public int getnBriocheFornite() {
        return kiriko.getnBriocheFornite();
    }
    /**
     * serve per ricevere quante dunat kiriko ha sfornato
     * @return il numero di dunat ceh ha sfornato
     */
    public int getnDonutFornite() {
        return kiriko.getnDonutFornite();
    }
}
