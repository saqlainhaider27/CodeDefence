package Game;

import Core.ModelLoader;
import Core.Scene.Entity.Component.Shooter;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Material;
import Core.Scene.Entity.Model;
import Core.Scene.Entity.Transform;
import org.joml.Vector3f;

public class Turret extends GameObject {
    public static String MODEL = "src/main/resources/models/turret/turret.fbx";
    public static String TEXTURE = "src/main/resources/models/turret/turret.png";

    public Shooter shooter;
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
        //transform.position = new Vector3f(0, 0, -10);
        transform.setEulerRotation(30, 0, 0);
        shooter.onShoot = () -> {
            scene.addGameObject(new Bullet(ModelLoader.loadModel(Bullet.MODEL, Bullet.TEXTURE)));
        };
    }
    @Override
    public void update(){
    }

}
