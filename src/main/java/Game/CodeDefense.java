package Game;

import Core.ModelLoader;

public class CodeDefense extends BaseGame{
    @Override
    public void start() {
        scene.addGameObject(new Turret(ModelLoader.loadModel(Turret.MODEL, Turret.TEXTURE)));

    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
    }
}
