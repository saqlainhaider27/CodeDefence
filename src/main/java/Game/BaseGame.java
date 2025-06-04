package Game;

import Core.Audio.AudioManager;
import Core.Audio.SoundPlayer;
import Core.Camera;
import Core.Engine.IAppLogic;
import Core.Scene.Entity.*;
import Core.Scene.Scene;
import Core.Scene.UI.Canvas;
import Core.Scene.UI.UIObject;

public abstract class BaseGame implements IAppLogic {

    protected Scene scene;
    protected Camera camera;
    protected Canvas canvas;
    protected SoundPlayer source;

    @Override
    public void init() {
        scene = new Scene();
        canvas = new Canvas();
        camera = scene.getCamera();
        source = new SoundPlayer();
        start();
    }
    @Override
    public void loop() {
        for (GameObject gameObject : scene.getGameObjects()) {
            gameObject.updateModelMatrix();
            gameObject.loop();
        }
        for (UIObject uiObject: canvas.getUiObjects()) {
            uiObject.update();
        }
        update();
    }

    @Override
    public void cleanup() {
        for (GameObject gameObject : scene.getGameObjects()) {
            gameObject.cleanup();
        }
        for (UIObject uiObject: canvas.getUiObjects()) {
            uiObject.cleanup();
        }
        source.cleanup();
        end();
    }
    public Scene getScene() {
        return scene;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Camera getCamera() {
        return camera;
    }

    public abstract void start();
    public abstract void update();
    public abstract void end();
}