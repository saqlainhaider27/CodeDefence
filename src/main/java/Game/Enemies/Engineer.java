package Game.Enemies;

import Core.Scene.Entity.Model;
import Core.Scene.Entity.Texture;
import Game.CodeDefense;
import Main.Launcher;

public class Engineer extends Enemy{
    public static final String MODEL = "src/main/resources/models/bullet/bullet.glb";
    public static final String TEXTURE = Texture.DEFAULT_TEXTURE;
    public Engineer(Model model) {
        super(model);
    }

    @Override
    public void start() {
        super.start();
        damage = 0;
        attackDelay = 3f;
    }

    @Override
    public void attack() {
        super.attack();
        CodeDefense game = (CodeDefense) Launcher.getGame();
        game.getTurret().shooter.shootDelay *= 0.95f;
    }
}
