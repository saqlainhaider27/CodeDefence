package Game;

import Core.ModelLoader;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Model;

public class CodeDefense extends BaseGame{
    @Override
    public void start() {
        camera.setPosition(0,10,20);
        camera.setEulerRotation(0.5f,0,0);
        scene.addGameObject(new GameObject(ModelLoader.loadModel("src/main/resources/models/grass/grass.obj")) {
            @Override
            public void start() {
                transform.setPosition(0,-11,0);
                transform.scale = 50f;
            }

            @Override
            public void update() {

            }
        });
        scene.addGameObject(new Turret(ModelLoader.loadModel(Turret.MODEL, Turret.TEXTURE)));
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
    }
}
