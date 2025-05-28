package Core.Scene.Entity;

import Utils.Generics.List;
import org.joml.Matrix4f;

public class Model {
    private List<Mesh> meshes;
    private Texture texture;
    public Model(String texturePath, Mesh ... meshArray) {
        meshes = new List<>();
        for (Mesh mesh : meshArray){
            meshes.add(mesh);
        }
        this.texture = new Texture(texturePath);
        create();
    }

    public Model(List<Mesh> meshes) {
        this.meshes = meshes;
        create();
    }

    public Model(String texturePath, List<Mesh> meshList){
        meshes = new List<>();
        for (Mesh mesh : meshList){
            meshes.add(mesh);
        }
        this.texture = new Texture(texturePath);
        create();
    }
    public void create(){
        for (Mesh mesh: meshes) {
            mesh.create();
        }
    }
    public Texture getMaterial() {
        return texture;
    }
    public Matrix4f getModelMatrix(){
        return new Matrix4f();
    }

    public void cleanup() {
        for (Mesh mesh: meshes) {
            mesh.cleanup();
        }
        texture.cleanup();
    }
    public List<Mesh> getMeshes() {
        return meshes;
    }
}