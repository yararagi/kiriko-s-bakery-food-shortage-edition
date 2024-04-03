package menu.view;

import static main.Main.raylib;

import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.text.Font;
import com.raylib.java.text.rText;
import com.raylib.java.textures.Image;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

import gioco.model.Stato;

import com.raylib.java.core.Color;

public class MenuView {
    private byte nScritte;
    private String[] scritte;
    private byte offsetScritte;
    private int screenX;
    private byte fontSize;
    private Font fontRegular;
    private Texture2D backGround;
    private Image backgroundIMG;
    //background image
    // boh altro

    public MenuView() {
        nScritte=3;
        offsetScritte=75;
        scritte = new String[nScritte];
        scritte[0]=Stato.GIOCA.toString().toLowerCase();
        scritte[1]=Stato.RISULTATI.toString().toLowerCase();
        scritte[2]=Stato.ESCI.toString().toLowerCase();
        fontSize=35;
        screenX=rCore.GetScreenWidth();
        fontRegular= raylib.text.LoadFont("font\\MagicBreadRegular.ttf");
        backgroundIMG=rTextures.LoadImage("texture\\background\\city-town-pixel-artwork-aesthetic.jpg");
        raylib.textures.ImageResizeNN(backgroundIMG, screenX, rCore.GetScreenHeight());
        backGround= rTextures.LoadTextureFromImage(backgroundIMG);
        rTextures.UnloadImage(backgroundIMG);
    }

    public byte getnScritte() {
        return nScritte;
    }

    public void paintScritte(byte scelta){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        raylib.textures.DrawTexture(backGround, 0, 0, Color.BLANK);
        for(byte i=0;i<3;i++){
            raylib.text.DrawTextEx(fontRegular, scritte[i], new Vector2(((screenX/2) - (int)(rText.MeasureTextEx(fontRegular, scritte[i], fontSize, 0).getX()/2)), 175+offsetScritte*i), fontSize, 0, (i==scelta) ? (new Color(164, 22, 26, 150)) : (Color.RAYWHITE));
        }
        raylib.core.EndDrawing();
    }

    public void paintBackground (){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);

        raylib.textures.DrawTexture(backGround, 0, 0, Color.WHITE);

        raylib.core.EndDrawing();
    }

    
}
