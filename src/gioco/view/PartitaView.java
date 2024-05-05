package gioco.view;


import static main.Main.raylib;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

public class PartitaView {
    private Rectangle cestaBrioche, cestaDonut, cestaBaguette;
    private Texture2D banconeTexture, vetroTexture, backgrounTexture, cestaBriocheTexture[], cestaDonutTexture[], cestaBaguetteTexture[] ;
    private byte cestaBaguetteStatus,cestaDonutStatus,cestaBriocheStatus; 

    public PartitaView(){
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
        cestaBrioche= new Rectangle(0,0,100,100); 
        cestaDonut= new Rectangle(100,0,100,100);
        cestaBaguette= new Rectangle(200,0,100,100);
    }

    public void paintCeste(){
        rShapes.DrawRectangleRec(cestaBaguette, Color.GOLD);
        rShapes.DrawRectangleRec(cestaBrioche, Color.BEIGE);
        rShapes.DrawRectangleRec(cestaDonut, Color.BROWN);
    }

    public void paintPartita(){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        paintCeste();
        raylib.core.EndDrawing();
    }

    public void paintIntermezzo(){
        raylib.core.BeginDrawing();
        
        raylib.core.ClearBackground(Color.BLACK);
        
        
        raylib.core.EndDrawing(); 
    }

    public void setCestaBaguetteEmptiness(boolean empty){
        cestaBaguetteStatus= (empty) ? ((byte) 0) : ((byte) 1);
    }
    public void setCestaBriocheEmptiness(boolean empty){
        cestaBaguetteStatus= (empty) ? ((byte) 0) : ((byte) 1);
    }
    public void setCestaDonutEmptiness(boolean empty){
        cestaBaguetteStatus= (empty) ? ((byte) 0) : ((byte) 1);
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

    
}
