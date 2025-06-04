package Game;

import Core.Audio.SoundPlayer;
import Core.Camera;
import Utils.Interfaces.IAppLogic;
import Core.Scene.Entity.*;
import Core.Scene.Scene;
import Core.Canvas.Canvas;
import Core.Canvas.Elemets.UIObject;

public abstract class BaseGame implements IAppLogic {

    public Scene scene;
    public Camera camera;
    public Canvas canvas;
    public SoundPlayer source;

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

    public abstract void start();
    public abstract void update();
    public abstract void end();


}