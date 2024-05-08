package gioco.model;

import java.util.HashMap;
import java.util.Map;
/**
 * Quest serve per creare un nuovo obbiettivo che il giocatore dovra eseguire
 */

public class Quest {
    private Map<TipoPane, Integer> paniRichiesti= new HashMap<>();

    public Quest(short nLivello){
        float moltiplicatore=nLivello*0.1f+((float)(Math.random()*0.4f+0.15f) +1) ;
        paniRichiesti.put(TipoPane.BAGUETTE, (int)(Math.pow(9,moltiplicatore)));
        paniRichiesti.put(TipoPane.BRIOCHE, (int)(Math.pow(6,moltiplicatore)));
        paniRichiesti.put(TipoPane.DONUT, (int)(Math.pow(2,moltiplicatore)));
    }

    /**
     * manda il numero di brioche necessari per il completamento della quest
     * @return il numero di brioche necessarie per la quest
     */
    public int getBriocheRichieste() {
        return paniRichiesti.get(TipoPane.BRIOCHE);
    }
    /**
     * manda il numero di baguette necessari per il completamento della quest
     * @return il numero di baguette necessarie per la quest
     */   
    public int getBaguetteRichieste() {
        return paniRichiesti.get(TipoPane.BAGUETTE);
    }
    /**
     * manda il numero di dunat necessari per il completamento della quest
     * @return il numero di dunat necessarie per la quest
     */    
    public int getDonutRichieste() {
        return paniRichiesti.get(TipoPane.DONUT);
    }
    /**
     * serve per interfacciarsi con un hascode, permette fare -1 sul pane selezionato
     * @param tipoPane
     */
    public void presoPane(TipoPane tipoPane){
        if(paniRichiesti.get(tipoPane)>0){
            paniRichiesti.replace(tipoPane, paniRichiesti.get(tipoPane)-1);
        }
        System.out.println("player ha preso una "+tipoPane+" ["+paniRichiesti.get(tipoPane)+" rimaste da prendere]");
    }
    /**
     * serve al programma per conprendere in che stato è la missione 
     * @return ritorna o true o false
     */
    public boolean isQuestCompleted(){
        return (paniRichiesti.get(TipoPane.BAGUETTE)==0 && paniRichiesti.get(TipoPane.DONUT)==0 && paniRichiesti.get(TipoPane.BRIOCHE)==0);
    }
}
