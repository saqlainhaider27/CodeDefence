package Core.Canvas.Elemets;

import Utils.Interfaces.IBehaviour;
import Core.Scene.Entity.Mesh;
import Core.Scene.Entity.Texture;
import Utils.Generics.List;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

public abstract class UIObject implements IBehaviour {

    private Texture texture;
    protected boolean visible = true;
    protected boolean enabled = true;

    private UIObject parent = null;
    private List<UIObject> children = new List<>();


    private Vector4f color;
    private Mesh mesh;

    protected Vector2f position = new Vector2f(0, 0);
    protected Vector2f scale = new Vector2f(1, 1);
    protected float rotation = 0f;
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

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }
    public void setPosition(Vector2f vector2f){
        this.position.x = vector2f.x;
        this.position.y = vector2f.y;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(float sx, float sy) {
        this.scale.set(sx, sy);
    }
    public void setScale(Vector2f vector2f){
        this.scale.x = vector2f.x;
        this.scale.y = vector2f.y;
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
        Matrix4f model = new Matrix4f().identity()
                .translate(position.x, position.y, 0)
                .rotate((float) Math.toRadians(rotation), 0, 0, 1)
                .scale(scale.x, scale.y, 1);

        if (parent != null) {
            return new Matrix4f(parent.getModelMatrix()).mul(model);
        }

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

    protected void setParent(UIObject uiObject) {
        if (this.parent != null) {
            this.parent.children.remove(this);
        }

        this.parent = uiObject;

        if (uiObject != null) {
            uiObject.children.add(this);
        }
    }

    public UIObject getParent() {
        return parent;
    }
    public List<UIObject> getChildren() {
        return children;
    }


}
