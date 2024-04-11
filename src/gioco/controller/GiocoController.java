package gioco.controller;

import static main.Main.raylib;
import static main.Main.statoApp;

import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;

import gioco.model.Giocatore;
import gioco.model.RisultatiGiocatori;
import gioco.model.StatoPartita;
import gioco.view.SalvaView;
import menu.model.Stato;

public class GiocoController {
    public static StatoPartita statoPartita=StatoPartita.GIOCANDO;
    private SalvaView salvaView;
    private RisultatiGiocatori risultatiGiocatori;
    private short punteggio;

    public GiocoController(SalvaView salvaView, RisultatiGiocatori risultatiGiocatori){
        this.salvaView= salvaView;
        this.risultatiGiocatori= risultatiGiocatori;
        this.punteggio=0;
    }

//-------------------------TEST SALVATAGGIO DATI-------------------------
    public static void main(String[] args) {
         RisultatiGiocatori g=new RisultatiGiocatori();
         
         
         g.salvaRisultati();
         //g.recuperaRisultati();
         
     }
     //-------------------------TEST SALVATAGGIO DATI-------------------------

     public void salva(){
        final byte MAX_INPUT_CHARS=14;
        char[] nome= new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
        int key, contaFrame=0;
        byte nLettere=0;
        boolean indietro=true;
//
statoPartita = StatoPartita.SALVA;
//
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
                    statoPartita=StatoPartita.GIOCANDO;
                    statoApp=Stato.MENU;
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
