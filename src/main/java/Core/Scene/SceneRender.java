package Core.Scene;

import Core.Scene.Entity.*;
import Core.Scene.Entity.Texture;
import Core.Render.ShaderProgram;
import Core.Render.UniformsMap;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class SceneRender {
    private ShaderProgram shaderProgram;
    private UniformsMap uniformsMap;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("src/main/resources/shaders/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("src/main/resources/shaders/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
        createUniforms();
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render(Scene scene) {
        shaderProgram.bind();
        uniformsMap.setUniform("projectionMatrix", scene.getProjection().getProjectionMatrix());
        uniformsMap.setUniform("txtSampler", 0);

        List<GameObject> gameObjects = scene.getGameObjects();

        for (GameObject gameObject : gameObjects) {
            Model model = gameObject.getModel();
            Mesh mesh = model.getMesh();
            Material material = model.getMaterial();

            // Bind texture
            Texture texture = material.getTexture();
            glActiveTexture(GL_TEXTURE0);
            texture.bind();

            // Set uniforms
            uniformsMap.setUniform("material.diffuse", material.getDiffuseColor());
            uniformsMap.setUniform("modelMatrix", gameObject.getModelMatrix());

            // Render mesh
            glBindVertexArray(mesh.getVaoId());
            glDrawElements(GL_TRIANGLES, mesh.getNumVertices(), GL_UNSIGNED_INT, 0);
        }

        glBindVertexArray(0);
        shaderProgram.unbind();
    }

    private void createUniforms() {
        uniformsMap = new UniformsMap(shaderProgram.getProgramId());
        uniformsMap.createUniform("projectionMatrix");
        uniformsMap.createUniform("modelMatrix");
        uniformsMap.createUniform("txtSampler");
        uniformsMap.createUniform("material.diffuse");
    }
}
