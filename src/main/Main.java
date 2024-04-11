package main;
 
import com.raylib.java.Raylib;

import gioco.controller.GiocoController;
import gioco.model.RisultatiGiocatori;
import gioco.view.SalvaView;
import menu.controller.MenuController;
import menu.model.Stato;
import menu.view.MenuView;
import menu.view.RisultatiView;

public class Main {
    public static Stato statoApp= Stato.MENU; 
    public static Raylib raylib= new Raylib(1920,1080,"kiriko's bakery: food shortage");
   
    public static void main(String[] args) {
        
        raylib.core.MaximizeWindow();
        raylib.core.SetWindowState(0x00000002);
        raylib.core.SetTargetFPS(60);

        RisultatiGiocatori risultatiGiocatori= new RisultatiGiocatori();
        
        MenuController menuController= new MenuController(new MenuView(), new RisultatiView(), risultatiGiocatori);
        GiocoController giocoController= new GiocoController(new SalvaView(), risultatiGiocatori);

        while (statoApp!=Stato.ESCI) {
            //--------------------TEST---------------------
            switch(statoApp){
                case MENU:
                    menuController.run(); 
                    break;
                case GIOCA:
                    giocoController.salva();
                    break;
                case ESCI:
                    break;
                default:
                    statoApp= Stato.MENU;
                    break;
            }
            //---------------------------------------------
                
        }

        risultatiGiocatori.salvaRisultati();
        menuController.runUnload();
    }
}
