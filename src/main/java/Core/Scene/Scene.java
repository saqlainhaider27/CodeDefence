package Core.Scene;

import Core.Camera;
import Core.Render.Projection;
import Core.Scene.Entity.GameObject;
import Main.Launcher;
import Utils.Generics.List;

public class Scene {
    private Projection projection;
    private List<GameObject> gameObjects;
    private Camera camera;

    public Scene() {
        camera = new Camera();
        gameObjects = new List<>();
        projection = new Projection(Launcher.getWindow().getWidth(), Launcher.getWindow().getHeight());
    }
    public void cleanup() {

    }
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }
    public List<GameObject> getGameObjects(){
        return gameObjects;
    }
    public Projection getProjection() {
        return projection;
    }

    public void resize(int width, int height) {
        projection.updateProjMatrix(width, height);
    }
    public Camera getCamera() {
        return camera;
    }
}