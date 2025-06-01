package Core.Scene.Entity.Component;

import org.joml.Vector3f;

public class Shooter extends BaseComponent{
    public Vector3f targetPosition;
    public Vector3f shootPoint;
    public float nanoTime = 1000000000f;
    public float shootDelay = 1f;
    public boolean startShoot;
    public Action onShoot;
    private float lastTime;

    @Override
    public void start() {
        super.start();
        lastTime = System.nanoTime();
    }
    @Override
    public void update() {
        if (!startShoot){
            return;
        }
        shootAt(targetPosition);
    }
    private void shootAt(Vector3f targetPosition){
        float currentTime = System.nanoTime();
        if(currentTime > lastTime + nanoTime * shootDelay){
            if (onShoot != null){
                onShoot.perform();
            }
            lastTime = currentTime;
        }
    }
}
