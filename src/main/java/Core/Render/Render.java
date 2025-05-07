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
            // Initialize the required uniforms in the shader
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

        // Bind the shader
        shader.bind();

        // Set the projection matrix uniform
        shader.setUniformMatrix("projectionMatrix", scene.getProjection().getProjectionMatrix());

        // Render each GameObject
        for (GameObject gameObject : scene.getGameObjects()) {
            renderModel(gameObject.getModel(), gameObject);
        }

        // Unbind the shader
        shader.unbind();
    }

    public void renderModel(Model model, GameObject gameObject) {
        // Set the modelMatrix uniform for the specific game object
        Matrix4f modelMatrix = gameObject.getModelMatrix();
        shader.setUniformMatrix("modelMatrix", modelMatrix);

        // Set the texture sampler uniform
        shader.setUniform("txtSampler", 0); // Texture is bound to texture unit 0

        // Bind VAO
        GL30.glBindVertexArray(model.getMesh().getVAO());
        GL30.glEnableVertexAttribArray(0); // Vertex positions
        GL30.glEnableVertexAttribArray(1); // Texture coordinates

        // Bind IBO and texture
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.getMesh().getIBO());
        GL13.glActiveTexture(GL13.GL_TEXTURE0); // Activate texture unit 0
        GL13.glBindTexture(GL_TEXTURE_2D, model.getMaterial().getTextureID());

        // Render the mesh
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);

        // Cleanup bindings
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    public void createUniforms() {
        shader.createUniform("projectionMatrix"); // For the camera projection
        shader.createUniform("modelMatrix");      // For the object's model transformation
        shader.createUniform("txtSampler");       // For the texture sampler
    }
}