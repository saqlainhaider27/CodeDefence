package Game.Player;

import Core.Scene.Entity.Component.CharacterController;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Material;
import Core.Scene.Entity.Model;
import Core.Scene.Entity.Transform;
import org.joml.Vector3f;

public class Bullet extends GameObject {
    public static String MODEL = "src/main/resources/models/bullet/bullet.glb";
    public static String TEXTURE = Material.DEFAULT_TEXTURE;

    public Bullet(Model model) {
        super(model);
    }

    public Bullet(Model model, Transform transform) {
        super(model, transform);
    }

    @Override
    public void start() {
        transform.position = new Vector3f(0, 0, -10);
        CharacterController bulletController = addComponent(CharacterController.class);
        bulletController.targetPosition = new Vector3f(10, 10, 10);
        bulletController.speed = 5;
        bulletController.onReached = () -> {
            scene.removeGameObject(this);
        };
    }

    @Override
    public void update() {

    }
}
