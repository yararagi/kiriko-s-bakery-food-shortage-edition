package gioco.model;

import com.raylib.java.core.rCore;

public class Kiriko extends Thread{

    private Bancone bancone;
    private int donutRichieste, baguetteRichieste, briocheRichieste;
    private volatile byte stagePartita;
    private volatile int  nBaguetteFornite, nBriocheFornite, nDonutFornite;

    public Kiriko (Quest quest, Bancone bancone){
        donutRichieste=quest.getDonutRichieste();
        briocheRichieste=quest.getBriocheRichieste();
        baguetteRichieste=quest.getBaguetteRichieste();
        this.bancone= bancone;
        this.stagePartita= 0;
        nBaguetteFornite=1;
        nBaguetteFornite=1;
        nDonutFornite=1;
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
        int temp1, temp2, temp3;
        temp1=(int)((baguetteRichieste/3)+(Math.random()*6)+3);
        temp2=(int)((briocheRichieste/3)+(Math.random()*4)+2);
        temp3=(int)((donutRichieste/3)+(Math.random()*3)+1);
        nDonutFornite=bancone.getNumDonutDisponinbili()+temp3;
        nBaguetteFornite+=bancone.getNumBaguetteDisponinbili()+temp1;
        nBriocheFornite+=bancone.getNumBriocheDisponinbili()+temp2;
        for(i=0; i<temp1;i++)    bancone.addBaguette(new Pane(TipoPane.BAGUETTE));
        for(i=0; i<temp3;i++)     bancone.addDonut(new Pane(TipoPane.DONUT));
        for(i=0; i<temp2;i++)     bancone.addBrioche(new Pane(TipoPane.BRIOCHE));
    }

    public byte getStagePartita() {
        return stagePartita;
    }

    public int getnBaguetteFornite() {
        return nBaguetteFornite;
    }
    public int getnBriocheFornite() {
        return nBriocheFornite;
    }
    public int getnDonutFornite() {
        return nDonutFornite;
    }
    
}
