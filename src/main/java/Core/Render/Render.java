package Core.Render;

import Core.Scene.Scene;
import Core.Scene.SceneRender;
import Core.Engine.IO.Window;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;

public class Render {

    private SceneRender sceneRender;

    public Render() {

    }
    public void init() {
        sceneRender = new SceneRender();
    }
    public void cleanup() {
        sceneRender.cleanup();
    }

    public void render(Window window, Scene scene) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glViewport(0, 0, window.getWidth(), window.getHeight());
        sceneRender.render(scene);
    }
}

