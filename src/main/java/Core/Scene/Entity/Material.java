package Core.Scene.Entity;

import org.joml.Vector4f;

public class Material {

    private Vector4f diffuseColor;
    private Texture texture;

    public static final Vector4f DEFAULT_COLOR = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);

    public Material() {
        diffuseColor = DEFAULT_COLOR;
        texture = new Texture();
    }

    public Material(Vector4f diffuseColor, Texture texture) {
        this.diffuseColor = diffuseColor;
        this.texture = texture;
    }
    public Material(Texture texture){
        this.diffuseColor = DEFAULT_COLOR;
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector4f getDiffuseColor() {
        return diffuseColor;
    }
    public void setDiffuseColor(Vector4f diffuseColor) {
        this.diffuseColor = diffuseColor;
    }
}
