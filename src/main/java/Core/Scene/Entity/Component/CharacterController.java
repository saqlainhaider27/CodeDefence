package Core.Scene.Entity.Component;

import Utils.Interfaces.Action;
import org.joml.Vector3f;

public class CharacterController extends BaseComponent{
    public Vector3f targetPosition;
    private float stoppingDistance = 0.1f;
    public float speed = 1f;

    public Action onReached;
    @Override
    public void start() {
        super.start();
        targetPosition = transform.position;
    }

    @Override
    public void update() {
        if (isStopped()){
            if (onReached == null){
            }else {
                onReached.perform();
            }

        }else {
            moveTo(targetPosition);
        }
    }
    private void moveTo(Vector3f position) {
        Vector3f direction = new Vector3f(position).sub(transform.position);
        float distance = direction.length();

        if (distance > 0) {
            direction.normalize();
        }

        float moveStep = Math.min(speed * 0.1f, distance);
        direction.mul(moveStep);
        transform.position.add(direction);
    }
    private boolean isStopped(){
        return transform.position.distance(targetPosition) < stoppingDistance;
    }

}
