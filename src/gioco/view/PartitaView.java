package gioco.view;


import static main.Main.raylib;

import java.util.Vector;
import java.util.concurrent.Semaphore;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.text.Font;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;


public class PartitaView {
    private Rectangle cestaBrioche, cestaDonut, cestaBaguette, stagePartitaSrc, stagePartitaDest;
    private Texture2D cartelloQuestTexture,stagePartitaSprite, banconeTexture, vetroTexture, backgrounTexture, cestaBriocheTexture[], cestaDonutTexture[], cestaBaguetteTexture[], baguette, brioche, donut ;
    private byte cestaBaguetteStatus, cestaDonutStatus, cestaBriocheStatus, fontsize;
    private int nBrioche, nBaguette, nDonut;
    private Vector2 posCartelloIntervallo, posScrittaQuest, poscartelloQuest, cestaBriochePos, cestaBaguettePos, cestaDonutPos, pos00, posLegendaBaguette, posLegendaBrioche, posLegendaDonut, posLegendaScrittaBrioche, posLegendaScrittaBaguette, posLegendaScrittaDonut ;
    private Font font;
    private float halfScreenWidth=rCore.GetScreenWidth()*0.5f, halfScreenHeight= rCore.GetScreenHeight()*0.5f;
    private float percentualeCestaBrioche=1, percentualeCestaBaguette=1, percentualeCestaDonut=1;
    private Animazioni animazioneKiriko, animazioneConsumer;
    

    public PartitaView(){
        fontsize=30;
        font = raylib.text.LoadFontEx("font/MagicBreadRegular.ttf",fontsize,null,0);
        backgrounTexture=rTextures.LoadTexture("texture/background/gioco/background.png");
        banconeTexture= rTextures.LoadTexture("texture/background/gioco/bancone.png");
        vetroTexture= rTextures.LoadTexture("texture/background/gioco/bancone.png");
        cestaBaguetteTexture= new Texture2D[2];
        cestaBriocheTexture= new Texture2D[2];
        cestaDonutTexture= new Texture2D[2];
        cestaBaguetteTexture[0]= rTextures.LoadTexture("texture/background/gioco/pani/baguet_vuoto.png");
        cestaBriocheTexture[0]= rTextures.LoadTexture("texture/background/gioco/pani/brioche_vuoto.png");
        cestaDonutTexture[0]= rTextures.LoadTexture("texture/background/gioco/pani/dunat_vuoto.png");
        cestaBaguetteTexture[1]= rTextures.LoadTexture("texture/background/gioco/pani/baguet_pieno.png");
        cestaBriocheTexture[1]= rTextures.LoadTexture("texture/background/gioco/pani/brioche_pieno.png");
        cestaDonutTexture[1]= rTextures.LoadTexture("texture/background/gioco/pani/dunat_pieno.png");
        cestaBrioche= new Rectangle(140*6,93*6,40*6,40*6); 
        cestaDonut= new Rectangle(184*6,93*6,40*6,40*6);
        cestaBaguette= new Rectangle(96*6,93*6,40*6,40*6);
        cestaBriochePos= new Vector2(cestaBrioche.getX(),cestaBrioche.getY()); 
        cestaDonutPos= new Vector2(cestaDonut.getX(),cestaDonut.getY());
        cestaBaguettePos= new Vector2(cestaBaguette.getX(),cestaBaguette.getY());
        cestaBaguetteStatus=1;
        cestaDonutStatus=1;
        cestaBriocheStatus=1;
        stagePartitaSprite=rTextures.LoadTexture("texture/background/gioco/sprite/indicatore.png");
        stagePartitaSrc= new Rectangle(0,0,40,40);
        stagePartitaDest= new Rectangle(150*6, 45*6, 40*3, 40*3);
        pos00=new Vector2(0,0);
        nBaguette=0;
        nBrioche=0;
        nDonut=0;
        donut= rTextures.LoadTexture("texture/background/gioco/asset_mission/dunat.png");
        brioche= rTextures.LoadTexture("texture/background/gioco/asset_mission/brioche.png");
        baguette= rTextures.LoadTexture("texture/background/gioco/asset_mission/baguet.png");
        posLegendaDonut=new Vector2(halfScreenWidth-75, 40);
        posLegendaBrioche=new Vector2(halfScreenWidth-75, 85);
        posLegendaBaguette=new Vector2(halfScreenWidth-75, 135);
        posLegendaScrittaDonut=new Vector2(halfScreenWidth+10, 55);
        posLegendaScrittaBrioche=new Vector2(halfScreenWidth+10, 105);
        posLegendaScrittaBaguette=new Vector2(halfScreenWidth+10, 150);
        cartelloQuestTexture=rTextures.LoadTexture("texture/background/gioco/asset_mission/cartello.png");
        poscartelloQuest=new Vector2(halfScreenWidth-25*5, 0);
        posScrittaQuest= new Vector2(halfScreenWidth-(rText.MeasureTextEx(font, "Quest:", fontsize+5, 2).getX()*0.5f), 15);
        posCartelloIntervallo= new Vector2(halfScreenWidth-25*20f, halfScreenHeight-20f*20);
        animazioneKiriko= new Animazioni(
            rTextures.LoadTexture("texture/animazione/Kiriko.png"),
            new Rectangle(0,0,67,72),
            new Rectangle(225*6,55*6,67*6,72*6),
            pos00, 0, Color.WHITE,
            (short)2, (short)21
            );
        animazioneConsumer= new Animazioni(
            rTextures.LoadTexture("texture/animazione/sconosciuto.png"),
            new Rectangle(0,0, 40, 60),
            new Rectangle(),
            pos00,0f, Color.WHITE,
            (short)10,(short)8
            );
    }

    public void paintCeste(){
        rShapes.DrawRectangleRec(cestaBaguette, Color.GOLD);
        rShapes.DrawRectangleRec(cestaBrioche, Color.BEIGE);
        rShapes.DrawRectangleRec(cestaDonut, Color.BROWN);
    }

    public void paintPartita(Semaphore lockAnimazione, Vector<Byte> statusAnimazioneConsumers){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        
        paintBackground();
        rTextures.DrawTexturePro(stagePartitaSprite, stagePartitaSrc, stagePartitaDest, pos00, 0, Color.WHITE);
        paintQuest();
        if(lockAnimazione.availablePermits()>0) {
            if (animazioneKiriko.hasAnimationEnded()) {
                lockAnimazione.drainPermits();
            }else{
                animazioneKiriko.DrawAnimazione();
                animazioneKiriko.avanti();
            }
        }
        painBancone();
        for(byte i=0; i<statusAnimazioneConsumers.size();i++){
            switch(statusAnimazioneConsumers.get(i)){
                
                case 0:
                    animazioneConsumer.setDest(164*6, 63*6, 40*12, 60*12);
                    animazioneConsumer.DrawAnimazione();
                    break;
                case 1:
                    animazioneConsumer.setDest(76*6, 63*6, 40*12, 60*12);
                    animazioneConsumer.DrawAnimazione();
                    break;
                case 2:
                    animazioneConsumer.setDest(120*6, 63*6, 40*12, 60*12);
                    animazioneConsumer.DrawAnimazione();
                    break;
                case -1:
                    break;
                default:
                    break;
            }
        }

        raylib.core.EndDrawing();
    }

    public void paintIntermezzo(int puntiTotalizzati){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        
        paintBackground();
        painBancone();
        setStagePartitaStage((byte)0);
        rTextures.DrawTexturePro(stagePartitaSprite, stagePartitaSrc, stagePartitaDest, pos00, 0, Color.WHITE);
        raylib.textures.DrawTextureEx(cartelloQuestTexture, posCartelloIntervallo, 0, 20f, Color.WHITE);
        raylib.text.DrawTextEx(font, "Congratulazioni!", new Vector2(halfScreenWidth-rText.MeasureTextEx(font, "Congratulazioni!", 70, 2).getX()*0.5f, posCartelloIntervallo.getY()+150), 70, 3, Color.WHITE);
        raylib.text.DrawTextEx(font, "Hai preso "+puntiTotalizzati+" punti pane !!", new Vector2(halfScreenWidth-rText.MeasureTextEx(font, "Hai preso "+puntiTotalizzati+" punti pane !!", 70, 2).getX()*0.5f, posCartelloIntervallo.getY()+350), 70, 3, Color.WHITE);
        raylib.text.DrawTextEx(font, "Caricando la prossima quest . . .", new Vector2(halfScreenWidth-rText.MeasureTextEx(font, "Caricando la prossima quest . . .", 70, 2).getX()*0.5f, posCartelloIntervallo.getY()+550), 70, 3, Color.WHITE);


        raylib.core.EndDrawing(); 
    }

    private void paintBackground(){
        raylib.textures.DrawTextureEx(backgrounTexture, pos00, 0, 6, Color.WHITE);
    }
    
    private void painBancone(){
        raylib.textures.DrawTextureEx(banconeTexture, pos00, 0, 6, Color.WHITE);

        raylib.textures.DrawTextureEx(cestaBaguetteTexture[cestaBaguetteStatus], cestaBaguettePos, 0, 6, Color.WHITE);
        raylib.textures.DrawTextureEx(cestaBriocheTexture[cestaBriocheStatus], cestaBriochePos, 0, 6, Color.WHITE);
        raylib.textures.DrawTextureEx(cestaDonutTexture[cestaDonutStatus], cestaDonutPos, 0, 6, Color.WHITE);

        paintCesteStatus();

        raylib.textures.DrawTextureEx(vetroTexture, pos00, 0, 6, new Color(255, 255, 255, 75));
    }

    private void paintCesteStatus(){
        raylib.shapes.DrawRectangle((int)cestaBaguette.getX(), (int)cestaBaguette.getY()-45, (int)cestaBaguette.getWidth(), 20, Color.LIGHTGRAY);
        raylib.shapes.DrawRectangle((int)cestaBrioche.getX(), (int)cestaBrioche.getY()-45, (int)cestaBrioche.getWidth(), 20, Color.LIGHTGRAY);
        raylib.shapes.DrawRectangle((int)cestaDonut.getX(), (int)cestaDonut.getY()-45, (int)cestaDonut.getWidth(), 20, Color.LIGHTGRAY);
        
        raylib.shapes.DrawRectangle((int)cestaBaguette.getX(), (int)cestaBaguette.getY()-45, (int)(percentualeCestaBaguette*cestaBaguette.getWidth()), 20, Color.GREEN);
        raylib.shapes.DrawRectangle((int)cestaBrioche.getX(), (int)cestaBrioche.getY()-45, (int)(percentualeCestaBrioche*cestaBrioche.getWidth()), 20, Color.GREEN);
        raylib.shapes.DrawRectangle((int)cestaDonut.getX(), (int)cestaDonut.getY()-45, (int)(percentualeCestaDonut*cestaDonut.getWidth()), 20, Color.GREEN);
        
        raylib.shapes.DrawRectangleLines((int)cestaBaguette.getX(), (int)cestaBaguette.getY()-45, (int)cestaBaguette.getWidth(), 20, Color.WHITE);
        raylib.shapes.DrawRectangleLines((int)cestaBrioche.getX(), (int)cestaBrioche.getY()-45, (int)cestaBrioche.getWidth(), 20, Color.WHITE);
        raylib.shapes.DrawRectangleLines((int)cestaDonut.getX(), (int)cestaDonut.getY()-45, (int)cestaDonut.getWidth(), 20, Color.WHITE);
    }

    private void paintQuest(){
        raylib.textures.DrawTextureEx(cartelloQuestTexture, poscartelloQuest,0,5f,Color.WHITE);
        raylib.textures.DrawTextureEx(donut, posLegendaDonut,0,2.5f,Color.WHITE);
        raylib.text.DrawTextEx(font, " x "+nDonut, posLegendaScrittaDonut, fontsize, 2, Color.WHITE);
        raylib.textures.DrawTextureEx(brioche, posLegendaBrioche,0,2.5f,Color.WHITE);
        raylib.text.DrawTextEx(font, " x "+nBrioche, posLegendaScrittaBrioche, fontsize, 2, Color.WHITE);
        raylib.textures.DrawTextureEx(baguette, posLegendaBaguette,0,2.5f,Color.WHITE);
        raylib.text.DrawTextEx(font, " x "+nBaguette, posLegendaScrittaBaguette, fontsize, 2, Color.WHITE);
        raylib.text.DrawTextEx(font, "Quest: ", posScrittaQuest, fontsize+5, 2, Color.WHITE);
    }


    public void setCestaBaguetteEmptiness(boolean empty){
        cestaBaguetteStatus= (empty) ? ((byte) 0) : ((byte) 1);
    }
    public void setCestaBriocheEmptiness(boolean empty){
        cestaBriocheStatus= (empty) ? ((byte) 0) : ((byte) 1);
    }
    public void setCestaDonutEmptiness(boolean empty){
        cestaDonutStatus= (empty) ? ((byte) 0) : ((byte) 1);
    }

    public void setnBaguette(int nBaguette) {
        this.nBaguette = nBaguette;
    }
    public void setnBrioche(int nBrioche) {
        this.nBrioche = nBrioche;
    }
    public void setnDonut(int nDonut) {
        this.nDonut = nDonut;
    }

    public void setStagePartitaStage(byte stage){
        stagePartitaSrc.setX(40*stage);
    }

    public void setCesteStatusPercentuale(float percentualeCestaBaguette, float percentualeCestaBrioche, float percentualeCestaDonut){
        this.percentualeCestaBaguette=percentualeCestaBaguette;
        this.percentualeCestaBrioche=percentualeCestaBrioche;
        this.percentualeCestaDonut=percentualeCestaDonut;
    }

    public Rectangle getCestaBrioche() {
        return cestaBrioche;
    }

    public Rectangle getCestaDonut() {
        return cestaDonut;
    }

    public Rectangle getCestaBaguette() {
        return cestaBaguette;
    }

    public void unload(){
        raylib.textures.UnloadTexture(vetroTexture);
        raylib.textures.UnloadTexture(banconeTexture);
        raylib.textures.UnloadTexture(backgrounTexture);
        raylib.textures.UnloadTexture(cestaBaguetteTexture[0]);
        raylib.textures.UnloadTexture(cestaBaguetteTexture[1]);
        raylib.textures.UnloadTexture(cestaBriocheTexture[0]);
        raylib.textures.UnloadTexture(cestaBriocheTexture[1]);
        raylib.textures.UnloadTexture(cestaDonutTexture[0]);
        raylib.textures.UnloadTexture(cestaDonutTexture[1]);
        raylib.textures.UnloadTexture(stagePartitaSprite);
        raylib.textures.UnloadTexture(cartelloQuestTexture);
    }
}
