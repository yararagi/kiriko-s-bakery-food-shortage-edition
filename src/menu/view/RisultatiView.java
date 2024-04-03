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
        fontRegular = raylib.text.LoadFont("font\\MagicBreadRegular.ttf");
        fontSize = 30;
        screenX=rCore.GetScreenWidth();
        offsetScritte=75;
    }

    public void paintRisultati(List<Giocatore> lista) {
        if(lista!=null && lista.size()!=0){
            for(byte i=0;i<lista.size();i++){
                raylib.text.DrawTextEx(fontRegular, lista.get(i).toString(), new Vector2(((rCore.GetScreenWidth()/2) - (rText.MeasureTextEx(fontRegular, lista.get(i).toString(), fontSize,0).getX()/2 )), 75+offsetScritte*i), fontSize, 0, Color.RAYWHITE);
            }
        }else{
            raylib.text.DrawTextEx(fontRegular, "nessun punteggio", new Vector2(((rCore.GetScreenWidth()/2) - (rText.MeasureTextEx(fontRegular,"nessun punteggio", fontSize,0).getX()/2)), rCore.GetScreenHeight()/2 ), 45, 0, Color.RAYWHITE);
        }
    }

    public void paintBack() {
        raylib.core.BeginDrawing();

        raylib.core.ClearBackground(Color.BLACK);
        raylib.text.DrawTextEx(fontRegular, "indietro", new Vector2((int)((screenX/2) - (int)(raylib.text.MeasureText("indietro", fontSize)/2)), rCore.GetScreenHeight()-75), fontSize, 0,(new Color(164, 22, 26, 150)));
        
        raylib.core.EndDrawing();
    }
    
}
