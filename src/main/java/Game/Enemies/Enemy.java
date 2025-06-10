package Game.Enemies;

import Core.Scene.Entity.Component.CharacterController;
import Core.Scene.Entity.Component.Health;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Model;
import Game.CodeDefense;
import Game.Path;
import Main.Launcher;
import org.joml.Vector3f;

public class Enemy extends GameObject {

    private Path path;

    private float nanoTime = 1000000000f;
    protected float attackDelay = 1f;
    private float lastTime;

    protected Health healthComponent;
    protected CharacterController characterController;
    protected int damage;
    public Enemy(Model model) {
        super(model);
        path = new Path();
    }

    @Override
    public void start() {
        damage = 1;
        characterController = this.addComponent(CharacterController.class);
        healthComponent = addComponent(Health.class);
        healthComponent.health = 100;

        characterController.speed = 1f;
        characterController.onReached = () -> {
            Vector3f next = path.nextPointPosition();
            if (next == null){
                float currentTime = System.nanoTime();
                if(currentTime > lastTime + nanoTime * attackDelay){
                    attack();
                    lastTime = currentTime;
                }
                return;
            }
            characterController.targetPosition = next;
        };

    }

    public void attack() {
        CodeDefense game = (CodeDefense) Launcher.getGame();
        game.getTurret().takeDamage(damage);
    }
    public void takeDamage(int damage){
        healthComponent.hit(damage);
    }
    @Override
    public void update() {
        if (healthComponent.isDead()){
            CodeDefense.getGameEconomics().addPoints(20);
            scene.removeGameObject(this);
            CodeDefense.getEnemySpawner().despawn(this);
        }
    }
}
