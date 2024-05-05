package gioco.model;

import java.lang.Thread.State;
import java.util.ArrayList;

public class ModelGioco {
    private Kiriko kiriko;
    private Bancone bancone;
    private Quest quest;
    private short nLivello;
    private ArrayList<Consumer> consumers;
    private MyGate lock; 
    private byte punteggio;

    public ModelGioco(MyGate lock){
        punteggio=0;
        nLivello=1;
        bancone= new Bancone();
        quest= new Quest(nLivello);
        kiriko= new Kiriko(quest, bancone);
        consumers= new ArrayList<>();
        this.lock= lock;
        for(byte i=0; i<3;i++){
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
        for(byte i=0; i<3;i++){
            if(consumers.get(i).getState()==State.NEW){
                consumers.get(i).start();
            }        
        }
    }

    public boolean prendiPane(TipoPane tipo) {
        return bancone.prendiPane(tipo);
    }

    public boolean isQuestCompleted(){
        return quest.isQuestCompleted();
    }

    public int getNumBaguetteDisponinbili() {
        return bancone.getNumBaguetteDisponinbili();
    }

    public int getNumDonutDisponinbili() {
        return bancone.getNumDonutDisponinbili();
    }

    public int getNumBriocheDisponinbili() {
        return bancone.getNumBriocheDisponinbili();
    }

    public MyGate getIntermezzoLock() {
        return lock;
    }
    
    public byte getStagePartita(){
        return kiriko.getStagePartita();
    }

    public int getBriocheRichieste() {
        return quest.getBriocheRichieste();
    }

    public int getBaguetteRichieste() {
        return quest.getBaguetteRichieste();
    }

    public int getDonutRichieste() {
        return quest.getDonutRichieste();
    }

    public void presoPane(TipoPane tipoPane) {
        quest.presoPane(tipoPane);
    }

    public byte getPunteggio() {
        return punteggio;
    }
    public void addPunti(byte punti) {
        this.punteggio+=punti;
    }
    
}
