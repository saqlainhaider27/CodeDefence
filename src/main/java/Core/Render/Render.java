package Core.Render;

import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Mesh;
import Core.Scene.Scene;
import Core.Scene.SceneRender;
import Core.Engine.IO.Window;
import Main.Launcher;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;

public class Render {

    private SceneRender sceneRender;
    private Shader shader;
    public Render() {

    }
    public void init() {
        //sceneRender = new SceneRender();
        try {
            shader = new Shader("src/main/resources/shaders/scene.vert", "src/main/resources/shaders/scene.frag");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public void cleanup() {
        //sceneRender.cleanup();
    }

    public void render(Window window, Scene scene) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glViewport(0, 0, window.getWidth(), window.getHeight());
        shader.bind();
        for (GameObject gameObject : scene.getGameObjects()) {
            renderMesh(gameObject.getModel().getMesh());
        }
        shader.unbind();
    }
    public void renderMesh(Mesh mesh) {
        GL30.glBindVertexArray(mesh.getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
}

