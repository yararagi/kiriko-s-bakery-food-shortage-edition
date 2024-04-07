package gioco.controller;

import gioco.model.Giocatore;
import gioco.model.RisultatiGiocatori;

public class GiocoController {

//-------------------------TEST SALVATAGGIO DATI-------------------------
    public static void main(String[] args) {
         RisultatiGiocatori g=new RisultatiGiocatori();
         g.add(new Giocatore(2,"1"));
         g.add(new Giocatore(333,"2"));
         g.add(new Giocatore(22,"3"));
         g.add(new Giocatore(25,"4rfg"));
         g.add(new Giocatore(22,"5rr"));
         g.add(new Giocatore(25,"6rfg"));
         g.add(new Giocatore(22,"7rr"));
         g.add(new Giocatore(25,"8rfg"));
         g.add(new Giocatore(22,"7rr"));
         g.add(new Giocatore(25,"8rfg"));
         g.add(new Giocatore(22234566,"11rr"));
   
         
         g.salvaRisultati();
         //g.recuperaRisultati();
         
     }
     //-------------------------TEST SALVATAGGIO DATI-------------------------
}
