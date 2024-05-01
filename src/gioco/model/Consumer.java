package gioco.model;

import java.util.concurrent.Semaphore;

import gioco.controller.GiocoController;

public class Consumer extends Thread {
    private Bancone bancone;
    private Semaphore semaphore;

    public Consumer(Bancone bancone, Semaphore semaphore){
        this.bancone= bancone;
        this.semaphore= semaphore;
    }

    @Override
    public void run() {
        try {
            while(GiocoController.statoPartita.equals(StatoPartita.GIOCANDO)){
                Thread.sleep((long)((Math.random()*2500)+350)); //per non farli accedere tutti assieme bph
                semaphore.acquire();
                switch((int)(Math.random()*3)){
                    case 0:
                        bancone.prendiPane(TipoPane.DONUT);
                        System.out.println(this.getName()+"ha preso donut, rimaste: "+bancone.getNumDonutDisponinbili());
                        break;
                    case 1:
                        bancone.prendiPane(TipoPane.BAGUETTE);
                        System.out.println(this.getName()+"ha preso baguette, rimaste: "+bancone.getNumBaguetteDisponinbili());
                        break;      
                    case 2:
                        bancone.prendiPane(TipoPane.BRIOCHE);
                        System.out.println(this.getName()+"ha preso brioche, rimaste: "+bancone.getNumBriocheDisponinbili());
                        break;  
                    default:
                        System.out.println("ermm strano");
                        break;
                }
                Thread.sleep(1000); //il tempo dell'animazione
                semaphore.release();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
