package Game.Enemies;

import Core.Scene.Entity.Model;
import Core.Scene.Entity.Texture;

public class Scout extends Enemy{
    public static final String MODEL = "src/main/resources/models/scout/scout.fbx";
    public static final String TEXTURE = "src/main/resources/models/scout/scout.jpeg";
    public Scout(Model model) {
        super(model);
    }

    @Override
    public void start() {
        super.start();
        healthComponent.health = 50;
        characterController.speed = 2f;
        damage = 1;
    }
}
