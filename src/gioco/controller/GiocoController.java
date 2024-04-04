package gioco.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gioco.model.Giocatore;
import gioco.model.Giocatori;

public class GiocoController {

//-------------------------TEST SALVATAGGIO DATI-------------------------
    public static void main(String[] args) {
         Giocatori g=new Giocatori(); /*= new ArrayList<Giocatore>()*/;
         g.lista.add(new Giocatore(2,"1"));
         g.lista.add(new Giocatore(333,"2"));
         g.lista.add(new Giocatore(22,"3"));
         g.lista.add(new Giocatore(25,"4rfg"));
         g.lista.add(new Giocatore(22,"5rr"));
         g.lista.add(new Giocatore(25,"6rfg"));
         g.lista.add(new Giocatore(22,"7rr"));
         g.lista.add(new Giocatore(25,"8rfg"));
         g.lista.add(new Giocatore(22,"7rr"));
         g.lista.add(new Giocatore(25,"8rfg"));
         g.lista.add(new Giocatore(22234566,"11rr"));
   
        try {
         FileOutputStream fileOut = new FileOutputStream("data/playerData.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(g);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/employee.ser");
      } catch (IOException i) {
         i.printStackTrace();
      }
    try {
         FileInputStream fileIn = new FileInputStream("data/playerData.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         g = (Giocatori) in.readObject();
         in.close();
         fileIn.close();
         g.lista.forEach(System.out::println);
      } catch (IOException i) {
         i.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return;
      }
     }
     //-------------------------TEST SALVATAGGIO DATI-------------------------
}
