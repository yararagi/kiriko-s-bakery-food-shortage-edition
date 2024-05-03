package gioco.model;

import gioco.controller.GiocoController;

public class Consumer extends Thread {
    private Bancone bancone;
    private MyGate lock;

    public Consumer(Bancone bancone, MyGate lock){
        this.bancone= bancone;
        this.lock=lock;
    }

    @Override
    public void run() {
        try {
            while(GiocoController.statoPartita.equals(StatoPartita.GIOCANDO)){
                Thread.sleep((long)((Math.random()*2500)+500)); //per non farli accedere tutti assieme bph
                lock.tryToPassThrough();
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
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
