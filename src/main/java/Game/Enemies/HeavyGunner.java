package Game.Enemies;

import Core.Scene.Entity.Model;
import Core.Scene.Entity.Texture;

public class HeavyGunner extends Enemy{
    public static final String MODEL = "src/main/resources/models/bullet/bullet.glb";
    public static final String TEXTURE = Texture.DEFAULT_TEXTURE;
    public HeavyGunner(Model model) {
        super(model);
    }

    @Override
    public void start() {
        super.start();
        healthComponent.health = 200;
        damage = 3;
    }
}
