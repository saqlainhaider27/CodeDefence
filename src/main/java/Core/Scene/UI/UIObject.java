package Core.Scene.UI;
import Core.Scene.Entity.Transform;
import org.joml.Matrix4d;

public class UIObject {
    public Transform transform;
    public Matrix4d modelMatrix;


    public UIObject() {
        modelMatrix = new Matrix4d();
        this.transform = new Transform();
    }

    public void updateModelMatrix() {
        modelMatrix.translationRotateScale(transform.position, transform.rotation, transform.scale);
    }
    public void cleanup(){
    }
}
