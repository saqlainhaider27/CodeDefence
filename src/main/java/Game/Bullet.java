package Game;

import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Material;
import Core.Scene.Entity.Model;
import Core.Scene.Entity.Transform;

public class Bullet extends GameObject {
    public static String MODEL = "src/main/resources/models/astronaut/astronaut.fbx";
    public static String TEXTURE = Material.DEFAULT_TEXTURE;

    public Bullet(Model model) {
        super(model);
    }

    public Bullet(Model model, Transform transform) {
        super(model, transform);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        transform.incrementPosition(0.1f, 0, 0);
    }
}
