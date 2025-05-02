package Core.Scene.Entity.Component;

import Core.Scene.Entity.GameObject;

public abstract class BaseComponent {
    public GameObject gameObject;

    public abstract void start();
    public abstract void update();
}
