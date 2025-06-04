package Game.Player;

import Core.Audio.SoundLoader;
import Core.ModelLoader;
import Core.Scene.Entity.Component.Health;
import Core.Scene.Entity.Component.Shooter;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Model;
import Core.Scene.Entity.Transform;
import Core.Scene.UI.Slider;
import Core.Scene.UI.SliderFill;
import Game.CodeDefense;
import org.joml.Vector3f;

public class Turret extends GameObject {
    public static String MODEL = "src/main/resources/models/turret/turret.fbx";
    public static String TEXTURE = "src/main/resources/models/turret/turret.png";

    public Shooter shooter;
    private Health healthComponent;
    private Slider healthBar;

    public Turret(Model model) {
        super(model);
    }

    public Turret(Model model, Transform transform) {
        super(model, transform);
    }

    @Override
    public void start() {

        healthBar = new Slider();

        shooter = addComponent(Shooter.class);
        shooter.startShoot = true;
        shooter.shootPoint = new Vector3f(7.5f,0,-10);
        shooter.shootDelay = 3f;
        shooter.onShoot = () -> {
            if (CodeDefense.getEnemySpawner().getEnemies().isEmpty()){
                return;
            }
            SoundLoader.SoundData laserShoot = SoundLoader.loadSound("src/main/resources/audio/laser_shoot.ogg");
            source.load(laserShoot);
            source.play();
            Bullet bullet = new Bullet(ModelLoader.loadModel(Bullet.MODEL, Bullet.TEXTURE), new Transform(new Vector3f(shooter.shootPoint.x,
                    shooter.shootPoint.y,
                    shooter.shootPoint.z)));
            scene.addGameObject(bullet);
        };


        healthComponent = addComponent(Health.class);
        healthComponent.maxHealth = 100;
        healthComponent.health = 100;

        transform.setEulerRotation(-90, 0, 0);
        transform.setPosition(10,0,-10);


        healthBar.setScale(200,25);
        healthBar.setPosition(850,150);

        canvas.addUIObject(healthBar);

    }
    @Override
    public void update(){
        if (healthComponent.isDead()){
            scene.removeGameObject(this);
        }
    }
    public void takeDamage(int damage){
        SoundLoader.SoundData metalHit = SoundLoader.loadSound("src/main/resources/audio/metal_hit.ogg");
        source.load(metalHit);
        source.play();
        healthComponent.hit(damage);
        healthBar.setFillAmount((float) healthComponent.health / healthComponent.maxHealth);

    }

}
