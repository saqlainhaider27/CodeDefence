package Core.Scene.UI;

import Utils.Generics.List;

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
}
