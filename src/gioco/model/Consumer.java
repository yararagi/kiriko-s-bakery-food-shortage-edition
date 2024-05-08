package gioco.model;

import static main.Main.statoApp;

import java.util.Vector;

import gioco.controller.GiocoController;
import menu.model.Stato;
/**
 * il consumer è un threat che "ruba" i panini al giocatore. Come oggeti con cui interagiscie ha Bancone e lock
 */

public class Consumer extends Thread {
    private Bancone bancone;
    private MyGate lock;
    private Vector<Byte> statoAnimazione;
    private byte nConsumer;

    public Consumer(Bancone bancone, MyGate lock, Vector<Byte> statoAnimazione, byte nConsumer){
        this.bancone= bancone;
        this.lock=lock;
        this.statoAnimazione= statoAnimazione;
        this.nConsumer=nConsumer;
        statoAnimazione.add(this.nConsumer, (byte)(-1));
    }

    @Override
    public void run() {
        try {
            while(GiocoController.statoPartita==StatoPartita.GIOCANDO && statoApp!= Stato.ESCI){
                Thread.sleep((long)((Math.random()*2000)+2200)); //per non farli accedere tutti assieme bph
                lock.tryToPassThrough();
                switch((int)(Math.random()*3)){
                    case 0:
                        bancone.prendiPane(TipoPane.DONUT);
                        System.out.println(this.getName()+"ha preso donut, rimaste: "+bancone.getNumDonutDisponinbili());
                        statoAnimazione.set(this.nConsumer, (byte)(0));
                        break;
                    case 1:
                        bancone.prendiPane(TipoPane.BAGUETTE);
                        System.out.println(this.getName()+"ha preso baguette, rimaste: "+bancone.getNumBaguetteDisponinbili());
                        statoAnimazione.set(this.nConsumer, (byte)(1));
                        break;      
                    case 2:
                        bancone.prendiPane(TipoPane.BRIOCHE);
                        statoAnimazione.set(this.nConsumer, (byte)(2));
                        System.out.println(this.getName()+"ha preso brioche, rimaste: "+bancone.getNumBriocheDisponinbili());
                        break;  
                    default:
                        System.err.println("ermm strano");
                        statoAnimazione.set(this.nConsumer, (byte)(-1));
                        break;
                }
                Thread.sleep(1000); //il tempo dell'animazione boh
                statoAnimazione.set(this.nConsumer, (byte)(-1));  //valore non valido, ferma l'animazione
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
