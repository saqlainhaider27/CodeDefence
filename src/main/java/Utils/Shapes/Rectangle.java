package Utils.Shapes;


public class Rectangle extends Quad {
    public Rectangle(float centerX, float centerY, float width, float height) {

        float halfW = width / 2.0f;
        float halfH = height / 2.0f;

        float[] topLeft = { centerX - halfW, centerY + halfH };
        float[] topRight = { centerX + halfW, centerY + halfH };
        float[] bottomRight = { centerX + halfW, centerY - halfH };
        float[] bottomLeft = { centerX - halfW, centerY - halfH };

        setCorners(topLeft, topRight, bottomRight, bottomLeft);
    }
}