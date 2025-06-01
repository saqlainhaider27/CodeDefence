package Core.Scene.UI;

import Core.Engine.IO.Input;
import Core.Scene.Entity.Component.Action;
import Core.Scene.Entity.Mesh;
import Utils.Shapes.Square;
import org.joml.Vector2d;

public class Button extends UIObject{
    private TextBox textBox;
    public Action onButtonClicked;
    public Action onButtonHover;
    public Action onExitHover;
    private boolean canPress;

    private float nanoTime = 1000000000f;
    public float pressWaitTime = 0.5f;
    private float lastTime;

    public Button(Mesh mesh) {
        super(mesh);
    }
    public Button(String buttonText){
        super(Square.getDefaultSquare());
    }
    public Button(){
        super(Square.getDefaultSquare());
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        float currentTime = System.nanoTime();
        if(currentTime > lastTime + nanoTime * pressWaitTime){
            canPress = true;
            lastTime = currentTime;
        }

        if (!isMouseOver()){
            if (onExitHover == null) return;
            onExitHover.perform();
            return;
        }
        if (onButtonHover != null){
            onButtonHover.perform();
        }
        if (Input.isMouseButtonPressed(0)){
            if (onButtonClicked == null) return;
            if (!canPress) return;
            onButtonClicked.perform();
            canPress = false;
        }

    }
    public boolean isMouseOver() {
        Vector2d mousePos = Input.getMousePosition();

        float halfWidth = scale.x / 2.0f;
        float halfHeight = scale.y / 2.0f;

        float left = position.x - halfWidth;
        float right = position.x + halfWidth;
        float top = position.y - halfHeight;
        float bottom = position.y + halfHeight;

        return mousePos.x >= left && mousePos.x <= right &&
                mousePos.y >= top && mousePos.y <= bottom;
    }

}
