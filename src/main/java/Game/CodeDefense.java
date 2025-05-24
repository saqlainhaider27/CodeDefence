package Game;

import Core.ModelLoader;
public class CodeDefense extends BaseGame{
    @Override
    public void start() {
        camera.setPosition(0,10,20);
        camera.setEulerRotation(0.5f,0,0);
        scene.addGameObject(new Turret(ModelLoader.loadModel(Turret.MODEL, Turret.TEXTURE)));
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
    }
}
