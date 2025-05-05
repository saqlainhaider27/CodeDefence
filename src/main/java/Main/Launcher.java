package Main;

import Core.Engine.Engine;
import Core.Engine.IO.Window;
import Game.Game;
import Utils.Consts;

public class Launcher {
    private static Window window;
    private static Game game;

    public static void main(String[] args){
        game = new Game();
        window = new Window(Consts.TITLE, 1280, 720);
        Engine.getInstance().start();
    }
    public static Window getWindow() {
        return window;
    }
    public static Game getGame() {
        return game;
    }
}
