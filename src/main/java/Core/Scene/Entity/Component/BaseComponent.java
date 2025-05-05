package Core.Scene.Entity.Component;

import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.IBehaviour;

public abstract class BaseComponent implements IBehaviour {
    public GameObject gameObject;

    public abstract void start();
    public abstract void update();
}
