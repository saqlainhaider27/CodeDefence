package Game;

import Core.*;
import Core.Engine.IAppLogic;
import Core.Scene.Entity.Component.CharacterController;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Material;
import Core.Scene.Entity.Mesh;
import Core.Scene.Entity.Model;
import Core.Scene.Scene;
import org.joml.Vector3f;

public class Game implements IAppLogic {
    Scene scene;
    @Override
    public void cleanup() {

    }

    @Override
    public void init() {
        scene = new Scene();
        float[] positions = new float[]{
                // VO
                -0.5f, 0.5f, 0.5f,
                // V1
                -0.5f, -0.5f, 0.5f,
                // V2
                0.5f, -0.5f, 0.5f,
                // V3
                0.5f, 0.5f, 0.5f,
                // V4
                -0.5f, 0.5f, -0.5f,
                // V5
                0.5f, 0.5f, -0.5f,
                // V6
                -0.5f, -0.5f, -0.5f,
                // V7
                0.5f, -0.5f, -0.5f,
        };
        float[] colors = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        int[] indices = new int[]{
                // Front face
                0, 1, 3, 3, 1, 2,
                // Top Face
                4, 0, 3, 5, 4, 3,
                // Right face
                3, 2, 7, 5, 3, 7,
                // Left face
                6, 1, 0, 6, 0, 4,
                // Bottom face
                2, 1, 6, 2, 6, 7,
                // Back face
                7, 6, 4, 7, 4, 5,
        };
        Mesh mesh = new Mesh(positions,colors,indices);
        Model cube = new Model(mesh, new Material());
        GameObject cubeGameObject = new GameObject(cube);
        cubeGameObject.transform.position = new Vector3f(0,0,-20);
        scene.addGameObject(cubeGameObject);
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

    public Scene getScene() {
        return scene;
    }
}
