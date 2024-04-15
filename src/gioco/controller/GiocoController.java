package gioco.controller;

import static main.Main.raylib;
import static main.Main.statoApp;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.core.input.Mouse.MouseButton;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

import gioco.model.Giocatore;
import gioco.model.RisultatiGiocatori;
import gioco.model.StatoPartita;
import gioco.view.SalvaView;
import menu.model.Stato;

public class GiocoController {
    public static StatoPartita statoPartita=StatoPartita.GIOCANDO;
    private SalvaView salvaView;
    private RisultatiGiocatori risultatiGiocatori;
    short punteggio;

    public GiocoController(SalvaView salvaView, RisultatiGiocatori risultatiGiocatori){
        this.salvaView= salvaView;
        this.risultatiGiocatori= risultatiGiocatori;
    }

//-------------------------TEST SALVATAGGIO DATI-------------------------
    public static void main(String[] args) {
         RisultatiGiocatori g=new RisultatiGiocatori();
         
         
         g.salvaRisultati();
         //g.recuperaRisultati();
         
     }
     //-------------------------TEST SALVATAGGIO DATI-------------------------


     public void run() {
        statoPartita=StatoPartita.GIOCANDO;
        while (statoApp == Stato.GIOCA ){
            switch (statoPartita) {
                case GIOCANDO:
                    gioca();
                    break;
                case SALVA:
                    salva(punteggio);
                    break;
                case TORNAALMENU:
                    statoApp= Stato.MENU;
                    break;
                default:
                    break;
            }
        }
    }
/*
    private void gioca(){
        Rectangle temp=new Rectangle(0, 0, 100, 100);
        punteggio=0;
        statoPartita= StatoPartita.GIOCANDO;
        while (statoPartita == StatoPartita.GIOCANDO) {
            raylib.core.BeginDrawing();
            raylib.core.ClearBackground(Color.BLACK);
            rShapes.DrawRectangleRec(temp, Color.GOLD);
            raylib.text.DrawText("(test, not the actual game obv) click the square 20 times", 800, 800, 35, Color.GOLD);
            temp.x+=1;
            temp.y+=0.5f;

            if(raylib.core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT)&&raylib.shapes.CheckCollisionPointRec(rCore.GetMousePosition(), temp)){
                temp.height+=5;
                temp.width+=5;
                punteggio++;
            }

            if(temp.height>=200){
                statoPartita= StatoPartita.SALVA;
            }
            
            raylib.core.EndDrawing();
        }
    }
*/
    private void gioca(){
        
        statoPartita= StatoPartita.GIOCANDO;
        while (statoPartita == StatoPartita.GIOCANDO) {
            raylib.core.BeginDrawing();
            raylib.core.ClearBackground(Color.BLACK);
           



            raylib.core.EndDrawing();
        }
    }
    

    private void salva(short punteggio){
        final byte MAX_INPUT_CHARS=14;
        char[] nome= new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
        int key, contaFrame=0;
        byte nLettere=0;
        boolean indietro=true;

        while(statoPartita == StatoPartita.SALVA){

            salvaView.paintFinestraSalvataggio(String.copyValueOf(nome));
            key= raylib.core.GetCharPressed();
            
            while (key > 0){
                if ((key >= 32) && (key <= 125) && (nLettere < MAX_INPUT_CHARS)){
                    nome[nLettere] = (char)key;
                    nLettere++;
                }
                if(key==32){
                    contaFrame=0;
                }

                key = raylib.core.GetCharPressed();
            }

            if (rCore.IsKeyDown(Keyboard.KEY_BACKSPACE) && ((((contaFrame)%5) == 0) || indietro)){
            	indietro=false;
                nLettere--;
                if (nLettere < 0){ nLettere = 0; }
                nome[nLettere]=' ';
                if(nLettere< MAX_INPUT_CHARS-1){ nome[nLettere+1]=' '; }
                contaFrame=0;
            }
            if(raylib.core.IsKeyUp(Keyboard.KEY_BACKSPACE)) {
            	indietro=true;
            }
            
            if(raylib.core.IsKeyPressed(Keyboard.KEY_ENTER)){
                if(nLettere<MAX_INPUT_CHARS && ((contaFrame/50)%2) == 0){
                    nome[nLettere]=' ';
                }
                if(!String.copyValueOf(nome).replaceAll(" +", "").equals("")){
                    risultatiGiocatori.add(new Giocatore(punteggio, String.copyValueOf(nome).trim().replaceAll(" +", " ")));
                    statoPartita= StatoPartita.TORNAALMENU;
                }
            }else{
                if(nLettere<MAX_INPUT_CHARS && ((contaFrame/50)%2) == 0){
                    nome[nLettere]='_';
                }else if(nLettere<MAX_INPUT_CHARS){
                    nome[nLettere]=' ';
                }
                contaFrame++;
            }

        }
     }

  
}
