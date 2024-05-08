package gioco.model;

import java.util.HashMap;
import java.util.Map;

public class Quest {
    private Map<TipoPane, Integer> paniRichiesti= new HashMap<>();

    public Quest(short nLivello){
        float moltiplicatore=nLivello*0.1f+((float)(Math.random()*0.4f+0.15f) +1) ;
        paniRichiesti.put(TipoPane.BAGUETTE, (int)(Math.pow(9,moltiplicatore)));
        paniRichiesti.put(TipoPane.BRIOCHE, (int)(Math.pow(6,moltiplicatore)));
        paniRichiesti.put(TipoPane.DONUT, (int)(Math.pow(2,moltiplicatore)));
    }

    public int getBriocheRichieste() {
        return paniRichiesti.get(TipoPane.BRIOCHE);
    }
    
    public int getBaguetteRichieste() {
        return paniRichiesti.get(TipoPane.BAGUETTE);
    }
    
    public int getDonutRichieste() {
        return paniRichiesti.get(TipoPane.DONUT);
    }

    public void presoPane(TipoPane tipoPane){
        if(paniRichiesti.get(tipoPane)>0){
            paniRichiesti.replace(tipoPane, paniRichiesti.get(tipoPane)-1);
        }
        System.out.println("player ha preso una "+tipoPane+" ["+paniRichiesti.get(tipoPane)+" rimaste da prendere]");
    }

    public boolean isQuestCompleted(){
        return (paniRichiesti.get(TipoPane.BAGUETTE)==0 && paniRichiesti.get(TipoPane.DONUT)==0 && paniRichiesti.get(TipoPane.BRIOCHE)==0);
    }
}
