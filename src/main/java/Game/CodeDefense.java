package Game;

import Core.Audio.SoundLoader;
import Core.ModelLoader;
import Core.Canvas.Elemets.Image;
import Game.Enemies.EnemySpawner;
import Game.Player.Turret;

public class CodeDefense extends BaseGame{
    private GameManager gameManager;
    private static EnemySpawner enemySpawner;
    private static UIManager uiManager;
    private static GameEconomics gameEconomics;

    private Turret turret;

    @Override
    public void start() {
        SoundLoader.SoundData bgMusic  = SoundLoader.loadSound("src/main/resources/audio/bg_music.ogg");
        source.load(bgMusic);
        source.play();

        gameManager = new GameManager();
        enemySpawner = new EnemySpawner();
        uiManager = new UIManager(canvas);
        uiManager.start();
        gameEconomics = new GameEconomics();

        enemySpawner.spawnDelay = 2f;
        enemySpawner.setSpawnLocation(-10,0.5f,-10);

        gameManager.setCurrentState(GameStates.Start);

        camera.setPosition(0,10,20);
        camera.setEulerRotation(30,0,0);

        turret = new Turret(ModelLoader.loadModel(Turret.MODEL, Turret.TEXTURE));

        scene.addGameObject(new Terrain(ModelLoader.loadModel(Terrain.MODEL, Terrain.TEXTURE)));
        scene.addGameObject(turret);

        // canvas.addUIObject(image);
    }

    @Override
    public void update() {
        enemySpawner.spawn();
        uiManager.update();
    }

    @Override
    public void end() {
        gameManager.setCurrentState(GameStates.End);
    }

    public Turret getTurret() {
        return turret;
    }

    public static EnemySpawner getEnemySpawner() {
        return enemySpawner;
    }

    public static GameEconomics getGameEconomics() {
        return gameEconomics;
    }
}
