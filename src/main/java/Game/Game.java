package Game;

import Core.Camera;
import Core.Engine.IAppLogic;
import Core.Engine.IO.Input;
import Core.Scene.Entity.*;
import Core.Scene.Entity.Component.CharacterController;
import Core.Scene.Scene;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Game implements IAppLogic {

    private Scene scene;
    @Override
    public void init() {
        scene = new Scene();

        Mesh mesh = new Mesh(new float[]{
                -0.5f,  0.5f, 0f,
                0.5f,  0.5f, 0f,
                0.5f, -0.5f, 0f,
                -0.5f, -0.5f, 0f
        }, new float[]{
                0f, 0f,  // Top-left
                1f, 0f,  // Top-right
                1f, 1f,  // Bottom-right
                0f, 1f   // Bottom-left
        }, new int[]{
                0, 2, 1,
                0, 3, 2
        });
        GameObject player = new Player(new Model(mesh, new Material("src/main/resources/models/cube/cube.png")));
        CharacterController playerController = player.addComponent(CharacterController.class, player);
        playerController.targetPosition = new Vector3f(0, 0, -10);
        Mesh mesh2 = new Mesh(new float[]{
                -0.5f,  0.5f, 0f,
                0.5f,  0.5f, 0f,
                0.5f, -0.5f, 0f,
                -0.5f, -0.5f, 0f
        }, new float[]{
                0f, 0f,  // Top-left
                1f, 0f,  // Top-right
                1f, 1f,  // Bottom-right
                0f, 1f   // Bottom-left
        }, new int[]{
                0, 2, 1,
                0, 3, 2
        });
        GameObject player2 = new Player(new Model(mesh2, new Material("src/main/resources/models/cube/cube.png")));
        CharacterController playerController2 = player2.addComponent(CharacterController.class, player2);
        playerController2.targetPosition = new Vector3f(0, 1, -10);

        scene.addGameObject(player);
        scene.addGameObject(player2);
    }

    @Override
    public void input() {
    }

    @Override
    public void update() {
        for (GameObject gameObject : scene.getGameObjects()) {
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