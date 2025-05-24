package Game;

import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Model;
import Core.Scene.Entity.Transform;

public class Terrain extends GameObject {
    public static final String MODEL = "src/main/resources/models/ground/ground.fbx";
    public static final String TEXTURE = "src/main/resources/models/ground/ground.png";
    public Terrain(Model model) {
        super(model);
    }

    public Terrain(Model model, Transform transform) {
        super(model, transform);
    }

    @Override
    public void start() {
        transform.scale = 2f;
        transform.setPosition(0,-0.8f,-10);
    }

    @Override
    public void update() {

    }
}
