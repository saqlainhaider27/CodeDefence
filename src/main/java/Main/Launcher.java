package Main;

import Core.Engine.Engine;
import Core.Engine.IO.Window;
import Game.BaseGame;
import Game.CodeDefense;
import Utils.Consts;

public class Launcher {
    private static Window window;
    private static BaseGame game;

    public static void main(String[] args){
        game = new CodeDefense();
        window = new Window(Consts.TITLE, 1280, 720);
        Engine.getInstance().start();
        
    }
    public static Window getWindow() {
        return window;
    }
    public static BaseGame getGame() {
        return game;
    }
}
