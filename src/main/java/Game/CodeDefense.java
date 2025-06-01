package Game;

import Core.ModelLoader;
import Core.Scene.Entity.Transform;
import Core.Scene.UI.TextBox;
import Core.Scene.UI.UIObject;
import Game.Enemies.EnemySpawner;
import Game.Enemies.Scout;
import Game.Player.Turret;
import Main.Launcher;
import Utils.Shapes.Square;
import org.joml.Vector3f;

public class CodeDefense extends BaseGame{
    private GameManager gameManager;
    private static EnemySpawner enemySpawner;

    private Turret turret;



    @Override
    public void start() {
        gameManager = new GameManager();
        enemySpawner = new EnemySpawner();

        enemySpawner.spawnDelay = 2f;
        enemySpawner.setSpawnLocation(-10,0.5f,-10);

        gameManager.setCurrentState(GameStates.Start);

        camera.setPosition(0,10,20);
        // camera.setPosition(0,0,20);
        camera.setEulerRotation(30,0,0);

        turret = new Turret(ModelLoader.loadModel(Turret.MODEL, Turret.TEXTURE));
        Square square = new Square(0,0,1);
        UIObject sq = new UIObject(square.createMesh()) {
            @Override
            public void start() {

            }

            @Override
            public void update() {

            }
        };
        sq.setScale(100f, 100f);
        sq.setPosition(Launcher.getWindow().getWidth() /2, Launcher.getWindow().getHeight() / 2);

        scene.addGameObject(new Terrain(ModelLoader.loadModel(Terrain.MODEL, Terrain.TEXTURE)));
        scene.addGameObject(turret);

        canvas.addUIObject(sq);
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

    public static EnemySpawner getEnemySpawner() {
        return enemySpawner;
    }
}
