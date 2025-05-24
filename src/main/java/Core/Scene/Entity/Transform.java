package Core.Scene.Entity;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform {
    public Vector3f position;
    public Quaternionf rotation;
    public float scale;

    public Transform(Vector3f position, Quaternionf rotation, float scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Transform() {
        this(new Vector3f(), new Quaternionf(), 1.0f);
    }
    public final void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }
    public void setRotation(float x, float y, float z, float angle) {
        this.rotation.fromAxisAngleRad(x, y, z, angle);
    }
    public void incrementRotation(float x, float y, float z) {
        this.rotation.rotateXYZ(x, y, z);
    }
    public void incrementPosition(float x, float y, float z) {
        this.position.x += x;
        this.position.y += y;
        this.position.z += z;
    }
    public void incrementPosition(Vector3f vector) {
        incrementPosition(vector.x, vector.y, vector.z);
    }
    public void setScale(float scale) {
        this.scale = scale;
    }
    public void setEulerRotation(float pitch, float yaw, float roll) {
        float pitchRad = (float) Math.toRadians(pitch);
        float yawRad = (float) Math.toRadians(yaw);
        float rollRad = (float) Math.toRadians(roll);
        this.rotation.identity();

        this.rotation.rotateX(pitchRad)
                .rotateY(yawRad)
                .rotateZ(rollRad);
    }


}
