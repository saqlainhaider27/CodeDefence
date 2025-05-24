package Core.Render;

import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Mesh;
import Core.Scene.Entity.Model;
import Core.Scene.Scene;
import Core.Engine.IO.Window;
import Main.Launcher;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;

public class Render {

    private Shader shader;

    public Render() {
    }

    public void init() {
        try {
            shader = new Shader("src/main/resources/shaders/scene.vert", "src/main/resources/shaders/scene.frag");
            createUniforms();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void cleanup() {
        // Cleanup shader resources
        shader.cleanup();
    }

    public void render(Window window, Scene scene) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glViewport(0, 0, window.getWidth(), window.getHeight());
        scene.resize(window.getWidth(), window.getHeight()); // Should not call this every render TODO: Make it only be called when resized

        // Bind the shader
        shader.bind();

        // Set the projection matrix uniform
        shader.setUniformMatrix("projectionMatrix", scene.getProjection().getProjectionMatrix());
        shader.setUniformMatrix("camera", scene.getCamera().getViewMatrix());
        // Render each GameObject
        for (GameObject gameObject : scene.getGameObjects()) {
            renderModel(gameObject.getModel(), gameObject);
        }

        // Unbind the shader
        shader.unbind();
    }

    public void renderModel(Model model, GameObject gameObject) {
        Matrix4f modelMatrix = gameObject.getModelMatrix();
        shader.setUniformMatrix("modelMatrix", modelMatrix);

        shader.setUniform("txtSampler", 0);

        for (Mesh mesh : model.getMeshes()) {
            // Bind VAO
            GL30.glBindVertexArray(mesh.getVAO());
            GL30.glEnableVertexAttribArray(0);
            GL30.glEnableVertexAttribArray(1);
            GL30.glEnableVertexAttribArray(2);

            // Bind IBO and texture
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL13.glBindTexture(GL_TEXTURE_2D, model.getMaterial().getTextureID());

            // Render the mesh
            GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);

            // Cleanup bindings
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
            GL30.glDisableVertexAttribArray(0);
            GL30.glDisableVertexAttribArray(1);
            GL30.glDisableVertexAttribArray(2);
            GL30.glBindVertexArray(0);
        }
    }


    public void createUniforms() {
        shader.createUniform("projectionMatrix");
        shader.createUniform("modelMatrix");
        shader.createUniform("txtSampler");
        shader.createUniform("camera");
    }
}