package menu.view;

import static main.Main.raylib;

import java.util.List;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.text.Font;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import gioco.model.Giocatore;

public class RisultatiView {
    private Font fontRegular;
    private byte fontSize;
    private byte offsetScritte;
    private int screenX;
    private Vector2 back;
    Texture2D background;
    private Rectangle backBtn;
    private Vector2[] triangoloScrollUp;
    private Vector2[] triangoloScrollDown;

    public RisultatiView(){
        background= rTextures.LoadTexture("texture/background/sharpen_city-town-pixel-artwork-aesthetic-blur-1600x900-wallpx.com.jpg");
        fontSize = 40;
        fontRegular = raylib.text.LoadFontEx("font/MagicBreadRegular.ttf",fontSize,null,0);
        screenX=rCore.GetScreenWidth();
        offsetScritte=100;
        back=new Vector2((int)((screenX/2) - (int)(rText.MeasureTextEx(fontRegular,"indietro", fontSize+15, 0).getX()/2)), rCore.GetScreenHeight()-150);
        backBtn= new Rectangle(back.x-10, back.y-10, rText.MeasureTextEx(fontRegular,"indietro", fontSize+15, 0).x+20, rText.MeasureTextEx(fontRegular,"indietro", fontSize+15, 0).y+20);
        triangoloScrollUp= new Vector2[]{new Vector2((screenX/2)+400, 689.4f), new Vector2((screenX/2)+375, 732.7f), new Vector2((screenX/2)+425, 732.7f)};
        triangoloScrollDown= new Vector2[]{new Vector2((screenX/2)+425, 757.7f), new Vector2((screenX/2)+375, 757.7f), new Vector2((screenX/2)+400, 800)};
    }

    private void paintRisultati(List<Giocatore> lista) {
        if(lista!=null && lista.size()!=0){
            for(byte i=0;i<lista.size();i++){
                raylib.text.DrawTextEx(fontRegular, lista.get(i).toString(), new Vector2(((screenX/2) - (rText.MeasureTextEx(fontRegular, lista.get(i).toString(), fontSize,0).getX()/2 )), 125+offsetScritte*i), fontSize, 0, Color.RAYWHITE);
            }
        }else{
            raylib.text.DrawTextEx(fontRegular, "nessun punteggio", new Vector2(((screenX/2) - (rText.MeasureTextEx(fontRegular,"nessun punteggio", 45,0).getX()/2)), rCore.GetScreenHeight()/2 ), 45, 0, Color.RAYWHITE);
        }
    }
    private void paintScrollGuide(boolean up, boolean down){
        if(up){
            raylib.shapes.DrawTriangle(triangoloScrollUp[0], triangoloScrollUp[1], triangoloScrollUp[2], Color.RAYWHITE);
        }
        if(down){
            raylib.shapes.DrawTriangle(triangoloScrollDown[0], triangoloScrollDown[1], triangoloScrollDown[2], Color.RAYWHITE);
        }
    }

    private void drawBackgorund(){
        raylib.textures.DrawTextureEx(background, new Vector2(0, 0), 0, 1.2f, Color.WHITE);
        raylib.shapes.DrawRectangleLines((screenX/2)-350, 75, 700, 725, Color.RAYWHITE);
        raylib.shapes.DrawRectangle((screenX/2)-350, 75, 700, 725, new Color(153, 40, 32, 75));
    }

    private void paintBack() {
        raylib.text.DrawTextEx(fontRegular, "indietro", back, fontSize+15, 0, (raylib.shapes.CheckCollisionPointRec(rCore.GetMousePosition(), backBtn)?(Color.WHITE):(new Color(164, 22, 26, 150)) ));
    }
    
    public void paintSchermataRisultati(List<Giocatore> lista, boolean up, boolean down){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        drawBackgorund();
        this.paintRisultati(lista);
        this.paintScrollGuide(up, down);
        this.paintBack();
        raylib.core.EndDrawing();
    }

    public void unload(){
        rText.UnloadFont(fontRegular);
    }

    public Rectangle getBackBtn() {
        return backBtn;
    }

    public Vector2[] getTriangoloScrollUp() {
        return triangoloScrollUp;
    }

    public Vector2[] getTriangoloScrollDown() {
        return triangoloScrollDown;
    }
}
