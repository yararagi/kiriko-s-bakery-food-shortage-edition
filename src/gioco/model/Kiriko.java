package gioco.model;

import com.raylib.java.core.rCore;

public class Kiriko extends Thread{

    private Bancone bancone;
    private int donutRichieste, baguetteRichieste, briocheRichieste;
    private volatile byte stagePartita;

    public Kiriko (Quest quest, Bancone bancone){
        donutRichieste=quest.getDonutRichieste();
        briocheRichieste=quest.getBriocheRichieste();
        baguetteRichieste=quest.getBaguetteRichieste();
        this.bancone= bancone;
        this.stagePartita= 0;
    }

    @Override
    public void run() {
        try {
            rifornisci();
            stagePartita=0;
            System.out.println("hey0");
            Thread.sleep(9500);
            stagePartita=1;
            System.out.println("hey1");
            Thread.sleep(500);
            stagePartita=2;
            System.out.println("hey2");
            rifornisci();
            Thread.sleep(9500);
            stagePartita=3;
            System.out.println("hey3");
            Thread.sleep(500);
            stagePartita=4;
            System.out.println("hey4");

            rifornisci();
        } catch (InterruptedException e) {
        
            e.printStackTrace();
        }
        System.out.println("muoro "+rCore.GetTime());
    }

    private void rifornisci() {
        short i;
        for(i=0; i<(baguetteRichieste/3)+(Math.random()*6)+3;i++)    bancone.addBaguette(new Pane(TipoPane.BAGUETTE));
        for(i=0; i<(donutRichieste/3)+(Math.random()*3)+1;i++)     bancone.addDonut(new Pane(TipoPane.DONUT));
        for(i=0; i<(briocheRichieste/3)+(Math.random()*4)+2;i++)     bancone.addBrioche(new Pane(TipoPane.BRIOCHE));
    }

    public byte getStagePartita() {
        return stagePartita;
    }
    
}
