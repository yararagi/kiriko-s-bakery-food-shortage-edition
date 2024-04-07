package menu.view;

import static main.Main.raylib;

import java.util.List;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.text.Font;
import com.raylib.java.text.rText;

import gioco.model.Giocatore;

public class RisultatiView {
    private Font fontRegular;
    private byte fontSize;
    private byte offsetScritte;
    private int screenX;

    public RisultatiView(){
        fontSize = 40;
        fontRegular = raylib.text.LoadFontEx("font\\MagicBreadRegular.ttf",fontSize,null,0);
        screenX=rCore.GetScreenWidth();
        offsetScritte=100;
    }

    private void paintRisultati(List<Giocatore> lista) {
        if(lista!=null && lista.size()!=0){
            for(byte i=0;i<lista.size();i++){
                raylib.text.DrawTextEx(fontRegular, lista.get(i).toString(), new Vector2(((screenX/2) - (rText.MeasureTextEx(fontRegular, lista.get(i).toString(), fontSize,0).getX()/2 )), 125+offsetScritte*i), fontSize, 0, Color.RAYWHITE);
            }
        }else{
            raylib.text.DrawTextEx(fontRegular, "nessun punteggio", new Vector2(((screenX/2) - (rText.MeasureTextEx(fontRegular,"nessun punteggio", 45,0).getX()/2)), rCore.GetScreenHeight()/2 ), 45, 0, Color.RAYWHITE);
        }
        raylib.shapes.DrawRectangleLines((screenX/2)-350, 75, 700, 725, Color.RAYWHITE);
    }
    private void paintScrollGuide(boolean up, boolean down){
        if(up){
            raylib.shapes.DrawTriangle(new Vector2((screenX/2)+400, 689.4f), new Vector2((screenX/2)+375, 732.7f), new Vector2((screenX/2)+425, 732.7f), Color.RAYWHITE);
        }
        if(down){
            raylib.shapes.DrawTriangle(new Vector2((screenX/2)+425, 757.7f), new Vector2((screenX/2)+375, 757.7f), new Vector2((screenX/2)+400, 800), Color.RAYWHITE);
        }
    }

    private void paintBack() {
        raylib.text.DrawTextEx(fontRegular, "indietro", new Vector2((int)((screenX/2) - (int)(raylib.text.MeasureText("indietro", fontSize+15)/2)), rCore.GetScreenHeight()-150), fontSize+15, 0, new Color(164, 22, 26, 150));
    }
    
    public void paintSchermataRisultati(List<Giocatore> lista, boolean up, boolean down){
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        this.paintRisultati(lista);
        this.paintScrollGuide(up, down);
        this.paintBack();
        raylib.core.EndDrawing();
    }

    public void unload(){
        rText.UnloadFont(fontRegular);
    }
}
