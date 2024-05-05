package main;


import java.util.Timer;
import java.util.TimerTask;

import com.raylib.java.Raylib;
import com.raylib.java.core.rCore;

import gioco.controller.GiocoController;
import gioco.model.ModelGioco;
import gioco.model.MyGate;
import gioco.model.RisultatiGiocatori;
import gioco.view.PartitaView;
import gioco.view.SalvaView;
import menu.controller.MenuController;
import menu.model.Stato;
import menu.view.MenuView;
import menu.view.RisultatiView;

public class Main {
    public volatile static Stato statoApp= Stato.MENU; 
    public static Raylib raylib= new Raylib(1920,1080,"kiriko's bakery: food shortage");
    MenuController menuController;
    GiocoController giocoController;
    RisultatiGiocatori risultatiGiocatori;

    Main (){
        rCore.SetConfigFlags(0x00000040);
        raylib.core.MaximizeWindow();
        raylib.core.SetWindowState(0x00000002);
        raylib.core.SetWindowState(0x00000100);
        raylib.core.SetTargetFPS(60);

        risultatiGiocatori= new RisultatiGiocatori();
        
        menuController= new MenuController(new MenuView(), new RisultatiView(), risultatiGiocatori);
        giocoController= new GiocoController(new SalvaView(),new PartitaView(), risultatiGiocatori, new ModelGioco(new MyGate()));

        raylib.core.SetExitKey(0);
        new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(statoApp!=Stato.ESCI && raylib.core.WindowShouldClose()){
                        statoApp=Stato.ESCI;
                    }
                };                
            }, 1, 1);
        
    }

    public void start(){
        while (statoApp!=Stato.ESCI) {
            switch(statoApp){
                case MENU:
                    menuController.run(); 
                    break;
                case GIOCA:
                    giocoController.run();
                    break;
                case ESCI:
                    break;
                default:
                    statoApp= Stato.MENU;
                    break;
            }
        }
    }

    private void esci(){
        risultatiGiocatori.salvaRisultati();
        giocoController.unload();
        menuController.runUnload();
        raylib.core.CloseWindow();
        System.exit(0);
    }

    public static void main(String[] args) {
        Main app= new Main();
        app.start();
        app.esci();
    }
}
