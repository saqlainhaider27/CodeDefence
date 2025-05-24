package Game;

import Core.ModelLoader;
import Game.Enemies.EnemySpawner;
import Game.Player.Turret;

public class CodeDefense extends BaseGame{
    private GameManager gameManager;
    private EnemySpawner enemySpawner;
    @Override
    public void start() {
        gameManager = new GameManager();
        enemySpawner = new EnemySpawner();
        enemySpawner.startSpawn();
        gameManager.setCurrentState(GameStates.Start);

        camera.setPosition(0,10,20);
        // camera.setPosition(0,0,20);
        camera.setEulerRotation(30,0,0);
        scene.addGameObject(new Terrain(ModelLoader.loadModel(Terrain.MODEL, Terrain.TEXTURE)));
        scene.addGameObject(new Turret(ModelLoader.loadModel(Turret.MODEL, Turret.TEXTURE)));
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
        gameManager.setCurrentState(GameStates.End);
    }
}
