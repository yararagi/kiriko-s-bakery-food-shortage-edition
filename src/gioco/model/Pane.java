package gioco.model;

import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;

public class Pane {
    private byte puntiPane;
    private Rectangle corpoPane;
    private Texture2D texturePane;
    private TipoPane tipoPane;
    
    public Pane(TipoPane tipoPane) {
        this.tipoPane = tipoPane;
        switch (tipoPane) {
            case BAGUETTE:
                puntiPane=5;

                break;
            case BRIOCHE:
                puntiPane=15;

                break;
            case DONUT:
                puntiPane=50;

                break;
            default:
                break;
        }
    }

    

}
