package Core.Scene.Entity.Component;

import Core.Scene.Entity.GameObject;
import Utils.Interfaces.IBehaviour;
import Core.Scene.Entity.Transform;

public abstract class BaseComponent implements IBehaviour {
    public GameObject gameObject;
    public Transform transform;

    public void start(){
        transform = gameObject.transform;
    }

    public abstract void update();

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
}
