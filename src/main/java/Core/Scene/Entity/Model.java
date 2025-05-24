package Core.Scene.Entity;

import Utils.Generics.List;
import org.joml.Matrix4f;

public class Model {
    private List<Mesh> meshes;
    private Material material;
    public Model(String texturePath, Mesh ... meshArray) {
        meshes = new List<>();
        for (Mesh mesh : meshArray){
            meshes.add(mesh);
        }
        this.material = new Material(texturePath);
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
        this.material = new Material(texturePath);
        create();
    }
    public void create(){
        for (Mesh mesh: meshes) {
            mesh.create();
        }
    }
    public Material getMaterial() {
        return material;
    }
    public Matrix4f getModelMatrix(){
        return new Matrix4f();
    }

    public void cleanup() {
        for (Mesh mesh: meshes) {
            mesh.cleanup();
        }
        material.cleanup();
    }
    public List<Mesh> getMeshes() {
        return meshes;
    }
}