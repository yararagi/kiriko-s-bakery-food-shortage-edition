package gioco.controller;

import static main.Main.raylib;
import static main.Main.statoApp;

import java.util.Timer;
import java.util.TimerTask;

import com.raylib.java.core.rCore;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.core.input.Mouse.MouseButton;

import gioco.model.Giocatore;
import gioco.model.ModelGioco;
import gioco.model.MyGate;
import gioco.model.Pane;
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
    private ModelGioco model;
    private PartitaView partitaView;
    private Timer timerRound;
    private MyGate intermezzoLock;
    private double tempoInizio;
    private int punteggio;

    public GiocoController(SalvaView salvaView, PartitaView partitaView, RisultatiGiocatori risultatiGiocatori, ModelGioco model){
        this.salvaView= salvaView;
        this.risultatiGiocatori= risultatiGiocatori;
        this.model= model;
        this.partitaView= partitaView;
        this.timerRound= new Timer();
        this.intermezzoLock= model.getIntermezzoLock();
        tempoInizio=0;
        punteggio=0;
    }

//-------------------------TEST SALVATAGGIO DATI-------------------------
    public static void main(String[] args) {
         RisultatiGiocatori g=new RisultatiGiocatori();
         g.add(new Giocatore(45, "g1"));
         g.add(new Giocatore(452, "g2"));
         g.add(new Giocatore(445, "g3"));
         g.add(new Giocatore(25, "g4"));
         g.add(new Giocatore(545, "g5"));
         g.add(new Giocatore(435, "g6"));
         g.add(new Giocatore(90, "g8"));
         g.add(new Giocatore(45, "g7"));
         g.add(new Giocatore(45, "91"));
         g.add(new Giocatore(45, "g1"));
         g.add(new Giocatore(45, "g1"));

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
                    punteggio=0;
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

        timerRound.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("check win "+rCore.GetTime());
                    if(model.isQuestCompleted()==false){
                        punteggio=model.getPunteggio();
                        model=new ModelGioco(intermezzoLock); 
                        statoPartita= StatoPartita.SALVA;
                        timerRound.cancel();
                    }else{
                        tempoInizio= rCore.GetTime();
                        model.preparaProssimoLivello();
                        intermezzoLock.lockAndWait(); 
                    }
                }
                
            }, 30000);
            
        while (statoPartita == StatoPartita.GIOCANDO && statoApp!= Stato.ESCI) {
            synchronized(this){
                partitaView.setCestaBriocheEmptiness((model.getNumBriocheDisponinbili()==0)?true:false);         
                partitaView.setCestaBaguetteEmptiness((model.getNumBaguetteDisponinbili()==0)?true:false);         
                partitaView.setCestaDonutEmptiness((model.getNumDonutDisponinbili()==0)?true:false); 
                partitaView.setStagePartitaStage(model.getStagePartita()); 
                partitaView.setnBaguette(model.getBaguetteRichieste());
                partitaView.setnBrioche(model.getBriocheRichieste());
                partitaView.setnDonut(model.getDonutRichieste());
            }

            if(didPlayerTakeBrioche() == true){
                if(model.prendiPane(TipoPane.BRIOCHE)){
                    model.presoPane(TipoPane.BRIOCHE);
                    model.addPunti(Pane.BRIOCHE_VALUE);
                    punteggio+=Pane.BRIOCHE_VALUE;
                    System.out.println(model.getPunteggio());
                }
            }
            if(didPlayerTakeDonut() == true){
                if(model.prendiPane(TipoPane.DONUT)){
                    model.presoPane(TipoPane.DONUT);
                    model.addPunti(Pane.DONUT_VALUE);
                    punteggio+=Pane.DONUT_VALUE;
                    System.out.println(model.getPunteggio());
                }
            }
            if(didPlayerTakeBaguette() == true){
                if(model.prendiPane(TipoPane.BAGUETTE)){
                    model.presoPane(TipoPane.BAGUETTE);
                    model.addPunti(Pane.BAGUETTE_VALUE);
                    punteggio+=Pane.BAGUETTE_VALUE;
                    System.out.println(model.getPunteggio());
                }
            }
           
            partitaView.paintPartita();

            if(intermezzoLock.isLocked()){
                statoPartita= StatoPartita.INTERMEZZO;
            }
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
            partitaView.paintIntermezzo(punteggio);
            if(((int)(rCore.GetTime()-tempoInizio))>=10){
                statoPartita=StatoPartita.GIOCANDO;
                punteggio=0;
                intermezzoLock.unlock();
            } 
        }
    }
    
    private void salva(int punteggio){
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
                    System.out.println(model.getPunteggio());
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

    public void unload(){
        partitaView.unload();
        salvaView.unload();
    }
  
}
