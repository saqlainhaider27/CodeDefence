package Game;

import Core.Camera;
import Core.Engine.IAppLogic;
import Core.Engine.IO.Input;
import Core.ModelLoader;
import Core.Scene.Entity.*;
import Core.Scene.Entity.Component.CharacterController;
import Core.Scene.Scene;
import org.joml.Vector3f;

public class Game implements IAppLogic {

    private Scene scene;
    @Override
    public void init() {
        scene = new Scene();
        Model model = ModelLoader.loadModel("src/main/resources/models/turret/double_turret.glb");
        GameObject gameObject = new GameObject(model);
        gameObject.transform.position = new Vector3f(0, 0, -5);
        gameObject.transform.scale = 10f;
        scene.addGameObject(gameObject);

        Model cube = ModelLoader.loadModel("src/main/resources/models/cube/cube.fbx");
        GameObject cubeObject = new GameObject(cube);
        cubeObject.transform.position = new Vector3f(0, 1, -5);
        // scene.addGameObject(cubeObject);
    }

    @Override
    public void input() {
    }

    @Override
    public void update() {
        for (GameObject gameObject : scene.getGameObjects()) {
            gameObject.updateModelMatrix();
            gameObject.transform.incrementRotation(1* 0.01f,1* 0.01f,1* 0.01f, 1* 0.01f);
            gameObject.update();
        }
    }

    @Override
    public void cleanup() {
    }

    public Scene getScene() {
        return scene;
    }
}