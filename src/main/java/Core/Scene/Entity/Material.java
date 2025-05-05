package Core.Scene.Entity;

import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;

public class Material {

    private Vector4f diffuseColor;
    private Texture texture;

    public static final Vector4f DEFAULT_COLOR = new Vector4f(1.0f, 0.0f, 0.0f, 1.0f);

    public Material() {
        diffuseColor = DEFAULT_COLOR;
        texture = new Texture();
    }
    public Material(String path) {
        try {
            texture = (Texture) TextureLoader.getTexture(path.split("[.]")[1], Material.class.getResourceAsStream(path), GL11.GL_LINEAR);
        } catch (IOException e) {
            System.out.println("Can't load texture at: " + path);
        }
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
