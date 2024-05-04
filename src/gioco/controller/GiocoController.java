package gioco.controller;

import static main.Main.raylib;
import static main.Main.statoApp;

import java.util.Timer;
import java.util.TimerTask;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.core.input.Mouse.MouseButton;

import gioco.model.Giocatore;
import gioco.model.ModelGioco;
import gioco.model.MyGate;
import gioco.model.RisultatiGiocatori;
import gioco.model.StatoPartita;
import gioco.model.TipoPane;
import gioco.view.SalvaView;
import gioco.view.PartitaView;
import menu.model.Stato;

public class GiocoController {
    public static StatoPartita statoPartita=StatoPartita.GIOCANDO;
    private SalvaView salvaView;
    private RisultatiGiocatori risultatiGiocatori;
    private short punteggio;
    private ModelGioco model;
    private PartitaView partitaView;
    private Timer timerRound;
    private MyGate intermezzoLock;
    private double tempoInizio;

    public GiocoController(SalvaView salvaView, PartitaView partitaView, RisultatiGiocatori risultatiGiocatori, ModelGioco model){
        this.salvaView= salvaView;
        this.risultatiGiocatori= risultatiGiocatori;
        this.model= model;
        this.partitaView= partitaView;
        this.timerRound= new Timer();
        this.intermezzoLock= model.getIntermezzoLock();
        tempoInizio=0;
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
        while (statoApp == Stato.GIOCA){
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
                case INTERMEZZO:
                    intermezzo();
                    break;
                default:
                    break;
            }
        }
    }

    
    private void gioca(){
        model.startPartita();

        timerRound.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(model.isQuestCompleted()==false){
                        statoPartita= StatoPartita.SALVA;
                    }else{
                        tempoInizio= rCore.GetTime();
                        model.preparaProssimoLivello();
                        intermezzoLock.lockAndWait(); 
                    }
                }
                
            }, 30000, 30000);
            
        while (statoPartita == StatoPartita.GIOCANDO && statoApp!= Stato.ESCI) {
            raylib.core.BeginDrawing();
            raylib.core.ClearBackground(Color.BLACK);
            
            if(didPlayerTakeBrioche() == true){
                model.prendiPane(TipoPane.BRIOCHE); 
                System.out.println("player ha preso brioche, rimaste:"+model.getNumBriocheDisponinbili());
            }
            if(didPlayerTakeDonut() == true){
                model.prendiPane(TipoPane.DONUT);
                System.out.println("player ha preso donut, rimaste:"+model.getNumDonutDisponinbili());
            }
            if(didPlayerTakeBaguette() == true){
                model.prendiPane(TipoPane.BAGUETTE);
                System.out.println("player ha preso baguette, rimaste:"+model.getNumBaguetteDisponinbili());
            }
           
            partitaView.paintCeste();

            if(intermezzoLock.isLocked()){
                statoPartita= StatoPartita.INTERMEZZO;
            }

            raylib.core.EndDrawing();
        }
    }

    private boolean didPlayerTakeBaguette(){
        return (raylib.shapes.CheckCollisionPointRec(rCore.GetMousePosition(), partitaView.getCestaBaguette())&&raylib.core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT));
    }
    private boolean didPlayerTakeBrioche(){
        return (raylib.shapes.CheckCollisionPointRec(rCore.GetMousePosition(), partitaView.getCestaBrioche())&&raylib.core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT));
    }
    private boolean didPlayerTakeDonut(){
        return (raylib.shapes.CheckCollisionPointRec(rCore.GetMousePosition(), partitaView.getCestaDonut())&&raylib.core.IsMouseButtonPressed(MouseButton.MOUSE_BUTTON_LEFT));
    }

    private void intermezzo(){
        while (statoPartita == StatoPartita.INTERMEZZO && statoApp!= Stato.ESCI) {
            raylib.core.BeginDrawing();
            raylib.core.ClearBackground(Color.BLACK);
            raylib.text.DrawFPS(500, 500);
            System.out.print("c");
            if(((int)(rCore.GetTime()-tempoInizio))>=10){
                statoPartita=StatoPartita.GIOCANDO;
                intermezzoLock.unlock();
            } 
            raylib.core.EndDrawing();
        }
    }
    
    private void salva(short punteggio){
        final byte MAX_INPUT_CHARS=14;
        char[] nome= new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
        int key, contaFrame=0;
        byte nLettere=0;
        boolean indietro=true;

        while(statoPartita == StatoPartita.SALVA && statoApp!= Stato.ESCI){

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