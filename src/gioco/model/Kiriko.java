package gioco.model;

import java.util.ArrayList;

import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;

public class Kiriko implements Runnable{

    private Texture2D texture;
    private Rectangle box;
    private ArrayList<Pane> pani;
    private Quest quest;

    public Kiriko (Quest quest){
        this.quest=quest;
    }

    @Override
    public void run() {
        try {
            for (int i= 0; i<3; i++){
                rifornisci();
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void rifornisci() {
        short i;
        for(i=0; i<(quest.getBaguetteRichieste()/3)+(Math.random()*5)+1;i++)    pani.add(new Pane(TipoPane.BAGUETTE));
        for(i=0; i<(quest.getDonutRichieste()/3)+(Math.random()*5)+1;i++)    pani.add(new Pane(TipoPane.DONUT));
        for(i=0; i<(quest.getBriocheRichieste()/3)+(Math.random()*5)+1;i++)    pani.add(new Pane(TipoPane.BRIOCHE));
    }
    
}
