package menu.view;

import static main.Main.raylib;

import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.text.Font;

import gioco.model.Stato;

import com.raylib.java.core.Color;

public class MenuView {
    private byte nScritte;
    private String[] scritte;
    private byte offsetScritte;
    private int screenX;
    private byte fontSize;
    private Font fontRegular;
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
    }

    public byte getnScritte() {
        return nScritte;
    }

    public void paintScritte(byte scelta){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        for(byte i=0;i<3;i++){
            raylib.text.DrawTextEx(fontRegular, scritte[i], new Vector2(((screenX/2) - (raylib.text.MeasureText(scritte[i], fontSize)/2)), 175+offsetScritte*i), fontSize, 0, (i==scelta) ? (new Color(164, 22, 26, 150)) : (Color.RAYWHITE));
        }
        raylib.core.EndDrawing();
    }

    
}
