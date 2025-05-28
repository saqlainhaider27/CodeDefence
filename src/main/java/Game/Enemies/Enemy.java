package Game.Enemies;

import Core.Scene.Entity.Component.CharacterController;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Model;
import Game.Path;

public class Enemy extends GameObject {

    private Path path;

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
            }
        };
    }

    @Override
    public void update() {

    }
}
