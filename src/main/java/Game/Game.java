package Game;

import Core.Engine.IAppLogic;
import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Material;
import Core.Scene.Entity.Mesh;
import Core.Scene.Entity.Model;
import Core.Scene.Scene;
import org.joml.Vector3f;



public class Game implements IAppLogic {

    Scene scene;

    @Override
    public void init() {
        scene = new Scene();

        float[] positions = {
                // Front face
                -0.5f,  0.5f,  0.5f,  // Top-left
                -0.5f, -0.5f,  0.5f,  // Bottom-left
                0.5f, -0.5f,  0.5f,  // Bottom-right
                0.5f,  0.5f,  0.5f,  // Top-right
                // Right face
                0.5f,  0.5f,  0.5f,
                0.5f, -0.5f,  0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f,  0.5f, -0.5f,
                // Back face
                0.5f,  0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f,  0.5f, -0.5f,
                // Left face
                -0.5f,  0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f,  0.5f,
                -0.5f,  0.5f,  0.5f,
                // Top face
                -0.5f,  0.5f, -0.5f,
                -0.5f,  0.5f,  0.5f,
                0.5f,  0.5f,  0.5f,
                0.5f,  0.5f, -0.5f,
                // Bottom face
                -0.5f, -0.5f,  0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, -0.5f,  0.5f,
        };

        float[] textCoords = {
                // Each face uses the same UV mapping
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,
                // Repeat for other faces
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,
        };

        int[] indices = {
                0,  1,  3,  3,  1,  2,        // Front
                4,  5,  7,  7,  5,  6,        // Right
                8,  9, 11, 11,  9, 10,        // Back
                12,13,15, 15,13,14,           // Left
                16,17,19, 19,17,18,           // Top
                20,21,23, 23,21,22            // Bottom
        };

        Mesh cubeMesh = new Mesh(positions, textCoords, indices);
        Model cube = new Model(cubeMesh, new Material());
        GameObject gameObject = new GameObject(cube);
        gameObject.transform.position = new Vector3f(0, 0, -5);
        scene.addGameObject(gameObject);

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
