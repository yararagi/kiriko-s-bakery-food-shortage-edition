package main;

 
import java.util.concurrent.Semaphore;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;

import menu.view.MenuView;

public class Main {
    public static Raylib raylib= new Raylib(800,450,"kiriko's bakery: food shortage");
    public static void main(String[] args) {
        Semaphore gestore = new Semaphore(1);
        while (!raylib.core.WindowShouldClose()) {
            raylib.core.BeginDrawing();
            raylib.core.ClearBackground(Color.BLACK);
            //--------------------TEST---------------------
            MenuView menu = new MenuView();
            menu.paintScritte(new boolean[]{true,false,false});
            //---------------------------------------------
            raylib.core.EndDrawing();
            
        }
    }
}
