package Utils.Shapes;

import Core.Scene.Entity.Mesh;

public class Square extends Quad {
    public Square(float centerX, float centerY, float size) {

        float half = size / 2.0f;

        float[] topLeft = { centerX - half, centerY + half };
        float[] topRight = { centerX + half, centerY + half };
        float[] bottomRight = { centerX + half, centerY - half };
        float[] bottomLeft = { centerX - half, centerY - half };

        setCorners(topLeft, topRight, bottomRight, bottomLeft);
    }
    public static Mesh getDefaultSquare(){
        Square square = new Square(0,0,1);
        return square.createMesh();
    }

}
