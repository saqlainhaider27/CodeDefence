package Game;

import Core.ModelLoader;
import Core.Scene.UI.TextBox;
import Game.Enemies.EnemySpawner;
import Game.Player.Turret;

public class CodeDefense extends BaseGame{
    private GameManager gameManager;
    private EnemySpawner enemySpawner;

    private Turret turret;



    @Override
    public void start() {
        gameManager = new GameManager();
        enemySpawner = new EnemySpawner();

        enemySpawner.spawnDelay = 10f;
        enemySpawner.setSpawnLocation(-10,0,0);

        gameManager.setCurrentState(GameStates.Start);

        camera.setPosition(0,10,20);
        // camera.setPosition(0,0,20);
        camera.setEulerRotation(30,0,0);

        turret = new Turret(ModelLoader.loadModel(Turret.MODEL, Turret.TEXTURE));

        scene.addGameObject(new Terrain(ModelLoader.loadModel(Terrain.MODEL, Terrain.TEXTURE)));
        scene.addGameObject(turret);

        canvas.addUIObject(new TextBox());
    }

    @Override
    public void update() {
        enemySpawner.spawn();
    }

    @Override
    public void end() {
        gameManager.setCurrentState(GameStates.End);
    }

    public Turret getTurret() {
        return turret;
    }
}
