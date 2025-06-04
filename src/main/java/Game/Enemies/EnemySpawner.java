package Game.Enemies;

import Core.ModelLoader;
import Main.Launcher;
import Utils.Generics.List;
import org.joml.Random;
import org.joml.Vector3f;

public class EnemySpawner {
    private List<Enemy> enemies;
    private Vector3f spawnLocation;
    private int maxEnemies = 100;
    private int spawnedEnemies;
    private boolean bossSpawned;


    private float nanoTime = 1000000000f;
    public float spawnDelay = 1f;
    private float lastTime;

    public EnemySpawner() {
        enemies = new List<>();
        spawnLocation = new Vector3f();
        spawnedEnemies = 0;
        bossSpawned = false;
    }

    public void spawnRandomFromList(){
        if (bossSpawned) return;
        Random rnd = new Random();
        int i = rnd.nextInt(4);
        EnemyTypes spawnType = getSpawnType(i);
        if (spawnedEnemies >= maxEnemies){
            spawnType = EnemyTypes.Boss;
            bossSpawned = true;
        }
        switch (spawnType){
            case Scout -> spawn(new Scout(ModelLoader.loadModel(Scout.MODEL,Scout.TEXTURE)));
            case Engineer -> spawn(new Engineer(ModelLoader.loadModel(Engineer.MODEL,Engineer.TEXTURE)));
            case Soldier -> spawn(new Soldier(ModelLoader.loadModel(Soldier.MODEL,Soldier.TEXTURE)));
            case Boss -> spawn(new Boss(ModelLoader.loadModel(Boss.MODEL,Boss.TEXTURE)));
            case HeavyGunner -> spawn(new HeavyGunner(ModelLoader.loadModel(HeavyGunner.MODEL,HeavyGunner.TEXTURE)));
        }
    }

    private EnemyTypes getSpawnType(int rndInt) {
        EnemyTypes spawnType;
        switch (rndInt){
            case 1 -> spawnType = EnemyTypes.Soldier;
            case 2 -> spawnType = EnemyTypes.Engineer;
            case 3 -> spawnType = EnemyTypes.HeavyGunner;
            default -> spawnType = EnemyTypes.Scout;
        }
        return spawnType;
    }
    private <T extends Enemy> void spawn(T enemy){
        Launcher.getGame().scene.addGameObject(enemy);
        enemies.add(enemy);
        spawnedEnemies++;
        enemy.transform.setPosition(spawnLocation.x, spawnLocation.y, spawnLocation.z);
        // System.out.println("Spawned: " + enemy.getClass() + " Location: " + spawnLocation.toString() );
    }
    public <T extends Enemy>void despawn(T enemy){
        enemies.remove(enemy);
    }
    public void spawn() {
        float currentTime = System.nanoTime();
        if(currentTime > lastTime + nanoTime * spawnDelay){
            spawnRandomFromList();
            lastTime = currentTime;
        }
    }

    public Vector3f getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Vector3f spawnLocation) {
        this.spawnLocation = spawnLocation;
    }
    public void setSpawnLocation(float x,float y, float z){
        this.spawnLocation = new Vector3f(x,y,z);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
