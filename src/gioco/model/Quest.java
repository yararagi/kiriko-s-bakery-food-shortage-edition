package gioco.model;

import java.util.HashMap;
import java.util.Map;

public class Quest {
    private Map<TipoPane, Integer> paniRichiesti= new HashMap<>();

    public Quest(short nLivello){
        float moltiplicatore=nLivello*0.1f+((float)(Math.random()*1.5f) +1) ;
        paniRichiesti.put(TipoPane.BAGUETTE, (int)(10*moltiplicatore));
        paniRichiesti.put(TipoPane.BRIOCHE, (int)(5*moltiplicatore));
        paniRichiesti.put(TipoPane.DONUT, (int)(0.6*moltiplicatore));
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
}
