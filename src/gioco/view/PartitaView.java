package gioco.view;


import static main.Main.raylib;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

public class PartitaView {
    private Rectangle cestaBrioche, cestaDonut, cestaBaguette;
    private Texture2D background;
    
    public PartitaView(){
        //background=rTextures.LoadTexture("");
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
        raylib.text.DrawFPS(500, 500);
        raylib.textures.DrawTexturePro(background, cestaBrioche, cestaBaguette, null, 0, null);
        raylib.core.EndDrawing(); 
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
