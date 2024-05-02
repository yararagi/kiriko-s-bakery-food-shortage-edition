package gioco.model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ModelGioco {
    private Kiriko kiriko;
    private Bancone bancone;
    private Quest quest;
    private short nLivello;
    private ArrayList<Consumer> consumers;
    private Semaphore accessiAltriConsumatori;

    public ModelGioco(){
        nLivello=1;
        bancone= new Bancone();
        quest= new Quest(nLivello);
        kiriko= new Kiriko(quest, bancone);
        accessiAltriConsumatori= new Semaphore(3);
        consumers= new ArrayList<>();
        for(byte i=0; i<5;i++){
            consumers.add(new Consumer(bancone, accessiAltriConsumatori));
        }
    }

    public void prossimoLivello(){
        quest= new Quest((short)(nLivello+1));
        //kiriko.start();
    }

    public void start() {
        kiriko.start();
        for(byte i=0; i<5;i++){
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

    public int drainPermits() {
        return accessiAltriConsumatori.drainPermits();
    }

    public void release() {
        accessiAltriConsumatori.release(5);
    }

    
}
