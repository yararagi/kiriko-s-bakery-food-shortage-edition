package main;
 
import java.util.concurrent.Semaphore;
import com.raylib.java.Raylib;

import gioco.model.Stato;
import menu.controller.MenuController;
import menu.view.MenuView;

public class Main {
    public static Stato statoApp= Stato.MENU; 
    public static Raylib raylib= new Raylib(800,450,"kiriko's bakery: food shortage");
   
    public static void main(String[] args) {
        Semaphore gestore = new Semaphore(1); //DA IMPLEMENTARE UNA VOLTA SVILUPPATE E TESTATE LE ALTRE SEZIONI DELL'APP
        MenuController menuController= new MenuController(new MenuView());
        while (statoApp!=Stato.EXIT) {
            System.out.println(statoApp);
            //--------------------TEST---------------------
            switch(statoApp){
                case MENU:
                    menuController.run(); //solo la voce exit è implementata
                    break;
                case CREDITI:
                    statoApp= Stato.MENU;
                    break;
                case EXIT:
                    break;
                case GIOCO:
                statoApp= Stato.MENU;
                    break;
                default:
                    break;
            }
            //---------------------------------------------
                
        }
    }
}
