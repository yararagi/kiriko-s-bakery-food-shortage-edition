package gioco.model;

import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;

public class Kiriko extends Thread{

    private Texture2D texture;
    private Rectangle box;
    private Bancone bancone;
    private Quest quest;

    public Kiriko (Quest quest, Bancone bancone){
        this.quest=quest;
        this.bancone= bancone;
    }

    @Override
    public void run() {
        try {
            rifornisci();
            Thread.sleep(10000);

            rifornisci();
            Thread.sleep(10000);

            rifornisci();
        } catch (InterruptedException e) {
        
            e.printStackTrace();
        }
        System.out.println("muoro");
    }

    private void rifornisci() {
        short i;
        for(i=0; i<(quest.getBaguetteRichieste()/3)+(Math.random()*5)+1;i++)    bancone.addBaguette(new Pane(TipoPane.BAGUETTE));
        for(i=0; i<(quest.getDonutRichieste()/3)+(Math.random()*5)+1;i++)     bancone.addDonut(new Pane(TipoPane.DONUT));
        for(i=0; i<(quest.getBriocheRichieste()/3)+(Math.random()*5)+1;i++)     bancone.addBrioche(new Pane(TipoPane.BRIOCHE));
    }
    
}
