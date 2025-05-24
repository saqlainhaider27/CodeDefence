package Game;

import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Model;
import Core.Scene.Entity.Transform;

public class Terrain extends GameObject {
    public static final String MODEL = "src/main/resources/models/ground/ground.fbx";
    public static final String TEXTURE = "src/main/resources/models/ground/ground.jpg";
    public Terrain(Model model) {
        super(model);
    }

    public Terrain(Model model, Transform transform) {
        super(model, transform);
    }

    @Override
    public void start() {
        transform.setEulerRotation(-90,0,0);
        transform.setPosition(0,-1.5f,0);
    }

    @Override
    public void update() {

    }
}
