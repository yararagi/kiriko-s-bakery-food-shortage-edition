package gioco.model;

import gioco.controller.GiocoController;

public class Consumer extends Thread {
    private Bancone bancone;

    public Consumer(Bancone bancone){
        this.bancone= bancone;
    }

    @Override
    public void run() {
        try {
            while(GiocoController.statoPartita.equals(StatoPartita.GIOCANDO)){
                Thread.sleep((long)((Math.random()*2500)+350)); //per non farli accedere tutti assieme bph
                switch((int)(Math.random()*3)){
                    case 1:
                        bancone.prendiPane(TipoPane.DONUT);
                        break;
                    case 2:
                        bancone.prendiPane(TipoPane.BAGUETTE);
                        break;
                    case 3:
                        bancone.prendiPane(TipoPane.BRIOCHE);
                        break;
                    default:
                    System.out.println("ermm strano");
                    break;
                }
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
