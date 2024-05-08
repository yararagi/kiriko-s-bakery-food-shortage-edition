package gioco.model;
/**
 * è una struttura di controllo della concorenza personalizato, creato per le esigenze specifiche del programma
 */
public class MyGate {
    private volatile boolean isLocked;

    public MyGate(){
        this.isLocked=false;
    }
    /**
     * verifica se MyGate è blocatto
     * @return ritorna o ture o false
     */
    public boolean isLocked(){
        return isLocked;
    }
    /**
     * serve a "chiudere" il MyGate e mette in attesa i threat che stanno operando
     */
    synchronized public void lockAndWait(){
        this.isLocked=true;
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * serve per "saprire" MyGate e svegliare tutti i threat che stanno aspettando di passare
     */
    synchronized public void unlock(){
        this.isLocked=false;
        notifyAll();
    }
    /**
     * serve per far aspettare il therat fino a quando non trova il cancello aperto
     */
    synchronized public void tryToPassThrough(){
        if(isLocked){
            while (isLocked) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
