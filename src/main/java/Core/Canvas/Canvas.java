package Core.Canvas;

import Core.Canvas.Elemets.UIObject;
import Utils.Generics.List;
import org.joml.Matrix4f;

public class Canvas {
    private List<UIObject> uiObjects;

    public Canvas() {
        uiObjects = new List<>();
    }
    public void addUIObject(UIObject object){
        uiObjects.add(object);
    }
    public void removeUIObject(UIObject object){
        uiObjects.remove(object);
    }

    public List<UIObject> getUiObjects() {
        return uiObjects;
    }
    public Matrix4f getOrthoProjection(int width, int height) {
        return new Matrix4f().ortho2D(0, width, height, 0);
    }

}
