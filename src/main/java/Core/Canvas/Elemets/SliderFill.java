package Core.Canvas.Elemets;

import Core.Scene.Entity.Mesh;
import Utils.Shapes.Square;

public class SliderFill extends UIObject {

    public SliderFill() {
        super(Square.getDefaultSquare());
        setColor(1, 0, 0, 1);
    }

    public SliderFill(Mesh mesh) {
        super(mesh);
    }

    public SliderFill(Mesh mesh, String texturePath) {
        super(mesh, texturePath);
    }

    @Override
    public void start() {
    }

    @Override
    public void update() {
    }

    public void updateFillVisual(float parentWidth, float fillAmount) {
        float width = parentWidth * fillAmount;
        float xOffset = -(parentWidth * (1 - fillAmount)) / 2.0f;

        setScale(width / parentWidth, 1);
        setPosition(xOffset / parentWidth,0);
        //setPosition(xOffset,0);

    }
}
