package gioco.model;

import java.util.Vector;

/**
 * il bancone ha come funzione quella di mantenere i dati, come brioche, dunat o baguette
 * 
 */
public class Bancone {
    private Vector<Pane> briocheDisponinbili, donutDisponinbili, baguetteDisponinbili;

    public Bancone(){
        briocheDisponinbili=new Vector<>();
        donutDisponinbili=new Vector<>();
        baguetteDisponinbili=new Vector<>();
    }

    public synchronized boolean prendiPane(TipoPane tipo){
        switch (tipo) {
            case BAGUETTE:
                if(baguetteDisponinbili.size()>0){
                    baguetteDisponinbili.remove(baguetteDisponinbili.size()-1);
                    return true;
                }
                break;
            case BRIOCHE:
                if(briocheDisponinbili.size()>0){
                    briocheDisponinbili.remove(briocheDisponinbili.size()-1);
                    return true;
                }
                break;
            case DONUT:
                if(donutDisponinbili.size()>0){
                    donutDisponinbili.remove(donutDisponinbili.size()-1);
                    return true;
                }
                break;
            default:
                break;
        }
        return false;

    }
    /**
     * 
     * @return ritorna le baguette disponibili
     */
    public int getNumBaguetteDisponinbili() {
        return baguetteDisponinbili.size();
    }
    /**
     * 
     * @return ritorna le dunat disponibili
     */
    public int getNumDonutDisponinbili() {
        return donutDisponinbili.size();
    }
    /**
     * 
     * @return ritorna le brioche disponibili
     */
    public int getNumBriocheDisponinbili() {
        return briocheDisponinbili.size();
    }
    
    /**
     * 
     * @param brioche
     */
    public void addBrioche(Pane brioche){
        briocheDisponinbili.add(brioche);
    }  
    /**
     * 
     * @param donut
     */
    public void addDonut(Pane donut){
        donutDisponinbili.add(donut);
    }
    /**
     * 
     * @param baguette
     */
    public void addBaguette(Pane baguette){
        baguetteDisponinbili.add(baguette);
    }

}
