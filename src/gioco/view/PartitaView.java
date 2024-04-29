package gioco.view;

import com.raylib.java.core.Color;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;

public class PartitaView {
    private Rectangle cestaBrioche, cestaDonut, cestaBaguette;
    
    public PartitaView(){
        cestaBrioche= new Rectangle(0,0,100,100); 
        cestaDonut= new Rectangle(100,0,100,100);
        cestaBaguette= new Rectangle(200,0,100,100);
    }

    public void paintCeste(){
        rShapes.DrawRectangleRec(cestaBaguette, Color.GOLD);
        rShapes.DrawRectangleRec(cestaBrioche, Color.BEIGE);
        rShapes.DrawRectangleRec(cestaDonut, Color.BROWN);
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
