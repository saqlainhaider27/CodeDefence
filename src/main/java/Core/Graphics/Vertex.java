package Core.Graphics;

import org.joml.Vector3f;

public class Vertex {
    private Vector3f position;

    public Vertex() {
        this(new Vector3f());
    }
    public Vertex(Vector3f position) {
        this.position = position;
    }
    public Vertex(float x, float y, float z) {
        this(new Vector3f(x, y, z));
    }
    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);
    }
    public void setPosition(Vector3f position) {
        this.position = position;
    }
    public Vector3f getPosition() {
        return position;
    }
}
