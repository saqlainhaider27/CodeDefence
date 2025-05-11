package Core;
import org.joml.*;

public class Camera {

    private Vector3f position;
    private Quaternionf rotation;
    private Matrix4f viewMatrix;

    public Camera() {
        viewMatrix = new Matrix4f();
        position = new Vector3f(0, 0, 0);
        rotation = new Quaternionf();
    }
    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }
    public Vector3f getPosition() {
        return position;
    }
    public void setPosition(Vector3f position) {
        this.position = position;
        recalculateViewMatrix();
    }
    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
        recalculateViewMatrix();
    }
    public void recalculateViewMatrix() {
        viewMatrix.identity().rotate(rotation).translate(-position.x, -position.y, -position.z);
    }
    public Quaternionf getRotation() {
        return rotation;
    }
    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
        recalculateViewMatrix();
    }
    public void setRotation(float x, float y, float z, float angle) {
        this.rotation.fromAxisAngleRad(x, y, z, angle);
        recalculateViewMatrix();
    }
    public void setEulerRotation(float x, float y, float z) {
        this.rotation.identity();
        this.rotation.rotateXYZ(x, y, z);
        recalculateViewMatrix();
    }
    public void lookAt(Vector3f target){

        recalculateViewMatrix();
    }
}