package menu.controller;

import static main.Main.raylib;
import static main.Main.statoApp;

import com.raylib.java.core.input.Keyboard;

import gioco.model.Stato;
import menu.view.MenuView;

public class MenuController {
    private byte scelta;
    private MenuView view;


    public MenuController(MenuView view){
        scelta=0;
        this.view=view; 
    }


    public void run() {
        while(statoApp==Stato.MENU){
            
            if(raylib.core.IsKeyPressed(Keyboard.KEY_DOWN)){
                if(scelta>=view.getnScritte()-1){
                    scelta=0;
                }else{
                    scelta++;
                } 
            }
            if(raylib.core.IsKeyPressed(Keyboard.KEY_UP)){ 
                if(scelta<=0){
                    scelta=(byte) (view.getnScritte()-1);
                }else{
                    scelta--;
                }     
            }
            
            if(raylib.core.IsKeyPressed(Keyboard.KEY_ENTER)){
                switch (scelta) {
                    case 0:
                        statoApp=Stato.GIOCO;
                        break;
                    case 1:
                        statoApp=Stato.CREDITI;
                        break;
                    case 2:
                        statoApp=Stato.EXIT;
                    default:
                        break;
                }
            }
            
            view.paintScritte(scelta);
        }
    }

}
