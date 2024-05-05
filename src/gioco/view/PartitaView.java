package gioco.view;


import static main.Main.raylib;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.text.Font;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

public class PartitaView {
    private Rectangle cestaBrioche, cestaDonut, cestaBaguette, stagePartitaSrc, stagePartitaDest;
    private Texture2D stagePartitaSprite, banconeTexture, vetroTexture, backgrounTexture, cestaBriocheTexture[], cestaDonutTexture[], cestaBaguetteTexture[], baguette, brioche, donut ;
    private byte cestaBaguetteStatus, cestaDonutStatus, cestaBriocheStatus, fontsize;
    private int nBrioche, nBaguette, nDonut;
    private Vector2 cestaBriochePos, cestaBaguettePos, cestaDonutPos, pos00, posLegendaBaguette, posLegendaBrioche, posLegendaDonut, posLegendaScrittaBrioche, posLegendaScrittaBaguette, posLegendaScrittaDonut ;
    private Font font;
    

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
        posLegendaDonut=new Vector2(30, 40);
        posLegendaScrittaDonut=new Vector2(100, 50);
        posLegendaBrioche=new Vector2(30, 90);
        posLegendaScrittaBrioche=new Vector2(100, 100);
        posLegendaBaguette=new Vector2(30, 140);
        posLegendaScrittaBaguette=new Vector2(100, 150);
    }

    public void paintCeste(){
        rShapes.DrawRectangleRec(cestaBaguette, Color.GOLD);
        rShapes.DrawRectangleRec(cestaBrioche, Color.BEIGE);
        rShapes.DrawRectangleRec(cestaDonut, Color.BROWN);
    }

    public void paintPartita(){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        
        paintBackground();
        painBancone();
        rTextures.DrawTexturePro(stagePartitaSprite, stagePartitaSrc, stagePartitaDest, pos00, 0, Color.WHITE);
        paintQuest();

        raylib.core.EndDrawing();
    }

    public void paintIntermezzo(){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        
        paintBackground();
        painBancone();
        setStagePartitaStage((byte)0);
        rTextures.DrawTexturePro(stagePartitaSprite, stagePartitaSrc, stagePartitaDest, pos00, 0, Color.WHITE);
        
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

        raylib.textures.DrawTextureEx(vetroTexture, pos00, 0, 6, new Color(255, 255, 255, 75));
    }

    private void paintQuest(){
        raylib.textures.DrawTextureEx(donut, posLegendaDonut,0,2.5f,Color.WHITE);
        raylib.text.DrawTextEx(font, " x "+nDonut, posLegendaScrittaDonut, fontsize, 2, Color.WHITE);
        raylib.textures.DrawTextureEx(brioche, posLegendaBrioche,0,2.5f,Color.WHITE);
        raylib.text.DrawTextEx(font, " x "+nBrioche, posLegendaScrittaBrioche, fontsize, 2, Color.WHITE);
        raylib.textures.DrawTextureEx(baguette, posLegendaBaguette,0,2.5f,Color.WHITE);
        raylib.text.DrawTextEx(font, " x "+nBaguette, posLegendaScrittaBaguette, fontsize, 2, Color.WHITE);
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
    }
}
