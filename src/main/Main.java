package main;
 
import java.util.concurrent.Semaphore;
import com.raylib.java.Raylib;

import gioco.model.Stato;
import menu.controller.MenuController;
import menu.view.MenuView;
import menu.view.RisultatiView;

public class Main {
    public static Stato statoApp= Stato.MENU; 
    public static Raylib raylib= new Raylib(1920,1080,"kiriko's bakery: food shortage");
   
    public static void main(String[] args) {
        //Semaphore gestore = new Semaphore(1); //DA IMPLEMENTARE UNA VOLTA SVILUPPATE E TESTATE LE ALTRE SEZIONI DELL'APP
        MenuController menuController= new MenuController(new MenuView(), new RisultatiView());
        while (statoApp!=Stato.ESCI) {
            //--------------------TEST---------------------
            switch(statoApp){
                case MENU:
                    menuController.run(); 
                    break;
                case RISULTATI:
                    menuController.runRisultati();
                    break;
                case ESCI:
                    break;
                case GIOCA:
                statoApp= Stato.MENU;
                    break;
                    default:
                    break;
            }
            
                System.out.println(statoApp);
            //---------------------------------------------
                
        }
    }
}
