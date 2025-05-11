package Game;

import Core.ModelLoader;
import Core.Scene.Entity.GameObject;

public class CodeDefense extends BaseGame{
    Turret turret;

    @Override
    public void start() {
        turret = new Turret(ModelLoader.loadModel(Turret.MODEL, Turret.TEXTURE));
        turret.shooter.onShoot = () -> {
          Bullet bullet = new Bullet(ModelLoader.loadModel(Bullet.MODEL, Bullet.TEXTURE));
          scene.addGameObject(bullet);
          System.out.println("Added bullet to scene");
        };
        scene.addGameObject(turret);
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
    }
}
