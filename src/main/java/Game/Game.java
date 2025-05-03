package Game;

import Core.Engine.IAppLogic;
import Core.Scene.Entity.*;
import Core.Scene.Scene;
import org.joml.Vector3f;



public class Game implements IAppLogic {

    Scene scene;

    @Override
    public void init() {
        scene = new Scene();

        float[] cubePositions = new float[]{
                // Front face
                -0.5f, -0.5f,  0.5f,   0.0f, 0.0f, // v0
                0.5f, -0.5f,  0.5f,   1.0f, 0.0f, // v1
                0.5f,  0.5f,  0.5f,   1.0f, 1.0f, // v2
                -0.5f,  0.5f,  0.5f,   0.0f, 1.0f, // v3

                // Back face
                -0.5f, -0.5f, -0.5f,   1.0f, 0.0f, // v4
                0.5f, -0.5f, -0.5f,   0.0f, 0.0f, // v5
                0.5f,  0.5f, -0.5f,   0.0f, 1.0f, // v6
                -0.5f,  0.5f, -0.5f,   1.0f, 1.0f, // v7

                // Top face
                -0.5f,  0.5f,  0.5f,   0.0f, 1.0f, // v8 (same as v3)
                0.5f,  0.5f,  0.5f,   1.0f, 1.0f, // v9 (same as v2)
                0.5f,  0.5f, -0.5f,   1.0f, 0.0f, // v10 (same as v6)
                -0.5f,  0.5f, -0.5f,   0.0f, 0.0f, // v11 (same as v7)

                // Bottom face
                -0.5f, -0.5f,  0.5f,   0.0f, 0.0f, // v12 (same as v0)
                0.5f, -0.5f,  0.5f,   1.0f, 0.0f, // v13 (same as v1)
                0.5f, -0.5f, -0.5f,   1.0f, 1.0f, // v14 (same as v5)
                -0.5f, -0.5f, -0.5f,   0.0f, 1.0f, // v15 (same as v4)

                // Right face
                0.5f, -0.5f,  0.5f,   0.0f, 0.0f, // v16 (same as v1)
                0.5f, -0.5f, -0.5f,   1.0f, 0.0f, // v17 (same as v5)
                0.5f,  0.5f, -0.5f,   1.0f, 1.0f, // v18 (same as v6)
                0.5f,  0.5f,  0.5f,   0.0f, 1.0f, // v19 (same as v2)

                // Left face
                -0.5f, -0.5f,  0.5f,   1.0f, 0.0f, // v20 (same as v0)
                -0.5f, -0.5f, -0.5f,   0.0f, 0.0f, // v21 (same as v4)
                -0.5f,  0.5f, -0.5f,   0.0f, 1.0f, // v22 (same as v7)
                -0.5f,  0.5f,  0.5f,   1.0f, 1.0f  // v23 (same as v3)
        };

        float[] cubeTextCoords = new float[]{
                // Front face
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,

                // Back face
                1.0f, 0.0f,
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,

                // Top face
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 0.0f,

                // Bottom face
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,

                // Right face
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,

                // Left face
                1.0f, 0.0f,
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f
        };

        int[] cubeIndices = new int[]{
                0, 1, 3, 3, 1, 2,   // Front face
                4, 5, 7, 7, 5, 6,   // Back face
                8, 9, 11, 11, 9, 10, // Top face
                12, 13, 15, 15, 13, 14, // Bottom face
                16, 17, 19, 19, 17, 18, // Right face
                20, 21, 23, 23, 21, 22  // Left face
        };
        Mesh cubeMesh = new Mesh(cubePositions, cubeTextCoords, cubeIndices);

        Model cube = new Model(cubeMesh, new Texture());
        GameObject gameObject = new GameObject(cube);
        gameObject.transform.position = new Vector3f(0, 0, -2);
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
