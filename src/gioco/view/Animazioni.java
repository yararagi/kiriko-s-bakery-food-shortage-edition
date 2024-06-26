package gioco.view;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;

public class Animazioni {
    private Texture2D texture;
    private Rectangle src, dest;
    private float destOgX; 
    private Vector2 origin; 
    private float rotation; 
    private Color tint ;
    private short framesPerSecond, howManyFrames, currentFrame;
    private double tempoInizio;


    public Animazioni(Texture2D texture, Rectangle src, Rectangle dest, Vector2 origin, float rotation, Color tint,
            short framesPerSecond, short howManyFrames) {
        this.texture = texture;
        this.src = src;
        this.dest = dest;
        this.destOgX=dest.x;
        this.origin = origin;
        this.rotation = rotation;
        this.tint = tint;
        this.framesPerSecond = framesPerSecond;
        this.howManyFrames = howManyFrames;
        this.currentFrame=-1;
        tempoInizio=0;
    }

    public static void DrawAnimazione(Animazioni animazione){
        short frame= (short)((rCore.GetTime()*animazione.framesPerSecond)%animazione.howManyFrames);
        animazione.src.x=0+frame*animazione.src.width;
        rTextures.DrawTexturePro(animazione.texture, animazione.src, animazione.dest, animazione.origin, animazione.rotation, animazione.tint);
    }
    
    public static void DrawAnimazione(Texture2D texture, short framesPerSecond, short howManyFrames, Rectangle src, Rectangle dest, Vector2 origin, float rotation, Color tint ){
        short frame= (short)((rCore.GetTime()*framesPerSecond)%howManyFrames);
        src.x=0+frame*src.width;
        rTextures.DrawTexturePro(texture, src, dest, origin, rotation, tint);
    }

    public void DrawAnimazione(){
        if(currentFrame==-1){
            tempoInizio=rCore.GetTime();
            currentFrame=0;
            this.dest.x=this.destOgX;
        }
        if(currentFrame>=this.howManyFrames){
            this.dest.x=this.destOgX;
            return;
        }
        currentFrame= (short)(((rCore.GetTime()-tempoInizio)*this.framesPerSecond)%this.howManyFrames);
        this.src.x=0+currentFrame*this.src.width;
        rTextures.DrawTexturePro(this.texture, this.src, this.dest, this.origin, this.rotation, this.tint);
    }

    public void avanti(){
        this.dest.x-=290f*rCore.GetFrameTime();
    }

    public boolean hasAnimationEnded(){
        if(currentFrame>=this.howManyFrames-1 && currentFrame!=-1){
            currentFrame=-1;
            return true;
        }
        return false ;   
    }

    public void setDest(float x, float y, float w, float h) {
        dest.x=x;
        dest.y=y;
        dest.width=w;
        dest.height=h;
    }
}
