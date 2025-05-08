package Core.Scene.Entity.Component;

import Core.Scene.Entity.GameObject;
import Core.Scene.Entity.Player;
import org.joml.Vector3f;

public class CharacterController extends BaseComponent{
    public Vector3f targetPosition;
    private float stoppingDistance = 0.1f;
    public Action onReached;
    public CharacterController(GameObject gameObject) {
        super(gameObject);
    }
    public CharacterController(Player player) {
        super(player);
    }
    public CharacterController(){}
    @Override
    public void start() {
        super.start();
        targetPosition = transform.position;
    }

    @Override
    public void update() {
        if (isStopped()){
            if (onReached == null){
                //System.out.println("OnReached Action is null");
            }else {
                onReached.perform();
            }

        }else {
            moveTo(targetPosition);
        }
    }
    private void moveTo(Vector3f position){
        // Vector3f direction = new Vector3f(position).sub(transform.position);
        // Vector3f normalizedDirection = direction.normalize();
        // transform.incrementPosition(normalizedDirection);
        transform.position = new Vector3f(transform.position).lerp(position, 0.1f);
    }
    private boolean isStopped(){
        return transform.position.distance(targetPosition) < stoppingDistance;
    }

}
