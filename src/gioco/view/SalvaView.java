package gioco.view;

import static main.Main.raylib;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.shapes.rShapes;
import com.raylib.java.text.Font;
import com.raylib.java.text.rText;

public class SalvaView {
    private Rectangle inserisciNome;
    private Rectangle finestra;
    private Font fontRegular;
    private byte fontSize;
    Vector2 nomeSize;

    public SalvaView(){
        fontSize= 30;
        fontRegular= raylib.text.LoadFontEx("font\\MagicBreadRegular.ttf",fontSize, null, 0);

        float width, height;
        width= rCore.GetScreenWidth()/4;
        height= rCore.GetScreenHeight()/4;

        finestra= new Rectangle((rCore.GetScreenWidth()*0.5f)-width*0.5f, (rCore.GetScreenHeight()*0.5f)-height*0.5f, rCore.GetScreenWidth()*0.25f, rCore.GetScreenHeight()*0.25f);
        
        nomeSize= rText.MeasureTextEx(fontRegular, "XXXXXXXXXXXXX", fontSize, 2);
        nomeSize.x+=50;
        nomeSize.y+=20;
        inserisciNome= new Rectangle(finestra.x+(width*0.5f-nomeSize.getX()*0.5f), finestra.y+(height*0.5f), nomeSize.getX(), nomeSize.getY());
        
    }

    public void paintFinestraSalvataggio(String nameText){
        //temp
        raylib.core.BeginDrawing();
        raylib.core.ClearBackground(Color.BLACK);
        
        //
        rShapes.DrawRectangleRec(finestra, Color.LIGHTGRAY);
        raylib.shapes.DrawRectangleLinesEx(finestra, 0.5f, Color.DARKGRAY);
        rShapes.DrawRectangleRec(inserisciNome, Color.WHITE);
        raylib.shapes.DrawRectangleLinesEx(inserisciNome, 1, Color.DARKGRAY);
        raylib.text.DrawTextEx(fontRegular, nameText, new Vector2(inserisciNome.getX()+15, inserisciNome.getY()+15), fontSize, 2, Color.BLACK);
        raylib.text.DrawTextEx(fontRegular, "NOME :", new Vector2(inserisciNome.getX()+5, inserisciNome.getY()-nomeSize.getY()), fontSize, 0, Color.BLACK);
        raylib.text.DrawTextEx(fontRegular, "PREMI 'INVIO' PER SALVARE", new Vector2(rCore.GetScreenWidth()*0.5f-rText.MeasureTextEx(fontRegular, "PREMI 'INVIO' PER SALVARE", fontSize, 0).getX()*0.5f, inserisciNome.getY()+inserisciNome.getHeight()+nomeSize.getY()), fontSize, 0, Color.BLACK);
    
        //
        raylib.core.EndDrawing();
        //
    
    }

}
