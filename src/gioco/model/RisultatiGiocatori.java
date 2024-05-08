package gioco.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class RisultatiGiocatori implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private ArrayList<Giocatore> listaGiocatori;

    public RisultatiGiocatori(){
        listaGiocatori= new ArrayList<Giocatore>(0);
        recuperaRisultati();
    }
    /**
     * serve a ricavare i dati salvati nel file 
     */
    public void recuperaRisultati(){
        new Thread(new Runnable() {
            @SuppressWarnings("unchecked")
            @Override
            synchronized public void run()  {
                try {
                    FileInputStream fileIn = new FileInputStream("data/playerData.ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    listaGiocatori= (ArrayList<Giocatore>) in.readObject();
                    //listaGiocatori.forEach(System.out::println);
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    System.out.println("scores may not have been loaded properly");
                   
                    return;
                } catch (ClassNotFoundException c) {
                    System.out.println("scores not found");
                    
                    return;
                }
            }
        }).start();
    }
    /**
     * serve per inserire i dati nel file
     */
    public void salvaRisultati(){
        new Thread(new Runnable() {
            @Override
            synchronized public void run()  {
                try {
                    FileOutputStream fileOut = new FileOutputStream("data/playerData.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(listaGiocatori);
                    //listaGiocatori.forEach(System.out::println);
                    out.close();
                    fileOut.close();
                } catch (IOException i) {
                    System.out.println("scores may not have been saved properly");
                }
            }
        }).start();
    }

    public ArrayList<Giocatore> getListaGiocatori() {
        return listaGiocatori;
    }

    public void add(Giocatore g){
        this.listaGiocatori.add(g);
    }

}
