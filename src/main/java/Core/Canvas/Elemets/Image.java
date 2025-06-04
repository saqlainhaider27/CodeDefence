package Core.Canvas.Elemets;

import Core.Scene.Entity.Mesh;
import Utils.Shapes.Square;

public class Image extends UIObject {
    public Image(Mesh mesh) {
        super(mesh);
    }
    public Image(String imagePath){
        super(Square.getDefaultSquare(), imagePath);
        setScale(getTexture().getWidth(),getTexture().getHeight());
    }
    @Override
    public void start() {

    }

    @Override
    public void update() {

    }
}
