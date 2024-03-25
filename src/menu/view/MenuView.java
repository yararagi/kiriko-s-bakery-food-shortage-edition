package menu.view;

import static main.Main.raylib;

import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.text.Font;
import com.raylib.java.core.Color;

public class MenuView {
    private short nScritte;
    private String[] scritte;
    private short offsetScritte;
    private int fontSize;
    private int screenX;
    private Font fontRegular;
    //background image
    // boh altro

    public MenuView() {
        nScritte=3;
        offsetScritte=50;
        scritte = new String[nScritte];
        scritte[0]="gioca";
        scritte[1]="crediti";
        scritte[2]="esci";
        fontSize=30;
        screenX=rCore.GetScreenWidth();
        fontRegular= raylib.text.LoadFont("..\\..\\font\\MagicBreadRegular.ttf");
    }

    public void paintScritte(boolean[] evidenziare){
        for(int i=0;i<3;i++){
            raylib.text.DrawTextEx(fontRegular, scritte[i], new Vector2((int)((screenX/2) - (raylib.text.MeasureText(scritte[i], fontSize)/2)), 175+offsetScritte*i), fontSize, 0, (evidenziare[i]) ? (new Color(164, 22, 26, 150)) : (new Color(35, 35, 35, 250)));
        }
    }

    
}
