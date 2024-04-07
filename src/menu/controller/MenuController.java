package menu.controller;

import static main.Main.raylib;
import static main.Main.statoApp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.raylib.java.core.input.Keyboard;

import gioco.model.Giocatori;
import menu.model.Stato;
import menu.view.MenuView;
import menu.view.RisultatiView;

public class MenuController {
    private byte scelta;
    private MenuView view;
    private RisultatiView rView;
    private Giocatori giocatori;

    public MenuController(MenuView view, RisultatiView rView){
        recuperaRisultati();
        scelta=0;
        this.view=view; 
        this.rView=rView;
    }


    public void run() {
        recuperaRisultati();
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
                        break;
                    case 2:
                        statoApp=Stato.ESCI;
                    default:
                        break;
                }
                scelta=0;
            }
            
        }
    }

    public void runRisultati(){
        short indexToShow=0, howManyToShow=7;
        while(statoApp==Stato.RISULTATI){
            if(giocatori.lista.size()>howManyToShow){
                rView.paintSchermataRisultati(giocatori.lista.subList(indexToShow, indexToShow+howManyToShow), (indexToShow==0)?(false):(true),((howManyToShow+indexToShow)<giocatori.lista.size())?(true):(false) );
            }else{
                rView.paintSchermataRisultati(giocatori.lista, false, false);
            }
            
            if(raylib.core.IsKeyPressed(Keyboard.KEY_DOWN) && indexToShow+howManyToShow< giocatori.lista.size()){   indexToShow++;  }
            if(raylib.core.IsKeyPressed(Keyboard.KEY_UP) && indexToShow>0){   indexToShow--;  }
            if(raylib.core.IsKeyPressed(Keyboard.KEY_ENTER)){
                statoApp=Stato.MENU;
            }
        }
    }

    private void recuperaRisultati(){
        new Thread(new Runnable() {
            @Override
            public void run()  {
                try {
                    FileInputStream fileIn = new FileInputStream("data/playerData.ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    giocatori = (Giocatori) in.readObject();
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    i.printStackTrace();
                    giocatori=null;
                    return;
                } catch (ClassNotFoundException c) {
                    System.out.println("scores not found");
                    giocatori=null;
                    return;
                }
            }
        }).start();
    }

}
