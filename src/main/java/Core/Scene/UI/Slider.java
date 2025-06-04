package Core.Scene.UI;

import Core.Scene.Entity.Mesh;
import Main.Launcher;
import Utils.Shapes.Square;
import org.joml.Vector2f;

public class Slider extends UIObject {
    private SliderFill fill;
    private float fillAmount = 1.0f;

    public Slider(Mesh mesh) {
        super(mesh);
        initFill();
    }

    public Slider(Mesh mesh, String texturePath) {
        super(mesh, texturePath);
        initFill();
    }

    public Slider() {
        super(Square.getDefaultSquare());
        initFill();
    }

    private void initFill() {
        fill = new SliderFill();
        Launcher.getGame().getCanvas().addUIObject(fill);
        fill.setParent(this);
        updateFill();
    }

    public void setFillAmount(float amount) {
        this.fillAmount = Math.max(0.0f, Math.min(1.0f, amount));
        updateFill();
    }

    public float getFillAmount() {
        return fillAmount;
    }

    private void updateFill() {
        fill.updateFillVisual(this.getScale().x, fillAmount);
    }

    @Override
    public void start() {
    }

    @Override
    public void update() {
        updateFill();
    }
}
