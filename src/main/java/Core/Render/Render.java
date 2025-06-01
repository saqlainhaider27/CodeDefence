package Core.Render;

import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Mesh;
import Core.Scene.Entity.Model;
import Core.Scene.Scene;
import Core.Engine.IO.Window;
import Core.Scene.UI.Canvas;
import Core.Scene.UI.UIObject;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;

public class Render {

    private Shader sceneShader;
    private Shader canvasShader;

    public Render() {
    }

    public void init() {
        try {
            sceneShader = new Shader("src/main/resources/shaders/scene.vert", "src/main/resources/shaders/scene.frag");
            canvasShader = new Shader("src/main/resources/shaders/canvas.vert", "src/main/resources/shaders/canvas.frag");
            createSceneUniforms();
            createCanvasUniforms();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void cleanup() {
        // Cleanup shader resources
        sceneShader.cleanup();
    }

    public void render(Window window, Scene scene, Canvas canvas) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glViewport(0, 0, window.getWidth(), window.getHeight());
        scene.resize(window.getWidth(), window.getHeight()); // TODO: Only on resize

        sceneShader.bind();


        sceneShader.setUniformMatrix("projectionMatrix", scene.getProjection().getProjectionMatrix());
        sceneShader.setUniformMatrix("camera", scene.getCamera().getViewMatrix());

        for (GameObject gameObject : scene.getGameObjects()) {
            renderModel(gameObject.getModel(), gameObject);
        }
        canvasShader.bind();
        Matrix4f orthoProj = canvas.getOrthoProjection(window.getWidth(), window.getHeight());
        canvasShader.setUniformMatrix("projectionMatrix", orthoProj);
        canvasShader.setUniformMatrix("camera", new Matrix4f().identity());

        for (UIObject uiObject : canvas.getUiObjects()) {
            renderUIObject(uiObject);
        }

        sceneShader.unbind();
        canvasShader.unbind();
    }


    public void renderModel(Model model, GameObject gameObject) {
        Matrix4f modelMatrix = gameObject.getModelMatrix();
        sceneShader.setUniformMatrix("modelMatrix", modelMatrix);

        sceneShader.setUniform("txtSampler", 0);

        for (Mesh mesh : model.getMeshes()) {
            GL30.glBindVertexArray(mesh.getVAO());
            GL30.glEnableVertexAttribArray(0);
            GL30.glEnableVertexAttribArray(1);
            GL30.glEnableVertexAttribArray(2);

            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL13.glBindTexture(GL_TEXTURE_2D, model.getMaterial().getTextureID());

            GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);

            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
            GL30.glDisableVertexAttribArray(0);
            GL30.glDisableVertexAttribArray(1);
            GL30.glDisableVertexAttribArray(2);
            GL30.glBindVertexArray(0);
        }
    }
    public void renderUIObject(UIObject uiObject) {
        if (!uiObject.isVisible() || !uiObject.isEnabled()) return;

        Mesh mesh = uiObject.getMesh();

        canvasShader.setUniformMatrix("modelMatrix", uiObject.getModelMatrix());
        canvasShader.setUniform("baseColor", new Vector4f(1.0f, 0.5f, 0.2f, 1.0f)); // orange

        GL30.glBindVertexArray(mesh.getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());

        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }



    public void createSceneUniforms() {
        sceneShader.createUniform("projectionMatrix");
        sceneShader.createUniform("modelMatrix");
        sceneShader.createUniform("txtSampler");
        sceneShader.createUniform("camera");
    }
    public void createCanvasUniforms() {
        canvasShader.createUniform("projectionMatrix");
        canvasShader.createUniform("modelMatrix");
        canvasShader.createUniform("camera");
        canvasShader.createUniform("baseColor");
    }
}