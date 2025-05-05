package Core.Scene;

import Core.Scene.Entity.*;
import Core.Scene.Entity.Texture;
import Core.Render.ShaderProgram;
import Core.Render.UniformsMap;
import Utils.Generics.List;

import org.lwjgl.opengl.*;


public class SceneRender {
    private ShaderProgram shaderProgram;
    private UniformsMap uniformsMap;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList =  new List<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("src/main/resources/shaders/scene.vert", GL20.GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("src/main/resources/shaders/scene.frag", GL20.GL_FRAGMENT_SHADER));
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
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            texture.bind();

            // Set uniforms
            uniformsMap.setUniform("material.diffuse", material.getDiffuseColor());
            uniformsMap.setUniform("modelMatrix", gameObject.getModelMatrix());

            // Render mesh
            GL30.glBindVertexArray(mesh.getVaoId());
            GL20.glEnableVertexAttribArray(0);
            GL30.glDrawElements(GL20.GL_TRIANGLES, mesh.getNumVertices(), GL11.GL_UNSIGNED_INT, 0);
        }

        GL30.glBindVertexArray(0);
        GL20.glDisableVertexAttribArray(0);
        shaderProgram.unbind();
    }
    public void render(Mesh mesh) {
        GL30.glBindVertexArray(mesh.getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
    private void createUniforms() {
        uniformsMap = new UniformsMap(shaderProgram.getProgramId());
        uniformsMap.createUniform("projectionMatrix");
        uniformsMap.createUniform("modelMatrix");
        uniformsMap.createUniform("txtSampler");
        uniformsMap.createUniform("material.diffuse");
    }
}
