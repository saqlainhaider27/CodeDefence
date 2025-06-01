package Core.Scene.UI;

import Core.Scene.Entity.Mesh;
import Core.Scene.Entity.Texture;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

public abstract class UIObject {

    private Texture texture;
    protected boolean visible = true;
    protected boolean enabled = true;

    private Vector4f color;
    private Mesh mesh;

    // Position, scale, rotation for UI placement
    protected Vector2f position = new Vector2f(0, 0);  // in screen pixels
    protected Vector2f scale = new Vector2f(1, 1);
    protected float rotation = 0f;  // rotation in degrees, optional
    public UIObject(Mesh mesh) {
        this.mesh = mesh;
        color = new Vector4f(1,1,1,1);
        this.mesh.create();
    }
    public UIObject(Mesh mesh, String texturePath){
        this(mesh);
        texture = new Texture(texturePath);
    }

    public abstract void start();

    public abstract void update();

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Mesh getMesh() {
        return mesh;
    }

    // Setters and getters for position, scale, rotation
    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(float sx, float sy) {
        this.scale.set(sx, sy);
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    public void scaleMultiply(float value){
        setScale(scale.x * value, scale.y * value);
    }

    public Matrix4f getModelMatrix() {
        Matrix4f model = new Matrix4f().identity();
        model.translate(position.x, position.y, 0);
        model.rotate((float) Math.toRadians(rotation), 0, 0, 1);
        model.scale(scale.x, scale.y, 1);
        return model;
    }
    public void setColor(float r, float g, float b, float a){
        this.color.x = r;
        this.color.y = g;
        this.color.z = b;
        this.color.w = a;
    }

    public Vector4f getColor() {
        return color;
    }

    public void cleanup() {
    }

    public int getTextureID() {
        if (texture == null){
            return 0;
        }
        return texture.getTextureID();
    }
    public Texture getTexture(){
        return texture;
    }
}
