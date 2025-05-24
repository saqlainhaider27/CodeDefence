package Game;

import Core.ModelLoader;
public class CodeDefense extends BaseGame{
    @Override
    public void start() {
        camera.setPosition(0,10,20);
        // camera.setPosition(0,0,20);
        camera.setEulerRotation(30,0,0);
        scene.addGameObject(new Terrain(ModelLoader.loadModel(Terrain.MODEL, Terrain.TEXTURE)));
        scene.addGameObject(new Turret(ModelLoader.loadModel(Turret.MODEL, Turret.TEXTURE)));
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
    }
}
