package Game.Player;

import Core.ModelLoader;
import Core.Scene.Entity.Component.Health;
import Core.Scene.Entity.Component.Shooter;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Model;
import Core.Scene.Entity.Transform;

public class Turret extends GameObject {
    public static String MODEL = "src/main/resources/models/turret/turret.fbx";
    public static String TEXTURE = "src/main/resources/models/turret/turret.png";

    public Shooter shooter;
    private Health healthComponent;

    public Turret(Model model) {
        super(model);
    }

    public Turret(Model model, Transform transform) {
        super(model, transform);
    }

    @Override
    public void start() {
        shooter = addComponent(Shooter.class);
        shooter.startShoot = true;
        shooter.shootDelay = 10f;
        shooter.onShoot = () -> {
            scene.addGameObject(new Bullet(ModelLoader.loadModel(Bullet.MODEL, Bullet.TEXTURE)));
        };

        healthComponent = addComponent(Health.class);
        healthComponent.health = 100;

        transform.setEulerRotation(-90, 0, 0);
        transform.setPosition(10,0,-10);

    }
    @Override
    public void update(){
        if (healthComponent.isDead()){
            scene.removeGameObject(this);
        }
    }
    public void takeDamage(int damage){
        healthComponent.hit(damage);
    }

}
