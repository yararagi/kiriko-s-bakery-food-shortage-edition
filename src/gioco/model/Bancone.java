package gioco.model;

import java.util.Vector;

public class Bancone {
    private Vector<Pane> briocheDisponinbili;
    private Vector<Pane> donutDisponinbili;
    private Vector<Pane> baguetteDisponinbili;

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

    public int getNumBaguetteDisponinbili() {
        return baguetteDisponinbili.size();
    }
    public int getNumDonutDisponinbili() {
        return donutDisponinbili.size();
    }
    public int getNumBriocheDisponinbili() {
        return briocheDisponinbili.size();
    }

    public synchronized void addBrioche(Pane brioche){
        briocheDisponinbili.add(brioche);
    }  
    public synchronized void addDonut(Pane donut){
        donutDisponinbili.add(donut);
    }
    public synchronized void addBaguette(Pane baguette){
        baguetteDisponinbili.add(baguette);
    }

}
