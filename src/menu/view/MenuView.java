package menu.view;

import static main.Main.raylib;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.text.Font;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import menu.model.Stato;

import com.raylib.java.core.Color;

public class MenuView {
    private byte nScritte;
    private String[] scritte;
    private byte offsetScritte;
    private byte fontSize;
    private Font fontRegular;
    private Texture2D backGround;
    private Vector2[] vociMenu;
    private Rectangle[] vociMenuBtn;
    //background image
    // boh altro

    public MenuView() {
        nScritte=3;
        offsetScritte=100;
        scritte = new String[nScritte];
        scritte[0]=Stato.GIOCA.toString().toLowerCase();
        scritte[1]=Stato.RISULTATI.toString().toLowerCase();
        scritte[2]=Stato.ESCI.toString().toLowerCase();
        fontSize=55;
        fontRegular= raylib.text.LoadFontEx("font\\MagicBreadRegular.ttf",fontSize, null, 0);
        //backGround= rTextures.LoadTexture("texture\\background\\city-town-pixel-artwork-aesthetic.jpg");
        backGround= rTextures.LoadTexture("texture\\background\\sharpen_city-town-pixel-artwork-aesthetic-1600x900-wallpx.com.jpg");
        vociMenu= new Vector2[]{new Vector2(150, 200+offsetScritte), new Vector2(150, 200+offsetScritte*2), new Vector2(150, 200+offsetScritte*3) };
        vociMenuBtn= new Rectangle[3];
        for(byte i=0; i<3;i++){
            vociMenuBtn[i]=new Rectangle(vociMenu[i].x-10, vociMenu[i].y-5, rText.MeasureTextEx(fontRegular, scritte[i], fontSize, 1).getX()+20, rText.MeasureTextEx(fontRegular, scritte[i], fontSize, 1).getY()+10);
        }
    }

    public byte getnScritte() {
        return nScritte;
    }

    private void paintScritte(byte scelta){
        for(byte i=0;i<3;i++){
            raylib.text.DrawTextEx(fontRegular, scritte[i], vociMenu[i], fontSize, 0, (i==scelta) ? (Color.WHITE) : (new Color(164, 22, 26, 150)));
        }
    }

    private void paintBackground (){
        raylib.textures.DrawTextureEx(backGround,new Vector2(0, 0), 0, 1.2f, Color.WHITE);
    }

    public void paintMenu(byte scelta){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        this.paintBackground();
        this.paintScritte(scelta);
        raylib.core.EndDrawing();
    }

    public void unload(){
        rText.UnloadFont(fontRegular);
        raylib.textures.UnloadTexture(backGround);
    }

    public Rectangle[] getVociMenuBtn() {
        return vociMenuBtn;
    }
}
