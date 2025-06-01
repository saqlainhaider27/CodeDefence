package Game.Player;

import Core.Scene.Entity.Component.CharacterController;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Texture;
import Core.Scene.Entity.Model;
import Core.Scene.Entity.Transform;
import Game.CodeDefense;
import Game.Enemies.Enemy;
import Main.Launcher;
import Utils.Generics.List;
import org.joml.Vector3f;

public class Bullet extends GameObject {
    public static String MODEL = "src/main/resources/models/bullet/bullet.glb";
    public static String TEXTURE = Texture.DEFAULT_TEXTURE;

    public Bullet(Model model) {
        super(model);
    }

    public Bullet(Model model, Transform transform) {
        super(model, transform);
    }

    @Override
    public void start() {
        transform.setEulerRotation(90,0,90);
        transform.scale = 0.5f;
        CharacterController bulletController = addComponent(CharacterController.class);
        // Check for enemies in the spawner list
        // Attack the closest enemy
        float previousDistance = 100000f;
        List<Enemy> enemies = CodeDefense.getEnemySpawner().getEnemies();
        Enemy target = enemies.get(0);
        for (Enemy enemy : enemies){
            float distance = transform.position.distance(enemy.transform.position);
            if (distance < previousDistance){
                target = enemy;
                previousDistance = distance;
            }
        }
        if (target != null){
            bulletController.targetPosition = new Vector3f(target.transform.position.x, target.transform.position.y, target.transform.position.z);
        }

        bulletController.speed = 10;
        Enemy finalTarget = target;
        bulletController.onReached = () -> {
            // damage the target
            if (finalTarget != null){
                finalTarget.takeDamage(100);
            }
            scene.removeGameObject(this);
        };
    }

    @Override
    public void update() {

    }
}
