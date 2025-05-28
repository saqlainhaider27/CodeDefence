package Game.Enemies;

import Core.Scene.Entity.Model;
import Core.Scene.Entity.Texture;

public class Scout extends Enemy{
    public static final String MODEL = "src/main/resources/models/bullet/bullet.glb";
    public static final String TEXTURE = Texture.DEFAULT_TEXTURE;
    public Scout(Model model) {
        super(model);
    }
}
