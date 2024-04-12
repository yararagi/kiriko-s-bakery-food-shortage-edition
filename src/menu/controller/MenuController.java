package menu.controller;

import static main.Main.raylib;
import static main.Main.statoApp;

import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.core.input.Mouse.MouseButton;

import gioco.model.RisultatiGiocatori;
import menu.model.Stato;
import menu.view.MenuView;
import menu.view.RisultatiView;

public class MenuController {
    private byte scelta;
    private MenuView view;
    private RisultatiView rView;
    private RisultatiGiocatori risultatiGiocatori;

    public MenuController(MenuView view, RisultatiView rView, RisultatiGiocatori risultatiGiocatori){
        this.risultatiGiocatori= risultatiGiocatori;
        scelta=0;
        this.view=view; 
        this.rView=rView;
    }


    public void run() {
        while(statoApp==Stato.MENU){
            view.paintMenu(scelta);

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
                        statoApp=Stato.GIOCA;
                        break;
                    case 1:
                        statoApp=Stato.RISULTATI;
                        runRisultati();
                        break;
                    case 2:
                        statoApp=Stato.ESCI;
                    default:
                        break;
                }
                scelta=0;
            }

            if(raylib.shapes.CheckCollisionPointRec(rCore.GetMousePosition(), view.getVociMenuBtn()[0])){
                scelta=0;
                if(raylib.core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)){
                    statoApp=Stato.GIOCA;
                }
            }
            if(raylib.shapes.CheckCollisionPointRec(rCore.GetMousePosition(), view.getVociMenuBtn()[1])){
                scelta=1;
                if(raylib.core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)){
                    statoApp=Stato.RISULTATI;
                    runRisultati();
                }
            }
            if(raylib.shapes.CheckCollisionPointRec(rCore.GetMousePosition(), view.getVociMenuBtn()[2])){
                scelta=2;
                if(raylib.core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)){
                    statoApp=Stato.ESCI;
                }
            }
            
        }
    }

    public void runRisultati(){
        short indexToShow=0, howManyToShow=7;
        while(statoApp==Stato.RISULTATI){
            if(risultatiGiocatori.getListaGiocatori().size()>howManyToShow){
                rView.paintSchermataRisultati(risultatiGiocatori.getListaGiocatori().subList(indexToShow, indexToShow+howManyToShow), (indexToShow==0)?(false):(true),((howManyToShow+indexToShow)<risultatiGiocatori.getListaGiocatori().size())?(true):(false) );
            }else{
                rView.paintSchermataRisultati(risultatiGiocatori.getListaGiocatori(), false, false);
            }
            
            if(raylib.core.IsKeyPressed(Keyboard.KEY_DOWN) && indexToShow+howManyToShow< risultatiGiocatori.getListaGiocatori().size()){
                indexToShow++;
            }
            if(raylib.core.IsKeyPressed(Keyboard.KEY_UP) && indexToShow>0){
                indexToShow--;
            }
            if(raylib.core.IsKeyPressed(Keyboard.KEY_ENTER)){
                statoApp=Stato.MENU;
            }
            if(raylib.core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)&&raylib.shapes.CheckCollisionPointTriangle(rCore.GetMousePosition(), rView.getTriangoloScrollDown()[0],rView.getTriangoloScrollDown()[1], rView.getTriangoloScrollDown()[2]) && indexToShow+howManyToShow< risultatiGiocatori.getListaGiocatori().size()){
                indexToShow++;
            }
            if(raylib.core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)&&raylib.shapes.CheckCollisionPointTriangle(rCore.GetMousePosition(), rView.getTriangoloScrollUp()[0],rView.getTriangoloScrollUp()[1], rView.getTriangoloScrollUp()[2]) && indexToShow>0){
                indexToShow--;
            }
            if(raylib.core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)&&raylib.shapes.CheckCollisionPointRec(rCore.GetMousePosition(), rView.getBackBtn())){
                statoApp=Stato.MENU;
            }
            
        }
    }

    public void runUnload(){
        view.unload();
        rView.unload();
    }

}
