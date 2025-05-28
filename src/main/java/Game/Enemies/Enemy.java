package Game.Enemies;

import Core.Scene.Entity.Component.CharacterController;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Model;
import Game.CodeDefense;
import Game.Path;
import Main.Launcher;

public class Enemy extends GameObject {

    private Path path;

    private float nanoTime = 1000000000f;
    protected float attackDelay = 1f;
    private float lastTime;

    public Enemy(Model model) {
        super(model);
        path = new Path();
    }

    @Override
    public void start() {
        CharacterController characterController = this.addComponent(CharacterController.class);
        characterController.speed = 1f;
        characterController.onReached = () -> {
            if(path.nextPointPosition() != null){
                characterController.targetPosition = path.nextPointPosition();
            }
            else {
                float currentTime = System.nanoTime();
                if(currentTime > lastTime + nanoTime * attackDelay){
                    attack();
                    lastTime = currentTime;
                }
            }
        };
    }

    private void attack() {
        CodeDefense game = (CodeDefense) Launcher.getGame();
        game.getTurret().takeDamage(10);
    }

    @Override
    public void update() {

    }
}
