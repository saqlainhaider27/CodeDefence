package Game;

import Core.Engine.IO.Input;
import Core.ModelLoader;
import Core.Scene.Entity.Texture;
import Core.Scene.Entity.Transform;
import Core.Scene.UI.Button;
import Core.Scene.UI.Image;
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

        Button button = new Button("Hi");
        button.setScale(100,100);
        button.setPosition(100,100);
        button.onButtonClicked = () -> {
            System.out.println("Pressed");
        };
        button.onButtonHover = () -> {
            button.setScale(120,120);
        };
        button.onExitHover = () ->{
            button.setScale(100,100);
        };

        Image image = new Image("src/main/resources/images/image.jpeg");
        image.setPosition(300,300);


        scene.addGameObject(new Terrain(ModelLoader.loadModel(Terrain.MODEL, Terrain.TEXTURE)));
        scene.addGameObject(turret);

        canvas.addUIObject(button);
        canvas.addUIObject(image);
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
