package gioco.model;

public class MyGate {
    private volatile boolean isLocked;

    public MyGate(){
        this.isLocked=false;
    }

    public boolean isLocked(){
        return isLocked;
    }

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

    synchronized public void unlock(){
        this.isLocked=false;
        notifyAll();
    }

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
