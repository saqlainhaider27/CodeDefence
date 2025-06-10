package Game.Enemies;

import Core.Scene.Entity.Model;
import Core.Scene.Entity.Texture;

public class Boss extends Enemy{
    public static final String MODEL = "src/main/resources/models/bullet/bullet.glb";
    public static final String TEXTURE = Texture.DEFAULT_TEXTURE;
    public Boss(Model model) {
        super(model);
    }

    @Override
    public void start() {
        super.start();
        damage = 10;
        healthComponent.health = 10000;
        characterController.speed = 0.25f;
    }
}
