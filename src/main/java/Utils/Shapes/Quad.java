package Utils.Shapes;

import Core.Scene.Entity.Mesh;

public abstract class Quad {
    protected float[] vertices;
    protected int[] indices = {
            0, 1, 2,
            2, 3, 0
    };
    protected float[] textureCoords = {
            0, 0,  // Top-left
            1, 0,  // Top-right
            1, 1,  // Bottom-right
            0, 1   // Bottom-left
    };

    public Mesh createMesh() {
        return new Mesh(vertices, textureCoords, indices);
    }

    protected void setCorners(float[] topLeft, float[] topRight, float[] bottomRight, float[] bottomLeft) {
        vertices = new float[] {
                topLeft[0], topLeft[1], 0.0f,
                topRight[0], topRight[1], 0.0f,
                bottomRight[0], bottomRight[1], 0.0f,
                bottomLeft[0], bottomLeft[1], 0.0f
        };
    }
}
