package gioco.model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ModelGioco {
    private Kiriko kiriko;
    private Bancone bancone;
    private Quest quest;
    private short nLivello;
    private ArrayList<Consumer> consumers;
    private MyGate lock; 

    public ModelGioco(MyGate lock){
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

    public void startProssimoLivello(){
        kiriko.start();
    }
    public void preparaProssimoLivello(){
        nLivello+=1;
        quest= new Quest((short)(nLivello+1));
        kiriko= new Kiriko(quest, bancone);
    }

    public void startPartita() {
        kiriko.start();
        for(byte i=0; i<3;i++){
            consumers.get(i).start();
        }
    }

    public boolean prendiPane(TipoPane tipo) {
        quest.presoPane(tipo);
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
    
}
