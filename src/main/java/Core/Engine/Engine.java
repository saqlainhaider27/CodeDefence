package Core.Engine;

import Core.Engine.IO.Input;
import Core.Render.Render;
import Core.Engine.IO.Window;
import Main.Launcher;
import org.lwjgl.glfw.GLFW;



public class Engine {

    public static Engine instance;
    public static Engine getInstance() {
        if (instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    public static final long NANOSECONDS = 1000000000;
    public static final long FRAMERATE = 1000;

    private static int fps;
    private static float frameTime = 1.0f / FRAMERATE;


    private IAppLogic appLogic;
    private Window window;
    private Render renderer;

    private boolean running = false;
    private Engine() {
        window = Launcher.getWindow();
        appLogic = Launcher.getGame();
        renderer = new Render();
    }
    public void start(){
        init();
        if (running) {
            return;
        }
        run();
    }

    public void stop(){
        running = false;
    }
    public void init(){
        window.init();
        appLogic.init();
        renderer.init();
    }
    public void run(){
        running = true;
        int frames = 0;
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;

        while (running) {
            boolean render = false;
            long startTime = System.nanoTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double)NANOSECONDS;
            frameCounter += passedTime;
            while (unprocessedTime >= frameTime) {
                render = true;
                unprocessedTime -= frameTime;
                if (Input.isKeyPressed(GLFW.GLFW_KEY_ESCAPE) || window.shouldClose()){
                    stop();
                }
                if(frameCounter >= NANOSECONDS){
                    fps = frames;
                    frames = 0;
                    frameCounter = 0;
                }
            }
            if (render) {
                update();
                render();
                frames++;
            }
        }

        cleanup();
    }
    private void update(){
        window.update();
        appLogic.input();
        appLogic.update();
    }
    private void render(){
        renderer.render(window, Launcher.getGame().getScene());
    }
    private void cleanup() {
        window.cleanup();
        appLogic.cleanup();
        renderer.cleanup();
    }
    private void resize() {
        Launcher.getGame().getScene().resize(window.getWidth(), window.getHeight());
    }

}


