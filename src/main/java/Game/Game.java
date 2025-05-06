package Game;

import Core.Engine.IAppLogic;
import Core.Scene.Entity.*;
import Core.Scene.Entity.Component.CharacterController;
import Core.Scene.Scene;
import org.joml.Vector3f;

public class Game implements IAppLogic {

    Scene scene;

    @Override
    public void init() {
        scene = new Scene();
        Mesh mesh = new Mesh(new float[]{
                -0.5f,  0.5f, 0f,
                0.5f,  0.5f, 0f,
                0.5f, -0.5f, 0f,
                -0.5f, -0.5f, 0f
        }, new int[]{
                0, 2, 1,
                0, 3, 2

        });
        mesh.create();
        GameObject player = new Player(new Model(mesh,new Material()));
        CharacterController playerController = player.addComponent(CharacterController.class, player);
        playerController.onReached = () -> {
            // System.out.println("Player reached the target");
        };
        // playerController.gameObject = player;
        playerController.targetPosition = new Vector3f(0,0,-5);

        scene.addGameObject(player);
    }

    @Override
    public void input() {

    }

    @Override
    public void update() {
        for (GameObject gameObject: scene.getGameObjects()){
            gameObject.updateModelMatrix();
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
