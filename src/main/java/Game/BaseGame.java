package Game;

import Core.Camera;
import Core.Engine.IAppLogic;
import Core.Scene.Entity.*;
import Core.Scene.Scene;

public abstract class BaseGame implements IAppLogic {

    protected Scene scene;
    protected Camera camera;
    @Override
    public void init() {
        scene = new Scene();
        camera = scene.getCamera();
        start();
    }
    @Override
    public void loop() {
        for (GameObject gameObject : scene.getGameObjects()) {
            gameObject.updateModelMatrix();
            gameObject.loop();
        }
        update();
    }

    @Override
    public void cleanup() {
        for (GameObject gameObject : scene.getGameObjects()) {
            gameObject.cleanup();
        }
        end();
    }
    public Scene getScene() {
        return scene;
    }
    public abstract void start();
    public abstract void update();
    public abstract void end();
}