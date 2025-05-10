package Core.Scene.Entity;

import Utils.Generics.List;
import org.joml.Matrix4f;

public class Model {
    private Mesh mesh;
    private Material material;
    public Model(Mesh mesh, String texturePath) {
        this.mesh = mesh;
        this.material = new Material(texturePath);
        create();
    }
    public void create(){
        mesh.create();
    }


    public Material getMaterial() {
        return material;
    }

    public Mesh getMesh() {
        return mesh;
    }
    public Matrix4f getModelMatrix(){
        return new Matrix4f();
    }
}